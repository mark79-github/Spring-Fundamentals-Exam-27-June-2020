package com.softuni.list.model.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import static com.softuni.list.constants.GlobalConstants.*;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(nullable = false, unique = true)
    @Length(min = 3, max = 20, message = USERNAME_RESTRICTION_MESSAGE)
    private String username;
    @Column(nullable = false)
    @Length(min = 3, max = 20, message = PASSWORD_RESTRICTION_MESSAGE)
    private String password;
    @Column(nullable = false, unique = true)
    @Email(message = EMAIL_RESTRICTION_MESSAGE)
    private String email;

    public User() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
