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

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.softuni.list.constants.GlobalConstants.CATEGORY_DESCRIPTION_PATTERN;
import static com.softuni.list.constants.GlobalConstants.CATEGORY_IMAGE_FILEPATH_PATTERN;

@Component
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void initCategories() {
        if (this.categoryRepository.count() == 0) {
            Arrays.stream(CategoryName.values())
                    .map(categoryName -> new Category(categoryName,
                            String.format(CATEGORY_DESCRIPTION_PATTERN, categoryName.name().toLowerCase())))
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
                    categoryViewModel.setImageUrl(String.format(CATEGORY_IMAGE_FILEPATH_PATTERN, category.getName().name().toLowerCase()));
                    return categoryViewModel;
                }).collect(Collectors.toList());
    }
}
