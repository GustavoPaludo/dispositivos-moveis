package com.mobile.core.user;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.core.user.entity.User;
import com.mobile.core.user.entity.UserBO;
import com.mobile.core.user.model.UserModel;

@Service
public class UserServiceBean implements UserService {

    @Autowired
    UserBO userBO;

    @Override
    public UserModel getUserByLogin(String email, String password) {
        User user = this.userBO.findByEmailAndPassword(email, password);
        UserModel userModel = new UserModel();
        userModel.parser(user);
    
        return userModel;
    }

    @Override
    public UserModel registerUser(UserModel userModel) throws Exception {

    	User userVerify = this.userBO.findByEmailAndPassword(userModel.getEmail(), userModel.getPassword());
    	
    	if(userVerify != null) {
    		throw new Exception("Usuário com o mesmo email já cadastrado !");
    	}

    	User user = new User(userModel.getName(), userModel.getLastName(), userModel.getEmail(), userModel.getPassword(), new Date());
        this.userBO.save(user);

        userModel = userModel.parser(user);
        return userModel;
    }

    @Override
    public User getUserById(Integer id) {
        User user = this.userBO.findById(id);
        return user;
    }
}
