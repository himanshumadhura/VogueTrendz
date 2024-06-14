package com.himanshu.voguetrendz.Entities;

import jakarta.validation.constraints.NotBlank;

public class LoginData {

    @NotBlank(message = "required")
    private String emailId;

    @NotBlank(message = "required")
    private String password;

    public LoginData() {
        super();
    }

    public LoginData(String emailId, String password) {
        this.emailId = emailId;
        this.password = password;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginData{" +
                "emailId='" + emailId + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
