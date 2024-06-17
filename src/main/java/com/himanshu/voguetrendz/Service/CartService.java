package com.himanshu.voguetrendz.Service;

import com.himanshu.voguetrendz.Entities.Product;
import com.himanshu.voguetrendz.Entities.User;
import com.himanshu.voguetrendz.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductService productService;

    public void addToCart(User user, Product product) {

        if (user != null && product != null) {
            user.getProducts().add(product);
            product.getUsers().add(user);

            userRepository.save(user);
        } else {
            System.out.println("User or Product not found");
        }
    }
}
