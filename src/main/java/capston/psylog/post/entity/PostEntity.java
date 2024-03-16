package capston.psylog.post.entity;

import capston.psylog.post.dto.PostDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "post_table")
public class PostEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postNo;

    @Column(nullable = false)
    private String postWriter;

    @Column
    private String postEmotion;

    @Column
    private String postTitle;

    @Column
    private String postContent;

    public static PostEntity toSaveEntity(PostDTO postDTO){
        PostEntity postEntity = new PostEntity();
        postEntity.setPostWriter(postDTO.getPostWriter());
        postEntity.setPostEmotion(postDTO.getPostEmotion());
        postEntity.setPostTitle(postDTO.getPostTitle());
        postEntity.setPostContent(postDTO.getPostContent());
        return postEntity;
    }

    public static PostEntity toUpdateEntity(PostDTO postDTO) {
        PostEntity postEntity = new PostEntity();
        postEntity.setPostNo(postDTO.getPostNo());
        postEntity.setPostWriter(postDTO.getPostWriter());
        postEntity.setPostEmotion(postDTO.getPostEmotion());
        postEntity.setPostTitle(postDTO.getPostTitle());
        postEntity.setPostContent(postDTO.getPostContent());
        return postEntity;
    }
}
