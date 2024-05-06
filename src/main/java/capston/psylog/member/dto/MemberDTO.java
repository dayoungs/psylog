package capston.psylog.member.dto;

import capston.psylog.member.entity.MemberEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MemberDTO {
    private Long memberNo;
    private String memberId;
    private String memberPassword;
    private String memberName;
    private String memberEmail;
    private String friendNickname;

    //private String confirmPassword;

    public static MemberDTO toMemberDTO(MemberEntity memberEntity) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberNo(memberEntity.getMemberNo());
        memberDTO.setMemberId(memberEntity.getMemberId());
        memberDTO.setMemberEmail(memberEntity.getMemberEmail());
        memberDTO.setMemberPassword(memberEntity.getMemberPassword());
        memberDTO.setMemberName(memberEntity.getMemberName());
        memberDTO.setFriendNickname(memberEntity.getFriendNickname());
        return memberDTO;
    }
}
