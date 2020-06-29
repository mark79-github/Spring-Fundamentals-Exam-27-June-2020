package com.softuni.list.service.impl;

import com.softuni.list.model.entity.Product;
import com.softuni.list.model.service.CategoryServiceModel;
import com.softuni.list.model.service.ProductServiceModel;
import com.softuni.list.repository.ProductRepository;
import com.softuni.list.service.CategoryService;
import com.softuni.list.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
    }

    @Override
    public void addProduct(ProductServiceModel productServiceModel) {
        CategoryServiceModel categoryServiceModel = this.categoryService.getCategoryByName(productServiceModel.getCategory().getName());
        productServiceModel.setCategory(categoryServiceModel);
        this.productRepository.saveAndFlush(this.modelMapper.map(productServiceModel, Product.class));
    }

    @Override
    public void buyProductById(String id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public void buyAllProducts() {
        this.productRepository.deleteAll();
    }

    @Override
    public BigDecimal getProductsTotalPrice() {
        return this.productRepository.getProductsTotalSum().orElse(BigDecimal.ZERO);
    }

}
