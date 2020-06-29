package com.softuni.list.service.impl;

import com.softuni.list.model.entity.Category;
import com.softuni.list.model.entity.CategoryName;
import com.softuni.list.model.service.CategoryServiceModel;
import com.softuni.list.model.view.CategoryViewModel;
import com.softuni.list.repository.CategoryRepository;
import com.softuni.list.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void initCategories() {
        if (this.categoryRepository.count() == 0) {
            Arrays.stream(CategoryName.values())
                    .map(categoryName -> new Category(categoryName,
                            String.format("Description for %s", categoryName.name().toLowerCase())))
                    .forEach(this.categoryRepository::saveAndFlush);
        }
    }

    @Override
    public CategoryServiceModel getCategoryByName(CategoryName categoryName) {
        return this.categoryRepository
                .findByName(categoryName)
                .map(category -> this.modelMapper.map(category, CategoryServiceModel.class))
                .orElse(null);
    }

    @Override
    public List<CategoryViewModel> getAllCategories() {
        return this.categoryRepository
                .findAll()
                .stream()
                .sorted(Comparator.comparingInt(f -> f.getName().ordinal()))
                .map(category -> {
                    CategoryViewModel categoryViewModel = this.modelMapper.map(category, CategoryViewModel.class);
                    categoryViewModel.setImageUrl(String.format("/img/%s.png", category.getName().name().toLowerCase()));
                    return categoryViewModel;
                }).collect(Collectors.toList());
    }
}
