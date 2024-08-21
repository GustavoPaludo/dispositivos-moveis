package com.mobile.core.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.googlecode.ehcache.annotations.Cacheable;
import com.mobile.ApplicationProperties;
import com.mobile.core.product.entity.Product;
import com.mobile.core.product.entity.ProductBO;
import com.mobile.core.product.model.ProductModel;

@Service
public class ProductServiceBean implements ProductService{

	@Autowired
    ProductBO productBO;
	
	@Override
	@Cacheable(cacheName = ApplicationProperties.CACHE_FIVE_MINUTE)
    public List<ProductModel> getAllProducts() {
        List<Product> productList = this.productBO.findAll();
        List<ProductModel> productModelList = new ArrayList<>();
        ProductModel model = new ProductModel();
        productModelList = model.parser(productList);
        return productModelList;
    }
}
