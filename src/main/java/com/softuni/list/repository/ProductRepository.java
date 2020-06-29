package com.softuni.list.repository;

import com.softuni.list.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    @Query("SELECT sum(p.price) AS total_price FROM Product AS p")
    Optional<BigDecimal> getProductsTotalSum();
}
