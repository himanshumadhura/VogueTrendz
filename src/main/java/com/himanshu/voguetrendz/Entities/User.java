package com.himanshu.voguetrendz.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank(message = "required")
    @NotNull
    private String firstName;

    private String lastName;

    @Column(unique = true)
    @NotBlank(message = "required")
    @NotNull
    private String email;

    @Column(unique = true)
    @Size(min = 10, max = 10, message = "Invalid Number")
    @NotBlank(message = "required")
    @NotNull
    private String phoneNumber;

    @NotBlank(message = "required")
    @NotNull
    private String password;

    private String role;

    private String profileImg;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="cart")
    @JsonManagedReference
    @NotNull
    private List<Product> products;

    @OneToOne(mappedBy = "user")
    private Address address;

    public User() {
        super();
    }

    public User(int id, String firstName, String lastName, String email, String phoneNumber, String password, String role, String profileImg, List<Product> products, Address address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.role = role;
        this.profileImg = profileImg;
        this.products = products;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", profileImg='" + profileImg + '\'' +
                ", products=" + products +
                ", address=" + address +
                '}';
    }
}
