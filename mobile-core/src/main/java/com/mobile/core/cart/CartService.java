package com.mobile.core.cart;

import java.util.List;

import com.mobile.core.product.model.ProductModel;

public interface CartService {

    public void registerCart(List<ProductModel> productList, Integer userId) throws Exception;
}
