package com.mobile.core.cart.entity;

import java.util.Date;

import com.mobile.core.order.entity.CustomerOrder;
import com.mobile.core.product.entity.Product;

import javax.persistence.*;

@Entity
@Table(name = "CART")
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@ManyToOne
	@JoinColumn(name = "PRODUCT_ID")
	private Product product;

	@ManyToOne
	@JoinColumn(name = "ORDER_ID")
	private CustomerOrder customerOrder;

	@Column(name = "REGISTER_DATE")
	private Date registerDate;

	@Column(name = "PRODUCT_QUANTITY")
	private Integer productQuantity;
	
	public Cart() {}

	public Cart(long id, Product product, CustomerOrder customerOrder, Date registerDate, Integer productQuantity) {
		super();
		this.id = id;
		this.product = product;
		this.customerOrder = customerOrder;
		this.registerDate = registerDate;
		this.productQuantity = productQuantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public CustomerOrder getCustomerOrder() {
		return customerOrder;
	}

	public void setCustomerOrder(CustomerOrder customerOrder) {
		this.customerOrder = customerOrder;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public Integer getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(Integer productQuantity) {
		this.productQuantity = productQuantity;
	}
}
