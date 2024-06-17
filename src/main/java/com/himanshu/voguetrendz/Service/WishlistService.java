package com.himanshu.voguetrendz.Service;

import com.himanshu.voguetrendz.Entities.Product;
import com.himanshu.voguetrendz.Entities.User;
import com.himanshu.voguetrendz.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishlistService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductService productService;

    public void addToWishlist(User user, Product product) {

        if (user != null && product != null) {
            user.getWishlist().add(product);
            product.getWishlistUsers().add(user);

            userRepository.save(user);
        } else {
            System.out.println("User or Product not found");
        }
    }
}
