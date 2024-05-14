package capston.psylog.member.controller;

import capston.psylog.member.dto.MemberDTO;
import capston.psylog.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
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
    public String save(@ModelAttribute MemberDTO memberDTO){
        memberService.save(memberDTO);
        return "login";
    }

    @ResponseBody
    @GetMapping("/member/idcheck")
    public int id_check(@RequestParam("username") String username){
        int result = memberService.idcheck(username); // 중복확인한 값을 int로 받음
        return result;
    }

    @ResponseBody
    @GetMapping("/member/emailcheck")
    public int email_check(@RequestParam("useremail") String useremail){
        int result = memberService.emailcheck(useremail); // 중복확인한 값을 int로 받음
        return result;
    }

    @GetMapping("/find_id")
    public String findIdForm(){
        return "find_id";
    }

    @PostMapping("/find_id")
    public String find_id(@RequestParam String email, @RequestParam String name, Model model){
        MemberDTO member = memberService.findMemberId(email, name);
        if (member == null){
            return "find_id2";
        }
        model.addAttribute("memberId", member.getMemberId());
        return "find_id_success";
    }

    @GetMapping("/find_password")
    public String findPasswordForm(){
        return "find_password";
    }

    @PostMapping("/find_password")
    public String find_password(@RequestParam String email, @RequestParam String name, @RequestParam String id, Model model){
        MemberDTO member = memberService.findMemberPw(email, name, id);
        if (member == null){
            return "find_password2";
        }
        String newPw = RandomStringUtils.randomAlphanumeric(10);
        memberService.update_password(member, newPw);
        model.addAttribute("memberPassword", newPw);
        return "find_password_success";
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
    public String update(@ModelAttribute MemberDTO memberDTO) {
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

    /*
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);  // Session이 없으면 null return
        if(session != null) {
            session.invalidate();
        }
        else System.out.println("session null");
        return "start";
    }
     */

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
