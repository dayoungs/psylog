package capston.psylog.post.service;

import capston.psylog.post.dto.PostDTO;
import capston.psylog.post.entity.PostEntity;
import capston.psylog.post.repository.PostRepository;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public void save(PostDTO postDTO) {
        PostEntity postEntity = PostEntity.toSaveEntity(postDTO);
        postEntity = postRepository.save(postEntity);

        String response = getResponse(postEntity.getPostNo());

        JsonObject sentiment = analyzeSentiment(postEntity.getPostNo());
        String negative = sentiment.get("negative").getAsString();
        String positive = sentiment.get("positive").getAsString();
        String neutral = sentiment.get("neutral").getAsString();

        postEntity.setAiAnswer(response);
        postEntity.setNegative(negative);
        postEntity.setPositive(positive);
        postEntity.setNeutral(neutral);

        postRepository.save(postEntity);
    }
    public List<PostDTO> findByWriter(String id) {
        List<PostEntity> postEntityList = postRepository.findByPostWriter(id);
        List<PostDTO> postDTOList = new ArrayList<>();
        for (PostEntity postEntity: postEntityList) {
            postDTOList.add(PostDTO.toPostDTO(postEntity));
        }
        Comparator<PostDTO> comparingPostNoReverse = Comparator.comparing(PostDTO::getPostNo, Comparator.reverseOrder());
        List<PostDTO> reverselists = postDTOList.stream().sorted(comparingPostNoReverse).collect(Collectors.toList());
        return reverselists;
    }



    public PostDTO findById(Long id) {
        Optional<PostEntity> optionalPostEntity = postRepository.findById(id);
        if (optionalPostEntity.isPresent()){
            PostEntity postEntity = optionalPostEntity.get();
            PostDTO postDTO = PostDTO.toPostDTO(postEntity);

            return postDTO;
        } else{
            return null;
        }
    }

    public PostDTO update(PostDTO postDTO) {
        PostEntity postEntity = PostEntity.toUpdateEntity(postDTO);
        String response = getResponse(postEntity.getPostNo());

        JsonObject sentiment = analyzeSentiment(postEntity.getPostNo());
        String negative = sentiment.get("negative").getAsString();
        String positive = sentiment.get("positive").getAsString();
        String neutral = sentiment.get("neutral").getAsString();

        postEntity.setAiAnswer(response);
        postEntity.setNegative(negative);
        postEntity.setPositive(positive);
        postEntity.setNeutral(neutral);

        postRepository.save(postEntity);

        return findById(postDTO.getPostNo());
    }


    @Value("${openai.api.key}")
    private String apiKey;

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String url;

    @Value("${clova.api.endpoint}")
    private String clovaApiEndpoint;

    @Value("${clova.api.key}")
    private String clovaApiKey;

    @Value("${clova.api.secret}")
    private String clovaApiSecret;

    public String getResponse(Long postId) {
        PostEntity postEntity = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 글이 존재하지 않습니다."));
        String result = postEntity.getPostContent();
        String prompt = result.replace("\n", "").replace("\r", "");


        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(apiKey);
        headers.add("Content-Type", "application/json");

        String requestBody = String.format("{\"model\": \"%s\", \"messages\": [{\"role\": \"user\", \"content\": \"%s\"}]}", model, prompt);
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        String responseBody = response.getBody();

        JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();
        String content = jsonObject.getAsJsonArray("choices").get(0).getAsJsonObject().getAsJsonObject("message").get("content").getAsString();

        return content;
    }

    public JsonObject analyzeSentiment(Long postId) {
        PostEntity postEntity = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 글이 존재하지 않습니다."));
        String result = postEntity.getPostContent();
        String prompt = result.replace("\n", "").replace("\r", "");

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-NCP-APIGW-API-KEY-ID", clovaApiKey);
        headers.set("X-NCP-APIGW-API-KEY", clovaApiSecret);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("content", prompt);

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(clovaApiEndpoint, requestEntity, String.class);

        String clova = response.getBody();

        JsonObject jsonObject = JsonParser.parseString(clova).getAsJsonObject();
        JsonObject sentiment = jsonObject.getAsJsonObject("document").getAsJsonObject("confidence");

        return sentiment;
    }

}
