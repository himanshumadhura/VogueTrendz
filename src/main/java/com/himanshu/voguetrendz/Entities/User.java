package com.himanshu.voguetrendz.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank(message = "required")
    private String firstName;

    @NotBlank(message = "required")
    private String lastName;

    @Column(unique = true)
//    @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}", message = "Invalid Email")
    @NotBlank(message = "required")
    private String email;

    @Column(unique = true)
    @Size(min = 10, max = 10, message = "Invalid Number")
    @NotBlank(message = "required")
    private String phoneNumber;

//    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[.@$!%*?&])[A-Za-z0-9.@$!%*?&]{5,10}$", message = "Password must contain one uppercase, one lowercase, one number, one special character and between 5-10 characters !!!")
    @NotBlank(message = "required")
    private String password;

    private String role;

    public User() {
        super();
    }

    public User(int id, String firstName, String lastName, String email, String phoneNumber, String password, String role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.role = role;
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
                '}';
    }
}
