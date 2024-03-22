package capston.psylog.ChatGpt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class WebController {
    private final RestTemplate restTemplate;

    public WebController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/input")
    public String showInputForm() {
        return "inputForm";
    }

    @PostMapping("/generate")
    public String generateResponse(@RequestParam("userInput") String userInput, Model model) {
        try {
            String response = restTemplate.postForObject(
                    "http://localhost:8080/api/openai/generate",
                    userInput,
                    String.class
            );
            model.addAttribute("response", response);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("response", "Error generating response");
        }
        return "response";
    }
}