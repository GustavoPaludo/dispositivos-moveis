package com.mobile.web.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.googlecode.ehcache.annotations.Cacheable;
import com.mobile.ApplicationProperties;
import com.mobile.core.product.ProductService;
import com.mobile.core.product.model.ProductModel;

@RestController
@RequestMapping("/product-api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Cacheable(cacheName = ApplicationProperties.CACHE_FIVE_MINUTE)
	@GetMapping("/get-products")
	public ResponseEntity<List<ProductModel>> getProducts() throws Exception {

		List<ProductModel> productList = this.productService.getAllProducts();
		return new ResponseEntity<List<ProductModel>>(productList, HttpStatus.OK);
	}
}
