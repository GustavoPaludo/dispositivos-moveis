package com.mobile.web.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.mobile.core.cart.CartService;
import com.mobile.core.product.model.ProductModel;

@RestController
@RequestMapping("/cart-api")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/finish-purchase")
	public ResponseEntity<String> registerUser(@RequestBody String requestBody, @RequestParam(value = "userId", required = false) Integer userId) throws Exception {
	    if (requestBody == null || requestBody.isBlank()) {
	        throw new Exception("Corpo da requisição não pode estar vazio!");
	    }

	    List<ProductModel> productList;
        try {
            productList = new Gson().fromJson(requestBody, new TypeToken<List<ProductModel>>() {}.getType());
        } catch (JsonSyntaxException e) {
            return new ResponseEntity<>("Erro ao converter a lista de produtos", HttpStatus.BAD_REQUEST);
        }
        
	    if (productList == null || productList.isEmpty()) {
	        throw new Exception("UserModel inválido!");
	    }

	    try {
            this.cartService.registerCart(productList, userId);
            return new ResponseEntity<>("OK", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
}
