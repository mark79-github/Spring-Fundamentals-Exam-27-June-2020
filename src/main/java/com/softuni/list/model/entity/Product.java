package com.softuni.list.model.entity;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.softuni.list.constant.GlobalConstants.*;

@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    @Column(nullable = false, unique = true)
    @Length(min = 3, max = 20, message = PRODUCT_NAME_RESTRICTION_MESSAGE)
    private String name;
    @Column(nullable = false, columnDefinition = "TEXT")
    @Length(min = 5, message = DESCRIPTION_RESTRICTION_MESSAGE)
    private String description;
    @Column(nullable = false)
    @Positive(message = PRICE_RESTRICTION_MESSAGE)
    private BigDecimal price;
    @Column(name = "needed_before", nullable = false)
    @DateTimeFormat(pattern = DATE_TIME_PATTERN)
    @FutureOrPresent(message = NEEDED_DATE_TIME_RESTRICTION_MESSAGE)
    private LocalDateTime neededBefore;
    @ManyToOne(optional = false)
    private Category category;

    public Product() {
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", neededBefore=" + neededBefore +
                ", category=" + category +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (!name.equals(product.name)) return false;
        if (!description.equals(product.description)) return false;
        if (!price.equals(product.price)) return false;
        if (!neededBefore.equals(product.neededBefore)) return false;
        return category.equals(product.category);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + neededBefore.hashCode();
        result = 31 * result + category.hashCode();
        return result;
    }
}
