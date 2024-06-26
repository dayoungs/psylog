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
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postNo;

    @Column(nullable = false)
    private String postWriter;

    @Column(name="post_date",updatable = false)
    private LocalDate postDate;

    @Column(name = "update_date",insertable = false)
    private LocalDate updateDate;

    @Column
    private String postEmotion;

    @Column
    private String postTitle;

    @Lob
    @Column (length = 100000)
    private String postContent;

    @Lob
    @Column(length = 100000)
    private String aiAnswer;

    @Column
    private String negative;

    @Column
    private String positive;

    @Column
    private String neutral;

    public static PostEntity toSaveEntity(PostDTO postDTO){
        PostEntity postEntity = new PostEntity();
        postEntity.setPostWriter(postDTO.getPostWriter());
        postEntity.setPostEmotion(postDTO.getPostEmotion());
        postEntity.setPostTitle(postDTO.getPostTitle());
        postEntity.setPostContent(postDTO.getPostContent());
        postEntity.setAiAnswer(postDTO.getAiAnswer());
        postEntity.setNegative(postDTO.getNegative());
        postEntity.setPositive(postDTO.getPositive());
        postEntity.setNeutral(postDTO.getNeutral());
        postEntity.setPostDate(postDTO.getPostDate());
        postEntity.setUpdateDate(postDTO.getUpdateDate());
        return postEntity;
    }

    public static PostEntity toUpdateEntity(PostDTO postDTO) {
        PostEntity postEntity = new PostEntity();
        postEntity.setPostNo(postDTO.getPostNo());
        postEntity.setPostWriter(postDTO.getPostWriter());
        postEntity.setPostEmotion(postDTO.getPostEmotion());
        postEntity.setPostTitle(postDTO.getPostTitle());
        postEntity.setPostContent(postDTO.getPostContent());
        postEntity.setAiAnswer(postDTO.getAiAnswer());
        postEntity.setNegative(postDTO.getNegative());
        postEntity.setPositive(postDTO.getPositive());
        postEntity.setNeutral(postDTO.getNeutral());
        postEntity.setUpdateDate(postDTO.getUpdateDate());
        return postEntity;
    }
}
