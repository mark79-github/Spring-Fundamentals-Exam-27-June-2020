package com.softuni.list.model.bind;

import com.softuni.list.model.entity.CategoryName;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.softuni.list.constants.GlobalConstants.*;

public class ProductAddBindingModel {

    @Length(min = 3, max = 20, message = PRODUCT_NAME_RESTRICTION_MESSAGE)
    private String name;
    @Length(min = 5, message = DESCRIPTION_RESTRICTION_MESSAGE)
    private String description;
    @NotNull(message = PRICE_RESTRICTION_MESSAGE)
    @Positive(message = PRICE_RESTRICTION_MESSAGE)
    private BigDecimal price;
    @NotNull(message = NEEDED_DATE_TIME_NOT_NULL_MESSAGE)
    @DateTimeFormat(pattern = DATE_TIME_PATTERN)
    @FutureOrPresent(message = NEEDED_DATE_TIME_RESTRICTION_MESSAGE)
    private LocalDateTime neededBefore;
    @NotNull(message = CATEGORY_NOT_NULL_MESSAGE)
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
