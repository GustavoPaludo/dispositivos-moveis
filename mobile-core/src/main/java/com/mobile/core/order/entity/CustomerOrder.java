package com.mobile.core.order.entity;

import java.util.Date;

import com.mobile.core.user.entity.User;

import javax.persistence.*;

@Entity
@Table(name = "CUSTOMER_ORDER")
public class CustomerOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "TOTAL_VALUE")
	private Double value;

	@Column(name = "REGISTER_DATE")
	private Date registerDate;
	
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;

	public CustomerOrder() {}

	public CustomerOrder(long id, Double value, Date registerDate, User user) {
		super();
		this.setId(id);
		this.setValue(value);
		this.setRegisterDate(registerDate);
		this.setUser(user);
	}

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}

	public Double getValue() {
		return value;
	}


	public void setValue(Double value) {
		this.value = value;
	}


	public Date getRegisterDate() {
		return registerDate;
	}


	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
