package com.himanshu.voguetrendz.Controller;

import com.himanshu.voguetrendz.Entities.LoginData;
import com.himanshu.voguetrendz.Entities.User;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("title", "Login");
        model.addAttribute("loginData", new LoginData());
        model.addAttribute("user", new User());
        return "Authentication/login";
    }

    @PostMapping("/doLogin")
    public String process(@Valid @ModelAttribute("loginData") LoginData loginData, BindingResult result, Model model, Principal principal){
        if(result.hasErrors()){
            return "Authentication/login";
        }
        System.out.println("!!!!!! DO LOGIN WORKING !!!!!!");
        return "Public/about";
    }
}
