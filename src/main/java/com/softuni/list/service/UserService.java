package com.softuni.list.service;

import com.softuni.list.model.service.UserServiceModel;

public interface UserService {
    void registerUser(UserServiceModel userServiceModel);

    UserServiceModel getUserByName(String username);

    UserServiceModel getUserByEmail(String email);
}
