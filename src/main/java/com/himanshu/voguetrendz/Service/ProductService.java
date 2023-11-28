package com.himanshu.voguetrendz.Service;

import com.himanshu.voguetrendz.Entities.Product;
import com.himanshu.voguetrendz.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return this.productRepository.findAll();
    }

    public Product getProductById(int id){
        Product product = null;
        try{
            product = this.productRepository.findById(id);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return product;
    }

    public Product addProduct(Product data){
        return this.productRepository.save(data);
    }

    public void deleteProduct(int id){
        this.productRepository.deleteById(id);
    }

    public void updateProduct(Product data, int id){
        data.setId(id);
        this.productRepository.save(data);
    }

    public List<Product> getProductsByCategory(String category){
        List<Product> product = null;
        try{
            product = this.productRepository.getProductsByCategory(category);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return product;
    }

}
