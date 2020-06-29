package com.softuni.list.model.bind;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import static com.softuni.list.constant.GlobalConstants.*;

public class UserRegisterBindingModel {

    @Length(min = 3, max = 20, message = USERNAME_RESTRICTION_MESSAGE)
    private String username;
    @Length(min = 3, max = 20, message = PASSWORD_RESTRICTION_MESSAGE)
    private String password;
    private String confirmPassword;
    @NotEmpty(message = EMAIL_NOT_EMPTY_MESSAGE)
    @Email(message = EMAIL_RESTRICTION_MESSAGE)
    private String email;

    public UserRegisterBindingModel() {
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
