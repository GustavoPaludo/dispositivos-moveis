package com.udesc.projetofinal.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.udesc.projetofinal.models.UserModel;
import com.udesc.projetofinal.models.ProductModel;

public class OrderModel implements Serializable {
    private Long id;
    private Date registerDate;
    private Double value;
    private List<ProductModel> productList;
    private UserModel userModel;

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public List<ProductModel> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductModel> productList) {
        this.productList = productList;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}


