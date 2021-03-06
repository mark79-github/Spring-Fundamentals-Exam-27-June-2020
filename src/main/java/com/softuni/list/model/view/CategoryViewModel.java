package com.softuni.list.model.view;

import java.util.List;

public class CategoryViewModel {

    private String name;
    private List<ProductViewModel> products;
    private String imageUrl;

    public CategoryViewModel() {
    }

    public String getName() {
        return this.name.equals("other".toUpperCase()) ?
                this.name.substring(0, 1).toUpperCase() + this.name.substring(1).toLowerCase() :
                this.name.substring(0, 1).toUpperCase() + this.name.substring(1).toLowerCase() + "s";
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductViewModel> getProducts() {
        return products;
    }

    public void setProducts(List<ProductViewModel> products) {
        this.products = products;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
