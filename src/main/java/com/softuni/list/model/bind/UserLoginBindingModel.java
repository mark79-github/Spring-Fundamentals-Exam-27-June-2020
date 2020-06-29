package com.softuni.list.model.bind;

import org.hibernate.validator.constraints.Length;

import static com.softuni.list.constants.GlobalConstants.PASSWORD_RESTRICTION_MESSAGE;
import static com.softuni.list.constants.GlobalConstants.USERNAME_RESTRICTION_MESSAGE;

public class UserLoginBindingModel {

    @Length(min = 3, max = 20, message = USERNAME_RESTRICTION_MESSAGE)
    private String username;
    @Length(min = 3, max = 20, message = PASSWORD_RESTRICTION_MESSAGE)
    private String password;

    public UserLoginBindingModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
