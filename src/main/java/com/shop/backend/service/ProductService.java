package com.shop.backend.service;

import com.shop.backend.exceptions.ProductAlreadyExistsException;
import com.shop.backend.exceptions.ProductNotFoundException;
import com.shop.backend.repository.BrandRepository;
import com.shop.backend.repository.ProductRepository;
import com.shop.backend.validation.ProductValidation;
import com.shop.backend.exceptions.BrandNotFoundException;
import com.shop.backend.model.Brand;
import com.shop.backend.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BrandRepository brandRepository;

    public List<Product> getProductByName(String productName) {
        return productRepository.findByProductName(productName)
                .orElseThrow(() -> new ProductNotFoundException(productName));
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product addNewProduct(Product newProduct) {
        Product product = productRepository.findByProductNameAndPrice(newProduct.getProductName(), newProduct.getPrice());
        if (product == null) {
            Brand brand = brandRepository.findByBrandName(newProduct.getProductBrand().getBrandName());
            if (brand != null) {
                newProduct.setProductBrand(brand);
                return productRepository.save(newProduct);
            } else {
                throw new BrandNotFoundException(newProduct.getProductBrand().getBrandName());
            }
        } else {
            throw new ProductAlreadyExistsException(newProduct.getProductName());
        }
    }

    public Product updateProduct(Product updateProduct) {
        Product product = new ProductValidation().checkProduct(updateProduct,productRepository);
        return productRepository.save(product);
    }

    public List<Product> deleteProduct(Integer id) {
        productRepository.delete(productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id)));
        return productRepository.findAll();
    }

}

