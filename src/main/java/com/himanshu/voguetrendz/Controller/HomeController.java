package com.himanshu.voguetrendz.Controller;

import com.himanshu.voguetrendz.Entities.LoginData;
import com.himanshu.voguetrendz.Entities.Product;
import com.himanshu.voguetrendz.Entities.User;
import com.himanshu.voguetrendz.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

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

    @GetMapping("/collection")
    public String collection(Model model){
        try{
            List<Product> products =  this.productService.getAllProducts();
            if(products.size()<=0){
                model.addAttribute("message", "No Product Found");
            }
            model.addAttribute("products", products);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        model.addAttribute("title", "Collections");
        model.addAttribute("pg","collection");
        return "Public/allProducts";
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
        model.addAttribute("title", "Collections-"+category);
        model.addAttribute("pg","collection");
        return "Public/products";
    }

    @GetMapping("/productInfo/{productId}")
    public String productDetail(@PathVariable("productId")int productId, Model model){
        Product product = this.productService.getProductById(productId);
        model.addAttribute("product", product);
        model.addAttribute("pg", "collection");
        model.addAttribute("title", "Collection - "+product.getName());
        return "Public/productDetail";
    }

}
