package com.example.eproject.project.user;

public class Seller {
    private String ShopName, Username, Email, PhoneNo, Address, Pass;

    public Seller(){

    }

    public Seller(String ShopName, String Username, String Email, String PhoneNo, String Address, String Pass){
        this.ShopName = ShopName;
        this.Username = Username;
        this.Email = Email;
        this.PhoneNo = PhoneNo;
        this.Address = Address;
        this.Pass = Pass;
    }

    public String getShopName(){
        return ShopName;
    }
    public void setShopName(String ShopName){
        this.ShopName = ShopName;
    }

    public String getUsername(){
        return Username;
    }
    public void setUsername(String Username){
        this.Username = Username;
    }

    public String getEmail(){
        return Email;
    }
    public void setEmail(String Email){
        this.Email = Email;
    }

    public String getPhoneNo(){
        return PhoneNo;
    }
    public void setPhoneNo(String PhoneNo){
        this.PhoneNo = PhoneNo;
    }

    public String getAddress(){
        return Address;
    }
    public void setAddress(String Address){
        this.Address = Address;
    }

    public String getPass(){
        return Pass;
    }
    public void setPass(String Pass){
        this.Pass = Pass;
    }
}
