package com.himanshu.voguetrendz.Repository;

import com.himanshu.voguetrendz.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    public Product findById(int id);
    public List<Product> getProductsByCategory(String category);
}
