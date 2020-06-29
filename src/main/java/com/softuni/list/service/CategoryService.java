package com.softuni.list.service;

import com.softuni.list.model.entity.CategoryName;
import com.softuni.list.model.service.CategoryServiceModel;
import com.softuni.list.model.view.CategoryViewModel;

import java.util.List;

public interface CategoryService {
    void initCategories();

    CategoryServiceModel getCategoryByName(CategoryName categoryName);

    List<CategoryViewModel> getAllCategories();
}
