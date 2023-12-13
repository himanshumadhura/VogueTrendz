package com.himanshu.voguetrendz.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    private String category;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private String price;

    @NotNull
    private String image;

    @NotNull
    private String type;

    @NotNull
    private String color;

    @NotNull
    private String material;

    @NotNull
    private String brand;

    @ManyToMany(mappedBy = "products")
    @JsonBackReference
    private List<User> users;

    public Product() {
    }

    public Product(int id, String category, String name, String description, String price, String image, String type, String color, String material, String brand, List<User> users) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        this.type = type;
        this.color = color;
        this.material = material;
        this.brand = brand;
        this.users = users;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", image='" + image + '\'' +
                ", type='" + type + '\'' +
                ", color='" + color + '\'' +
                ", material='" + material + '\'' +
                ", brand='" + brand + '\'' +
                ", users=" + users +
                '}';
    }
}
