package com.himanshu.voguetrendz.Entities;

import jakarta.persistence.*;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int addressId;
    private String houseNo;
    private String area;
    private String city;
    private String state;
    private int pinCode;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    public Address() {
        super();
    }

    public Address(int addressId, String houseNo, String area, String city, String state, int pinCode, User user) {
        this.addressId = addressId;
        this.houseNo = houseNo;
        this.area = area;
        this.city = city;
        this.state = state;
        this.pinCode = pinCode;
        this.user = user;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", houseNo='" + houseNo + '\'' +
                ", area='" + area + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", pinCode=" + pinCode +
                ", user=" + user +
                '}';
    }
}
