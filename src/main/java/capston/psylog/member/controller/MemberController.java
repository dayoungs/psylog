package capston.psylog.member.controller;

import capston.psylog.member.dto.MemberDTO;
import capston.psylog.member.entity.MemberEntity;
import capston.psylog.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
    public String save(@ModelAttribute MemberDTO memberDTO, BindingResult bindingResult){
        /*
        if (!memberDTO.getMemberPassword().equals(memberDTO.getConfirmPassword())){
            bindingResult.addError(new FieldError("memberDTO", "confirmPassword", "비밀번호가 일치하지 않습니다."));

            return "register";
        }
        */
        memberService.save(memberDTO);
        return "login";
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
            return "home";
        } else {
            // login 실패
            return "login";
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
    public String update(@ModelAttribute MemberDTO memberDTO){
        System.out.println("memberDTO = " + memberDTO);
        memberService.update(memberDTO);
        return "redirect:/member/mypage/edit";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);  // Session이 없으면 null return
        if(session != null) {
            session.invalidate();
        }
        return "start";
    }

}
