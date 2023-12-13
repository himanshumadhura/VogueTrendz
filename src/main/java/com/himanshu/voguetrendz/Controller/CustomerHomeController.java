package com.himanshu.voguetrendz.Controller;

import com.himanshu.voguetrendz.Entities.Address;
import com.himanshu.voguetrendz.Entities.Product;
import com.himanshu.voguetrendz.Entities.User;
import com.himanshu.voguetrendz.Repository.AddressRepository;
import com.himanshu.voguetrendz.Repository.UserRepository;
import com.himanshu.voguetrendz.Service.ProductService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class CustomerHomeController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private AddressRepository addressRepository;

    @ModelAttribute
    public void commonData(Model model, Principal principal){
        User user = userRepository.getUserByUsername(principal.getName());

        model.addAttribute("user", user);
        model.addAttribute("address", user.getAddress());

    }

    @GetMapping("/dashboard")
    public String dashboard(Model model){
        model.addAttribute("title","VogueTendz");
        model.addAttribute("pg","home");
        return "Customer/dashboard";
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

        model.addAttribute("pg", "collection");
        model.addAttribute("title", "Collections");
        return "Customer/customer_allProducts";
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

        model.addAttribute("pg", "collection");
        model.addAttribute("title", "Collections-"+category);
        return "Customer/customer_products";
    }

    @GetMapping("/productInfo/{productId}")
    public String productDetail(@PathVariable("productId")int productId, Model model){
        model.addAttribute("product", this.productService.getProductById(productId));
        model.addAttribute("pg", "collection");
        model.addAttribute("title", "Collection - "+this.productService.getProductById(productId).getName());
        return "Customer/customer_productDetail";
    }

    @GetMapping("/addToCart/{productId}")
    public String addToCart(@PathVariable("productId")int productId, Model model, Principal principal){
        try{
            Thread.sleep(1550);
            System.out.println("Added to cart");
            User user = this.userRepository.getUserByUsername(principal.getName());
            Product product = this.productService.getProductById(productId);
            List<Product> productList = new ArrayList<Product>();
            productList.add(product);
            user.setProducts(productList);
            this.userRepository.save(user);
            model.addAttribute("product", this.productService.getProductById(productId));
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return "redirect:/user/productInfo/"+productId;
    }

    @GetMapping("/cart")
    public String cart(Model model){
        model.addAttribute("title", "Cart");
        model.addAttribute("pg", "cart");
        model.addAttribute("message", "No Product Found");
        return "Customer/customer_cart";
    }

    @GetMapping("/addAddress")
    public String addAddress(Model model){
        model.addAttribute("title", "Add Address");
        model.addAttribute("pg", "login");
        return "Customer/customer_addAddress";
    }

    @PostMapping("/addAddressProcess")
    public String addAddressProcess(@RequestParam("houseNo")String houseNo, @RequestParam("area")String area, @RequestParam("city")String city, @RequestParam("pinCode")int pinCode, @RequestParam("state")String state, Principal principal){
        try{
            Thread.sleep(1550);
            Address address = new Address();
            address.setHouseNo(houseNo);
            address.setArea(area);
            address.setCity(city);
            address.setPinCode(pinCode);
            address.setState(state);
            address.setUser(this.userRepository.getUserByUsername(principal.getName()));
            this.addressRepository.save(address);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return "redirect:/user/profile";
    }

    @GetMapping("/profile")
    public String userProfile(Model model){
        model.addAttribute("title", "Profile");
        model.addAttribute("pg", "login");
        return "Customer/customer_profile";
    }

    @PostMapping("/userProfileUpdate")
    public String userProfileUpdate(Model model){
        model.addAttribute("title", "Profile");
        model.addAttribute("pg", "login");
        return "Customer/customer_profileUpdate";
    }

    @PostMapping("/profileUpdateProcess")
    public String profileUpdateProcess(@RequestParam("profilePic") MultipartFile profilePic, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("email") String email, @RequestParam("phoneNumber") String phoneNumber, @RequestParam("houseNo")String houseNo, @RequestParam("area")String area, @RequestParam("city")String city, @RequestParam("pinCode")int pinCode, @RequestParam("state")String state, User user, Principal principal){
        try{
            user.setId(user.getId());
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setPhoneNumber(phoneNumber);
            user.getAddress().setHouseNo(houseNo);
            user.getAddress().setArea(area);
            user.getAddress().setCity(city);
            user.getAddress().setPinCode(pinCode);
            user.getAddress().setState(state);
            user.getAddress().setUser(this.userRepository.getUserByUsername(principal.getName()));

            if(profilePic.isEmpty()){
                System.out.println("File is empty");
            }else {
                File file1 = new ClassPathResource("static/image").getFile();
                Path path = Paths.get(file1.getAbsolutePath() + File.separator + profilePic.getOriginalFilename());
                Files.copy(profilePic.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                user.setProfileImg(profilePic.getOriginalFilename());
            }
            this.userRepository.save(user);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return "Customer/customer_profile";
    }

    @PostMapping("/create_order")
    @ResponseBody
    public String createOrder(@RequestBody Map<String, Object> data)throws RazorpayException {
//        System.out.println(data);
        int amount = Integer.parseInt(data.get("amount").toString());
        RazorpayClient client = new RazorpayClient("rzp_test_EfSo5IaAFzDMqi", "iLESWdk9TdEZtnk8n4qJpKy9");
        JSONObject ob = new JSONObject();
        ob.put("amount", amount*100);
        ob.put("currency", "INR");
        ob.put("receipt", "txn_180205");

        Order order = client.orders.create(ob);
        System.out.println(order);
        return order.toString();
    }
}
