package com.example.eproject.project.user;

public class Products {
    private String ProductName, ProductPrice, ProductDesc,selectedProduct, ImageUrl, By;

    public Products(){
        //Empty
    }

    public Products( String ProductName, String ProductPrice, String ProductDesc,String selectedProduct, String ImageUrl, String By){

        this.ProductName = ProductName;
        this.ProductPrice = ProductPrice;
        this.ProductDesc = ProductDesc;
        this.selectedProduct = selectedProduct;
        this.ImageUrl = ImageUrl;
        this.By = By;
    }


    public String getProductName(){ return ProductName;}

    public void setProductName(String ProductName){ this.ProductName = ProductName;}

    public String getProductPrice(){ return ProductPrice;}

    public void setProductPrice(String ProductPrice){ this.ProductPrice = ProductPrice;}

    public String getProductDesc(){ return ProductDesc;}

    public void setProductDesc(String ProductDesc){ this.ProductDesc = ProductDesc;}

    public String getSelectedProduct(){ return selectedProduct;}

    public void setSelectedProduct(String SelectedProduct){ this.selectedProduct = selectedProduct;}

    public String getImageUrl(){ return ImageUrl;}

    public void setImageUrl(String ImageUrl){ this.ImageUrl = ImageUrl;}

    public String getBy() { return By;}

    public void setBy(String by) { By = by;}
}
