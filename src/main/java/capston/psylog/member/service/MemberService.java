package capston.psylog.member.service;

import capston.psylog.member.dto.MemberDTO;
import capston.psylog.member.entity.MemberEntity;
import capston.psylog.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void save(MemberDTO memberDTO) {
        String password = passwordEncoder.encode(memberDTO.getMemberPassword());
        memberDTO.setMemberPassword(password);
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        memberRepository.save(memberEntity);
    }

    public int idcheck(String memberId){
        boolean check = memberRepository.existsMemberEntityByMemberId(memberId);
        if (!check) return 1;
        else return 0;
    }

    public int emailcheck(String memberEmail){
        boolean check = memberRepository.existsMemberEntityByMemberEmail(memberEmail);
        if (!check) return 1;
        else return 0;
    }

    public MemberDTO login(MemberDTO memberDTO){
        Optional<MemberEntity> byMemberId = memberRepository.findByMemberId(memberDTO.getMemberId());

        if (byMemberId.isPresent()) {
            // 조회 결과가 있다(해당 아이디를 가진 회원 정보가 있다)
            MemberEntity memberEntity = byMemberId.get();

            if (passwordEncoder.matches(memberDTO.getMemberPassword(), memberEntity.getMemberPassword())){
                MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);
                return dto;
            } else {
                // 비밀번호 불일치(로그인실패)
                return null;
            }
        } else {
            // 조회 결과가 없다(해당 아이디를 가진 회원이 없다)
            return null;
        }
    }

    public MemberDTO findById(String id) {
       Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberId(id);
       if (optionalMemberEntity.isPresent()){
           return MemberDTO.toMemberDTO(optionalMemberEntity.get());
       }
       else return null;
    }

    public MemberDTO findMemberId(String email, String name){
        Optional<MemberEntity> member = memberRepository.findMemberEntityByMemberEmailAndMemberName(email, name);

        if (member.isPresent()){
            return MemberDTO.toMemberDTO(member.get());
        }
        else return null;
    }

    public MemberDTO findMemberPw(String email, String name, String id){
        Optional<MemberEntity> member = memberRepository.findMemberEntityByMemberEmailAndMemberNameAndMemberId(email, name, id);

        if (member.isPresent()) return MemberDTO.toMemberDTO(member.get());
        else return null;
    }

    public void update(MemberDTO memberDTO){
        String password = passwordEncoder.encode(memberDTO.getMemberPassword());
        memberDTO.setMemberPassword(password);
        memberRepository.save(MemberEntity.toUpdateMemberEntity(memberDTO));
    }

    public void update_password(MemberDTO memberDTO, String password){
        String pw = passwordEncoder.encode(password);
        memberDTO.setMemberPassword(pw);
        memberRepository.save(MemberEntity.toUpdateMemberEntity(memberDTO));
    }

    public int leave(MemberDTO memberDTO, String password){
        if (passwordEncoder.matches(password, memberDTO.getMemberPassword())) {
            MemberEntity member = MemberEntity.toMemberEntity(memberDTO);
            memberRepository.deleteMemberEntityByMemberId(member.getMemberId());
            return 1;
        }
        else return 0;
    }
}
