package capston.psylog.member.controller;

import capston.psylog.member.dto.MemberDTO;
import capston.psylog.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/member/register")
    public String registerForm(){
        return "register";
    }

    @PostMapping("/member/register")
    public String save(@ModelAttribute MemberDTO memberDTO, String confirmPassword){
        if (!confirmPassword.equals(memberDTO.getMemberPassword()))
            return "register2";
        memberService.save(memberDTO);
        return "login";
    }

    @PostMapping("/find_id")
    public String find_id(@RequestParam String email, Model model){
        MemberDTO member = memberService.findMemberId(email);
        model.addAttribute("memberId", member.getMemberId());
        return "find_id_success";
    }

    @GetMapping("/member/login")
    public String loginForm(){
        return "login";
    }

    @PostMapping("/member/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session){
        MemberDTO loginResult = memberService.login(memberDTO);
        if (loginResult != null) {
            // login 성공
            session.setAttribute("loginId", loginResult.getMemberId());
            return "redirect:/login_home";
        } else {
            // login 실패
            return "login2";
        }
    }

    @GetMapping("/member/mypage")
    public String myPage(){
        return "my_page";
    }

    @GetMapping("/member/mypage/edit")
    public String edit(@SessionAttribute(name = "loginId") String loginId, Model model){
        MemberDTO memberDTO = memberService.findById(loginId);
        model.addAttribute("member", memberDTO);
        return "mem_info_change";
    }

    @PostMapping("/member/mypage/edit")
    public String update(HttpServletRequest request, @ModelAttribute MemberDTO memberDTO, String confirmPassword) throws Exception {
        if (!confirmPassword.equals(memberDTO.getMemberPassword())) {
            request.setAttribute("msg", "비밀번호를 다시 입력해주세요");
            request.setAttribute("url", "/member/mypage/edit");
            return "alert";
        }
        memberService.update(memberDTO);
        return "my_page";
    }

    @GetMapping("/member/{memberId}")
    public String findFriendName(@PathVariable String memberId, Model model){
        MemberDTO memberDTO = memberService.findById(memberId);
        String friendName = memberDTO.getFriendNickname(); // friendName 추출
        model.addAttribute("friendName", friendName); // 모델에 friendName 추가
        return "writingResult"; // 결과를 보여줄 뷰 이름 반환
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);  // Session이 없으면 null return
        if(session != null) {
            session.invalidate();
        }
        return "start";
    }

    @GetMapping("/check_leave")
    public String leave_form() {
        return "check_leave";
    }

    @PostMapping("/check_leave")
    public String check_leave(@SessionAttribute(name = "loginId") String loginId, String password) {
        MemberDTO memberDTO = memberService.findById(loginId);
        int check = memberService.leave(memberDTO, password);
        if (check == 0) return "check_leave2";
        return "leave";
    }

}
