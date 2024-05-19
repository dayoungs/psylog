package capston.psylog.post.controller;

import capston.psylog.member.dto.MemberDTO;
import capston.psylog.member.service.MemberService;
import capston.psylog.post.dto.PostDTO;
import capston.psylog.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final MemberService memberService;

    @GetMapping("/post/save")
    public String saveForm(){
        return "write";
    }

    @PostMapping("/post/save")
    public String save(@ModelAttribute PostDTO postDTO, @SessionAttribute(name = "loginId") String loginId){
        postDTO.setPostWriter(loginId);
        postDTO.setUpdateDate(postDTO.getPostDate());
        postService.save(postDTO);

        return "redirect:/login_home";
    }

    @GetMapping("/login_home")
    public String findByWriter(@SessionAttribute(name = "loginId") String loginId, Model model){
        List<PostDTO> postDTOList = postService.findByWriter(loginId);
        model.addAttribute("postList", postDTOList);

        return "home";
    }

    @ResponseBody
    @GetMapping("/post/emotionfind")
    public String emotion_find(@RequestParam("date") String date, @SessionAttribute(name="loginId") String loginId) {
        String subdate = date.substring(0, 24);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd yyyy HH:mm:ss", Locale.ENGLISH);

        LocalDateTime localTime = LocalDateTime.parse(subdate, formatter);
        LocalDate output = localTime.toLocalDate();
        System.out.println("output date = " + output);

        PostDTO post = postService.emotionfind(loginId, output);
        if (post == null) {
            return null;
        }
        String emotion = post.getPostEmotion();
        return emotion;
    }

    @GetMapping("/post/list")
    public String postlist(@SessionAttribute(name = "loginId") String loginId, Model model){
        List<PostDTO> postDTOList = postService.findByWriter(loginId);
        model.addAttribute("postList", postDTOList);

        return "my_diary";
    }

    @GetMapping("/post/{postNo}")
    public String findById(@PathVariable Long postNo, Model model){
        PostDTO postDTO = postService.findById(postNo);
        String writer = postDTO.getPostWriter();
        MemberDTO memberDTO = memberService.findById(writer);
        model.addAttribute("member", memberDTO); // 모델에 friendName 추가
        model.addAttribute("post", postDTO);
        return "writingResult";
    }

    @GetMapping("/post/update/{id}")
    public String updateForm(@PathVariable Long id, Model model){
        PostDTO postDTO = postService.findById(id);
        model.addAttribute("postUpdate", postDTO);
        return "edit_write";
    }

    @PostMapping("/post/update")
    public String update(@ModelAttribute PostDTO postDTO, Model model){
        PostDTO post = postService.update(postDTO);
        model.addAttribute("post", post);
        return "redirect:/login_home";
    }


    @GetMapping("/post/delete/{id}")
    public String delete(@PathVariable Long id) {
        PostDTO postDTO = postService.findById(id);
        postService.delete(postDTO);
        return "redirect:/login_home";
    }


}
