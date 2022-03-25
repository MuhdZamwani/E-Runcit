package com.example.eproject.project.user;

public class Order {
    private String By, CusName, CusEmail, CusPhoneNo, CusPoscode, CusAddress, CusCity, Status, ImageUrl, ProductName, UID, ShopName, ShopAdd, ShopPhoneNo;
    private double TotalPrice;
    private int Quantity;

    public Order(){
        //
    }

    public Order(String by, String cusName, String cusEmail, String cusPhoneNo,
                 String cusPoscode, String cusAddress, String cusCity, String status,
                 String imageUrl, String productName, double totalPrice, int quantity, String UID,
                 String ShopName, String ShopAdd, String ShopPhoneNo) {

        this.By = by;
        this.CusName = cusName;
        this.CusEmail = cusEmail;
        this.CusPhoneNo = cusPhoneNo;
        this.CusPoscode = cusPoscode;
        this.CusAddress = cusAddress;
        this.CusCity = cusCity;
        this.Status = status;
        this.ImageUrl = imageUrl;
        this.ProductName = productName;
        this.TotalPrice = totalPrice;
        this.Quantity = quantity;
        this.UID = UID;
        this.ShopName = ShopName;
        this.ShopAdd = ShopAdd;
        this.ShopPhoneNo = ShopPhoneNo;
    }

    public String getBy() {
        return By;
    }

    public void setBy(String by) {
        By = by;
    }

    public String getCusName() {
        return CusName;
    }

    public void setCusName(String cusName) {
        CusName = cusName;
    }

    public String getCusEmail() {
        return CusEmail;
    }

    public void setCusEmail(String cusEmail) {
        CusEmail = cusEmail;
    }

    public String getCusPhoneNo() {
        return CusPhoneNo;
    }

    public void setCusPhoneNo(String cusPhoneNo) {
        CusPhoneNo = cusPhoneNo;
    }

    public String getCusPoscode() {
        return CusPoscode;
    }

    public void setCusPoscode(String cusPoscode) {
        CusPoscode = cusPoscode;
    }

    public String getCusAddress() {
        return CusAddress;
    }

    public void setCusAddress(String cusAddress) {
        CusAddress = cusAddress;
    }

    public String getCusCity() {
        return CusCity;
    }

    public void setCusCity(String cusCity) {
        CusCity = cusCity;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public double getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        TotalPrice = totalPrice;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getShopName() {
        return ShopName;
    }

    public void setShopName(String shopName) {
        ShopName = shopName;
    }

    public String getShopAdd() {
        return ShopAdd;
    }

    public void setShopAdd(String shopAdd) {
        ShopAdd = shopAdd;
    }

    public String getShopPhoneNo() {
        return ShopPhoneNo;
    }

    public void setShopPhoneNo(String shopPhoneNo) {
        ShopPhoneNo = shopPhoneNo;
    }
}
