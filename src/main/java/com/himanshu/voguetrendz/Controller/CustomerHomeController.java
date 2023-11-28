package com.himanshu.voguetrendz.Controller;

import com.himanshu.voguetrendz.Entities.Product;
import com.himanshu.voguetrendz.Entities.User;
import com.himanshu.voguetrendz.Repository.UserRepository;
import com.himanshu.voguetrendz.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user")
public class CustomerHomeController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductService productService;

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

    @GetMapping("/collections/{category}")
    public String mens(Model model, @PathVariable("category") String category){
        try{
            List<Product> products =  this.productService.getProductsByCategory(category);
            if(products.size()<=0){
                model.addAttribute("message", "No Product Found");
            }
            model.addAttribute("products", products);
        }catch (Exception ex){
            ex.printStackTrace();
        }

        model.addAttribute("title", "Collections-Mens");
        return "Customer/customer_products";
    }

}
