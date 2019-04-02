package app.withyou.ahometoshare.controller;

import app.withyou.ahometoshare.model.Article;
import app.withyou.ahometoshare.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StaticPageController {


    @Autowired
    ArticleService articleService;

    @GetMapping("/static/howWeWork")
    public String howWeWork(Model model) {
        return "howWeWork";
    }
    
    @GetMapping("/static/aboutUs")
    public String aboutUs(Model model) {
        return "aboutUs";
    }
    
    @GetMapping("/static/faq")
    public String faq(Model model) {
       return "FAQ"; 
    }

    @GetMapping("/static/termsOfUse")
    public String termsOfUse(Model model) {
        Article article = articleService.getTermsOfUse();
        model.addAttribute("termsOfUseContent",article.getContent());
        return "termsOfUse";
    }

    @GetMapping("/static/privacyPolicy")
    public String privacyPolicy(Model model) {
        Article article = articleService.getPrivacyPolicy();
        model.addAttribute("privacyPolicy",article.getContent());
        return "privacyPolicy";
    }
}
