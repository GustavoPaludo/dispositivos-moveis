package com.mobile.core.cart;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.core.cart.entity.Cart;
import com.mobile.core.cart.entity.CartBO;
import com.mobile.core.order.entity.CustomerOrder;
import com.mobile.core.order.entity.CustomerOrderBO;
import com.mobile.core.product.entity.Product;
import com.mobile.core.product.entity.ProductBO;
import com.mobile.core.product.model.ProductModel;
import com.mobile.core.user.entity.User;
import com.mobile.core.user.entity.UserBO;

@Service
public class CartServiceBean implements CartService {

    @Autowired
    CartBO cartBO;
    
    @Autowired
    ProductBO productBO;
    
    @Autowired
    CustomerOrderBO customerOrderBO;

    @Autowired
    UserBO userBO;
    
    @Override
    public void registerCart(List<ProductModel> productModel, Integer userId) throws Exception {

        List<Long> listIds = new ArrayList<Long>();
        Double totalValue = 0.0;
        
        for(ProductModel productModelB : productModel) {
        	listIds.add(productModelB.getId());
        	totalValue += productModelB.getPrice();
        }

        Map<Long, Integer> productQuantityMap = new HashMap<>();

        for (ProductModel productModelL : productModel) {
            Long productId = productModelL.getId();
            productQuantityMap.put(productId, productQuantityMap.getOrDefault(productId, 0) + 1);
        }

        List<Product> cartProducts = this.productBO.findByIdList(listIds);

        for (Product product : cartProducts) {
            if (product.getQuantity() < productQuantityMap.get(product.getId())) {
                throw new Exception("A quantidade de produtos no carrinho é maior que o estoque!");
            }
        }
        
        for (Product product : cartProducts) {
            product.setQuantity(product.getQuantity() - productQuantityMap.get(product.getId()));
            productBO.save(product);
        }
        
        User user = this.userBO.findById(userId);
        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setRegisterDate(new Date());
        customerOrder.setValue(totalValue);
        customerOrder.setUser(user);
        
        customerOrder = this.customerOrderBO.save(customerOrder);
        
        for (Product product : cartProducts) {
        	Cart cart = new Cart();
        	cart.setCustomerOrder(customerOrder);
        	cart.setProduct(product);
        	cart.setRegisterDate(new Date());
        	cart.setProductQuantity(productQuantityMap.get(product.getId()));
        	cartBO.save(cart);
        }
    }
}
