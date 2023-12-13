package com.himanshu.voguetrendz.Controller;

import com.himanshu.voguetrendz.Entities.LoginData;
import com.himanshu.voguetrendz.Entities.User;
import com.himanshu.voguetrendz.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("title", "Login");
        model.addAttribute("pg", "login");
        model.addAttribute("loginData", new LoginData());
        model.addAttribute("user", new User());
        return "Authentication/login";
    }
}
