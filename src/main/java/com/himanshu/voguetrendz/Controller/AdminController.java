package com.himanshu.voguetrendz.Controller;

import com.himanshu.voguetrendz.Entities.Product;
import com.himanshu.voguetrendz.Entities.User;
import com.himanshu.voguetrendz.Repository.UserRepository;
import com.himanshu.voguetrendz.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {

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
        model.addAttribute("title", "Admin Dashboard");
        return "Admin/admin_dashboard";
    }

    @GetMapping("/addProduct")
    public String addProduct(Model model){
        model.addAttribute("title", "Admin Dashboard - Add Product");
        model.addAttribute("pg", "addProduct");
        return "Admin/admin_addProduct";
    }

    @PostMapping("/addProduct")
    public String addProduct(@ModelAttribute("product")Product product, @RequestParam("productName")String productName, @RequestParam("imageUrl") MultipartFile file){
        product.setName(productName);
        product.setPrice("₹ "+product.getPrice());
        try{
            if(file.isEmpty()){
                System.out.println("File is empty");
            }else{
                File file1 = new ClassPathResource("static/image").getFile();
                Path path = Paths.get(file1.getAbsolutePath()+File.separator+file.getOriginalFilename());
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                product.setImage(file.getOriginalFilename());
                product.setDescription("Fashion Description");

                this.productService.addProduct(product);
                Thread.sleep(1600);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return "redirect:/admin/dashboard";
    }

}
