package com.softuni.list.model.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(name = "username", nullable = false, unique = true)
    @Length(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String username;
    @Column(name = "password", nullable = false)
    @Length(min = 3, max = 20, message = "Password must be between 3 and 20 characters")
    private String password;
    @Column(name = "email", nullable = false, unique = true)
    @Email(message = "Must contains '@'")
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
