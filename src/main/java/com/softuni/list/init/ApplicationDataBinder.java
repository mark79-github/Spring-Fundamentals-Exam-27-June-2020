package com.softuni.list.init;

import com.softuni.list.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationDataBinder implements CommandLineRunner {

    private final CategoryService categoryService;

    @Autowired
    public ApplicationDataBinder(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.categoryService.initCategories();
    }
}
