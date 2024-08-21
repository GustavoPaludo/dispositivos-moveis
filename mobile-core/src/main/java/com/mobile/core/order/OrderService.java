package com.mobile.core.order;

import java.util.List;

import com.mobile.core.order.model.OrderModel;

public interface OrderService {
    public List<OrderModel> getOrdersByUserId(Long userId);
}
