package com.softuni.list.model.service;

import com.softuni.list.model.entity.CategoryName;

import java.util.List;

public class CategoryServiceModel extends BaseServiceModel {

    private CategoryName name;
    private String description;
    private List<ProductServiceModel> products;

    public CategoryServiceModel() {
    }

    public CategoryName getName() {
        return name;
    }

    public void setName(CategoryName name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ProductServiceModel> getProducts() {
        return products;
    }

    public void setProducts(List<ProductServiceModel> products) {
        this.products = products;
    }
}
