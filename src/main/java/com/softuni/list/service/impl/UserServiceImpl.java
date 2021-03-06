package com.softuni.list.service.impl;

import com.softuni.list.model.entity.User;
import com.softuni.list.model.service.UserServiceModel;
import com.softuni.list.repository.UserRepository;
import com.softuni.list.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final Argon2PasswordEncoder argon2PasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, Argon2PasswordEncoder argon2PasswordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.argon2PasswordEncoder = argon2PasswordEncoder;
    }

    @Override
    public void registerUser(UserServiceModel userServiceModel) {
        User user = this.modelMapper.map(userServiceModel, User.class);
        user.setPassword(this.argon2PasswordEncoder.encode(userServiceModel.getPassword()));
        this.userRepository.saveAndFlush(user);
    }

    @Override
    public UserServiceModel getUserByName(String username) {
        return this.userRepository
                .findByUsername(username)
                .map(user -> this.modelMapper.map(user, UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public UserServiceModel getUserByEmail(String email) {
        return this.userRepository
                .findByEmail(email)
                .map(user -> this.modelMapper.map(user, UserServiceModel.class))
                .orElse(null);
    }
}
