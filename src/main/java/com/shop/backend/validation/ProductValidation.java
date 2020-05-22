package com.shop.backend.validation;

import com.shop.backend.model.Product;
import com.shop.backend.repository.ProductRepository;

public class ProductValidation {

    public Product checkProduct(Product newProduct, ProductRepository productRepository) {

        Product oldProduct = productRepository.findById(newProduct.getId()).orElse(null);

        if (oldProduct != null) {
            if (newProduct.getProductName() == null) {
                newProduct.setProductName(oldProduct.getProductName());
            } else {
                oldProduct.setProductName(newProduct.getProductName());
            }

            if (newProduct.getProductDescription() == null) {
                newProduct.setProductDescription(oldProduct.getProductDescription());
            } else {
                oldProduct.setProductDescription(newProduct.getProductDescription());
            }

            if (newProduct.getUnitsInStock() == null) {
                newProduct.setUnitsInStock(oldProduct.getUnitsInStock());
            } else {
                oldProduct.setUnitsInStock(newProduct.getUnitsInStock());
            }

            if (newProduct.getPrice() == null) {
                newProduct.setPrice(oldProduct.getPrice());
            } else {
                oldProduct.setPrice(newProduct.getPrice());
            }

            if (newProduct.getProductCategory() == null) {
                newProduct.setProductCategory(oldProduct.getProductCategory());
            } else {
                oldProduct.setProductCategory(newProduct.getProductCategory());
            }

            newProduct.setProductBrand(oldProduct.getProductBrand());
            newProduct.setOrders(oldProduct.getOrders());
        }

        return newProduct;
    }
}
