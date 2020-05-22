package com.shop.backend.repository;

import com.shop.backend.model.Brand;
import com.shop.backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findByProductNameAndPrice(String productName, BigDecimal price);

    List<Product> findByProductBrand(Brand productBrand);

    Optional<List<Product>> findByProductName(String productName);

}
