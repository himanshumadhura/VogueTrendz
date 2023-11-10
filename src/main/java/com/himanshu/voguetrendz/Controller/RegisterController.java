package com.himanshu.voguetrendz.Controller;

import com.himanshu.voguetrendz.Entities.LoginData;
import com.himanshu.voguetrendz.Entities.User;
import com.himanshu.voguetrendz.Helper.Message;
import com.himanshu.voguetrendz.Repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("title", "Register");
        model.addAttribute("loginData", new LoginData());
        model.addAttribute("user", new User());
        return "Authentication/signup";
    }

    @PostMapping("/regProcess")
    public String regProcess(@Valid @ModelAttribute("user") User user, BindingResult result, @RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model model, HttpServletRequest request, HttpSession session){
        try{
            model.addAttribute("loginData", new LoginData());
            if(!agreement){
                model.addAttribute("agree", "You haven't agree the terms & conditions");
                return "Authentication/signup";
            }else if(result.hasErrors()){
                return "Authentication/signup";
            }else if(!user.getPassword().equals(request.getParameter("confirmPassword"))){
                model.addAttribute("cMsg","Password doesn't match");
                return "Authentication/signup";
            }else{
                model.addAttribute("loginData", new LoginData());
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                user.setRole("ROLE_CUSTOMER");
                this.userRepository.save(user);
                System.out.println("USER SAVED");
                return "Authentication/login";
            }
        }catch (DataIntegrityViolationException dataIntegrityViolationException){
            session.setAttribute("message", new Message("User already exists", "alert-danger"));
            return "Authentication/signup";
        }catch (Exception e) {
            session.setAttribute("message", new Message(e.getMessage() , "alert-danger"));
            return "Authentication/signup";
        }
    }
}
