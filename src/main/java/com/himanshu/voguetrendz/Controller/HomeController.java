package com.himanshu.voguetrendz.Controller;

import com.himanshu.voguetrendz.Entities.LoginData;
import com.himanshu.voguetrendz.Entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomeController {

    @ModelAttribute
    public void commonData(Model model){
        model.addAttribute("loginData", new LoginData());
        model.addAttribute("user", new User());
    }

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("title","VogueTendz");
        model.addAttribute("pg","home");
        return "Public/home";
    }

    @GetMapping("/about")
    public String about(Model model){
        model.addAttribute("title","About - VogueTrendz");
        model.addAttribute("pg","about");
        return "Public/about";
    }

    @GetMapping("/contact")
    public String contact(Model model){
        model.addAttribute("title","Contact Us");
        model.addAttribute("pg","contact");
        return "Public/contact";
    }
}
