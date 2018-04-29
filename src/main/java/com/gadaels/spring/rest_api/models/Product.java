package com.gadaels.spring.rest_api.models;


public class Product{
    String _productName;
    int _productId;
    public Product(int productId, String productName) {
        this._productId=productId;
        this._productName=productName;
    }

    public Product(){
        
    }
    public void setProductName(String value){
         this._productName=value;
    }

    public String getProductName(){
        return this._productName;
    }
    public void setProductId(int value){
        this._productId=value;
    }

    public int getProductId(){
        return this._productId;
    }


}