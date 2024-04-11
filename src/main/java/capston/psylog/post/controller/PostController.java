package capston.psylog.post.controller;

import capston.psylog.member.dto.MemberDTO;
import capston.psylog.member.service.MemberService;
import capston.psylog.post.dto.PostDTO;
import capston.psylog.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        System.out.println("postDTO = " + postDTO);
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
        return "edit_diary";
    }

    @PostMapping("/post/update")
    public String update(@ModelAttribute PostDTO postDTO, Model model){
        PostDTO post = postService.update(postDTO);
        model.addAttribute("post", post);
        return "writing";
    }


}
