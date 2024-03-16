package capston.psylog.post.service;

import capston.psylog.post.dto.PostDTO;
import capston.psylog.post.entity.PostEntity;
import capston.psylog.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public void save(PostDTO postDTO) {
        PostEntity postEntity = PostEntity.toSaveEntity(postDTO);
        postRepository.save(postEntity);
    }

    public List<PostDTO> findByWriter(String id) {
        List<PostEntity> postEntityList = postRepository.findByPostWriter(id);
        List<PostDTO> postDTOList = new ArrayList<>();
        for (PostEntity postEntity: postEntityList) {
            postDTOList.add(PostDTO.toPostDTO(postEntity));
        }
        return postDTOList;
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
        postRepository.save(postEntity);
        return findById(postDTO.getPostNo());
    }
}
