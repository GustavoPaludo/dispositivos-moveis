package com.mobile.core.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.core.cart.entity.Cart;
import com.mobile.core.cart.entity.CartBO;
import com.mobile.core.order.entity.CustomerOrder;
import com.mobile.core.order.entity.CustomerOrderBO;
import com.mobile.core.order.model.OrderModel;
import com.mobile.core.product.entity.Product;
import com.mobile.core.product.model.ProductModel;
import com.mobile.core.user.model.UserModel;

@Service
public class OrderServiceBean implements OrderService {

    @Autowired
    CustomerOrderBO customerOrderBO;
    
    @Autowired
    CartBO cartBO;

    @Override
    public List<OrderModel> getOrdersByUserId(Long id) {
        List<OrderModel> orderModelList = new ArrayList<>();

        List<CustomerOrder> customerOrderList = this.customerOrderBO.findByUserId(id);

        for (CustomerOrder customerOrder : customerOrderList) {
            OrderModel orderModel = new OrderModel();
            orderModel.setId(customerOrder.getId());
            orderModel.setRegisterDate(customerOrder.getRegisterDate());
            orderModel.setValue(customerOrder.getValue());

            UserModel userModel = new UserModel();
            userModel = userModel.parser(customerOrder.getUser());
            orderModel.setUserModel(userModel);

            List<Cart> cartList = this.cartBO.findByOrderId(customerOrder.getId());
            Map<Long, Integer> productQuantityMap = new HashMap<>();
            Map<Long, Double> productTotalValueMap = new HashMap<>();

            for (Cart cart : cartList) {
                Product product = cart.getProduct();
                Long productId = product.getId();
                int quantity = cart.getProductQuantity();
                double productTotalValue = quantity * product.getPrice();

                productQuantityMap.put(productId, productQuantityMap.getOrDefault(productId, 0) + quantity);
                productTotalValueMap.put(productId, productTotalValueMap.getOrDefault(productId, 0.0) + productTotalValue);
            }

            List<ProductModel> productModelList = new ArrayList<>();

            for (Cart cart : cartList) {
                Product product = cart.getProduct();
                Long productId = product.getId();

                if (productQuantityMap.containsKey(productId)) {
                    ProductModel productModel = new ProductModel();
                    productModel.setId(product.getId());
                    productModel.setName(product.getName());
                    productModel.setDescription(product.getDescription());
                    productModel.setPrice(productTotalValueMap.get(productId));
                    productModel.setQuantity(productQuantityMap.get(productId));
                    productModelList.add(productModel);
                    productQuantityMap.remove(productId);
                }
            }

            orderModel.setProductList(productModelList);
            orderModelList.add(orderModel);
        }

        return orderModelList;
    }
}
