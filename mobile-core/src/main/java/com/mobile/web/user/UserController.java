package com.mobile.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.mobile.core.user.UserService;
import com.mobile.core.user.model.UserModel;

@RestController
@RequestMapping("/user-api")
public class UserController {

    @Autowired
    private UserService userService;

	@GetMapping("/get-user")
	public ResponseEntity<UserModel> getUser(
			@RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "password", required = true) String password) throws Exception {

		if (email == null || email.isBlank()) {
			throw new Exception("Email não pode estar vazio!");
		} else if (password == null || password.isBlank()) {
			throw new Exception("A senha não pode estar vazia!");
		}

		UserModel user = this.userService.getUserByLogin(email, password);
		return new ResponseEntity<UserModel>(user, HttpStatus.OK);
	}

	@PostMapping("/register-user")
	public ResponseEntity<UserModel> registerUser(@RequestBody String requestBody) throws Exception {
	    if (requestBody == null || requestBody.isBlank()) {
	        throw new Exception("Corpo da requisição não pode estar vazio!");
	    }

	    Gson gson = new Gson();
	    UserModel userModel = gson.fromJson(requestBody, UserModel.class);

	    if (userModel == null) {
	        throw new Exception("UserModel inválido!");
	    }

	    userModel = this.userService.registerUser(userModel);
		return new ResponseEntity<UserModel>(userModel, HttpStatus.OK);
	}
}
