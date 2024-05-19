package capston.psylog.post.repository;

import capston.psylog.post.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
    List<PostEntity> findByPostWriter(String loginId);
    Optional<PostEntity> findByPostWriterAndPostDate(String loginId, LocalDate date);
}
