package capston.psylog.post.service;

import capston.psylog.post.dto.PostDTO;
import capston.psylog.post.entity.PostEntity;
import capston.psylog.post.repository.PostRepository;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public void save(PostDTO postDTO) {
        PostEntity postEntity = PostEntity.toSaveEntity(postDTO);
        postEntity = postRepository.save(postEntity);
        String response = getResponse(postEntity.getPostNo());
        postEntity.setAiAnswer(response);
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
        postEntity.setAiAnswer(response);
        postRepository.save(postEntity);
        return findById(postDTO.getPostNo());
    }


    @Value("${openai.api.key}")
    private String apiKey;

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String url;

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
}
