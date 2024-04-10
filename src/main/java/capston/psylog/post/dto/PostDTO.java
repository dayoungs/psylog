package capston.psylog.post.dto;

import capston.psylog.post.entity.PostEntity;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    private Long postNo;
    private String postWriter;
    private LocalDate postDate;
    private LocalDate updateDate;
    private String postEmotion;
    private String postTitle;
    private String postContent;
    private String aiAnswer;

    public static PostDTO toPostDTO(PostEntity postEntity){
        PostDTO postDTO = new PostDTO();
        postDTO.setPostNo(postEntity.getPostNo());
        postDTO.setPostWriter(postEntity.getPostWriter());
        postDTO.setPostDate(postEntity.getPostDate());
        postDTO.setUpdateDate(postEntity.getUpdateDate());
        postDTO.setPostEmotion(postEntity.getPostEmotion());
        postDTO.setPostTitle(postEntity.getPostTitle());
        postDTO.setPostContent(postEntity.getPostContent());
        postDTO.setAiAnswer(postEntity.getAiAnswer());
        return postDTO;
    }
}
