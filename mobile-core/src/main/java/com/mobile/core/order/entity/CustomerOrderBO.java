package com.mobile.core.order.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
public interface CustomerOrderBO extends JpaRepository<CustomerOrder, Long> {

	public CustomerOrder findById(Integer id);

	@Query("SELECT p FROM CustomerOrder p WHERE p.user.id = :customerId")
    List<CustomerOrder> findByUserId(@Param("customerId") Long customerId);
}
