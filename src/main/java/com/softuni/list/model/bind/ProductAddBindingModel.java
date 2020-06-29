package com.softuni.list.model.bind;

import com.softuni.list.model.entity.CategoryName;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductAddBindingModel {

    @Length(min = 3, max = 20, message = "Name must be between 3 and 20 characters")
    private String name;
    @Length(min = 5, message = "Description must be minimum 5 characters")
    private String description;
    @NotNull(message = "Price must be a positive number")
    @Positive(message = "Price must be a positive number")
    private BigDecimal price;
    @NotNull(message = "Date and time can not be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @FutureOrPresent(message = "The date and time cannot be in the past")
    private LocalDateTime neededBefore;
    @NotNull(message = "Category must not be null")
    private CategoryName category;

    public ProductAddBindingModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getNeededBefore() {
        return neededBefore;
    }

    public void setNeededBefore(LocalDateTime neededBefore) {
        this.neededBefore = neededBefore;
    }

    public CategoryName getCategory() {
        return category;
    }

    public void setCategory(CategoryName category) {
        this.category = category;
    }
}
