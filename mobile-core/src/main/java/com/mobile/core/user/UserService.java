package com.mobile.core.user;

import com.mobile.core.user.entity.User;
import com.mobile.core.user.model.UserModel;

public interface UserService {
    public UserModel getUserByLogin(String email, String password);

    public UserModel registerUser(UserModel userModel) throws Exception;

    public User getUserById(Integer id);
}
