package com.mobile.core.product.model;

import java.util.ArrayList;
import java.util.List;

import com.mobile.core.product.entity.Product;

public class ProductModel {

	private Long id;
	private String name;
	private String description;
	private Double price;
	private Integer quantity;

	public ProductModel() {}

	public List<ProductModel> parser(List<Product> productList) {
	    if (productList.isEmpty()) {
	        return new ArrayList<>();
	    }

	    List<ProductModel> productModelList = new ArrayList<>();

	    for (Product product : productList) {
	        ProductModel model = new ProductModel();
	        model.setId(product.getId());
	        model.setDescription(product.getDescription());
	        model.setName(product.getName());
	        model.setPrice(product.getPrice());
	        model.setQuantity(product.getQuantity());
	        productModelList.add(model);
	    }

	    return productModelList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
