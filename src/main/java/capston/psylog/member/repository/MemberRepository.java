package capston.psylog.member.repository;

import capston.psylog.member.entity.MemberEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    Optional<MemberEntity> findByMemberId(String memberId);
    Optional<MemberEntity> findByMemberEmail(String email);

    Optional<MemberEntity> findMemberEntityByMemberEmailAndMemberName(String email, String name);

    Optional<MemberEntity> findMemberEntityByMemberEmailAndMemberNameAndMemberId(String email, String name, String id);

    boolean existsMemberEntityByMemberId(String id);
    boolean existsMemberEntityByMemberEmail(String email);

    @Transactional
    void deleteMemberEntityByMemberId(String id);
}
