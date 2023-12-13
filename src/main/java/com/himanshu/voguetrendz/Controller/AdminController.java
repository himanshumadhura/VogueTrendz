package com.himanshu.voguetrendz.Controller;

import com.himanshu.voguetrendz.Entities.Product;
import com.himanshu.voguetrendz.Entities.User;
import com.himanshu.voguetrendz.Helper.Message;
import com.himanshu.voguetrendz.Repository.UserRepository;
import com.himanshu.voguetrendz.Service.ProductService;
import jakarta.servlet.http.HttpSession;
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
        model.addAttribute("product", new Product());
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

        if(product.getDescription()==null){
            product.setDescription("Fashion Description");
        }

        try{
            if(file.isEmpty()){
                System.out.println("File is empty");
            }else{
                File file1 = new ClassPathResource("static/image").getFile();
                Path path = Paths.get(file1.getAbsolutePath()+File.separator+file.getOriginalFilename());
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                product.setImage(file.getOriginalFilename());

                this.productService.addProduct(product);
                Thread.sleep(1600);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return "redirect:/admin/addProduct";
    }

    @GetMapping("/searchProduct")
    public String searchProduct(Model model){
        model.addAttribute("title", "Admin Dashboard - Search Product");
        model.addAttribute("pg", "update/deleteProduct");
        return "Admin/admin_searchProduct";
    }

    @PostMapping("/detailProduct")
    public String detailProduct(@RequestParam("productId1")int productId, Model model, HttpSession session){
        Product product = this.productService.getProductById(productId);
        if(product==null){
            session.setAttribute("message", new Message("No Product Found", "alert-danger"));
            model.addAttribute("title", "Admin Dashboard - Details of Product");
            model.addAttribute("pg", "update/deleteProduct");
            return "redirect:/admin/searchProduct";
        }
        model.addAttribute("result", product);
        model.addAttribute("title", "Admin Dashboard - Details of Product");
        model.addAttribute("pg", "update/deleteProduct");
        return "Admin/admin_detailProduct";
    }

    @PostMapping("/updateProduct/{productId}")
    public String updateProduct(@PathVariable("productId")int productId,Model model){
        Product product = this.productService.getProductById(productId);
        model.addAttribute("result", product);
        model.addAttribute("title", "Admin Dashboard - Update Product");
        model.addAttribute("pg", "update/deleteProduct");
        return "Admin/admin_updateProduct";
    }

    @PostMapping("/updateProductProcess")
    public String updateProduct(@ModelAttribute("product")Product product, @RequestParam("productName")String productName, @RequestParam("imageUrl") MultipartFile file, @RequestParam("productId")int productId){
        Product product1 = this.productService.getProductById(productId);
        product1.setId(productId);
        product1.setName(productName);
        product1.setPrice(product.getPrice());
        product1.setCategory(product.getCategory());
        product1.setBrand(product.getBrand());
        product1.setColor(product.getColor());
        product1.setMaterial(product.getMaterial());
        product1.setType(product.getType());

        if(product1.getDescription()==null){
            product1.setDescription("Fashion Description");
        }else{
            product1.setDescription(product.getDescription());
        }

        try{
            if(file.isEmpty()){
                System.out.println("File is empty");
            }else{
                File file1 = new ClassPathResource("static/image").getFile();
                Path path = Paths.get(file1.getAbsolutePath()+File.separator+file.getOriginalFilename());
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                product1.setImage(file.getOriginalFilename());

                this.productService.addProduct(product1);
                Thread.sleep(1600);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return "redirect:/admin/searchProduct";
    }

    @GetMapping("/deleteProductProcess/{productId}")
    public String deleteProduct(@PathVariable("productId")int productId){
        try {
            Thread.sleep(1600);
            this.productService.deleteProduct(productId);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return "redirect:/admin/searchProduct";
    }

    @GetMapping("/profile")
    public String adminProfile(Model model){
        model.addAttribute("title", "Profile");
        model.addAttribute("pg", "login");
        return "Admin/admin_profile";
    }

}
