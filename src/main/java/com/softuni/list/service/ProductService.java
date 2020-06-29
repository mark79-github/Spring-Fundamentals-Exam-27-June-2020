package com.softuni.list.service;

import com.softuni.list.model.service.ProductServiceModel;

import java.math.BigDecimal;

public interface ProductService {
    void addProduct(ProductServiceModel productServiceModel);

    void buyProductById(String id);

    void buyAllProducts();

    BigDecimal getProductsTotalPrice();
}
