package com.mobile.core.cart.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
public interface CartBO extends JpaRepository<Cart, Long> {

	public Cart findById(Integer id);

	@Query("SELECT p FROM Cart p WHERE p.customerOrder.id = :customerOrderId")
    public List<Cart> findByOrderId(@Param("customerOrderId") Long customerOrderId);
}
