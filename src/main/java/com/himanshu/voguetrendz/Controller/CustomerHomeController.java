package com.himanshu.voguetrendz.Controller;

import com.himanshu.voguetrendz.Entities.User;
import com.himanshu.voguetrendz.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class CustomerHomeController {

    @Autowired
    private UserRepository userRepository;

    @ModelAttribute
    public void commonData(Model model, Principal principal){
        User user = userRepository.getUserByUsername(principal.getName());

        model.addAttribute("user", user);
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model){
        model.addAttribute("title","VogueTendz");
        model.addAttribute("pg","home");
        return "Customer/dashboard";
    }
    @GetMapping("/about")
    public String about(Model model){
        model.addAttribute("title","About - VogueTrendz");
        model.addAttribute("pg","about");
        return "Customer/customer_about";
    }

    @GetMapping("/contact")
    public String contact(Model model){
        model.addAttribute("title","Contact Us");
        model.addAttribute("pg","contact");
        return "Customer/customer_contact";
    }

    @GetMapping("/wishlist")
    public String wishlist(Model model){
        model.addAttribute("pg", "wishlist");
        model.addAttribute("title","Wishlist");
        return "Customer/wishlist";
    }

}
