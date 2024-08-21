package com.mobile.web.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mobile.core.order.OrderService;
import com.mobile.core.order.model.OrderModel;

@RestController
@RequestMapping("/order-api")
public class OrderController {

    @Autowired
    private OrderService orderService;

	@GetMapping("/get-order-by-user")
	public ResponseEntity<List<OrderModel>> getOrderList(
			@RequestParam(value = "userId", required = true) Long userId) throws Exception {

		if (userId == null) {
			throw new Exception("Id não pode estar vazio!");
		}

		List<OrderModel> orders = this.orderService.getOrdersByUserId(userId);
		return new ResponseEntity<List<OrderModel>>(orders, HttpStatus.OK);
	}
}
