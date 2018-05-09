package com.gadaels.spring.rest_api.models;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product{
    private String _productName;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY )
    private int _productId;

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