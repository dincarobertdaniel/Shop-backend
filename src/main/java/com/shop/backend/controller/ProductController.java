package com.shop.backend.controller;

import com.shop.backend.service.ProductService;
import com.shop.backend.dto.ProductDto;
import com.shop.backend.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/search/{productName}")
    public List<Product> getProductByName(@PathVariable String productName){
            return productService.getProductByName(productName);
    }

    @GetMapping("/search")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/add")
    public Product addProduct(@RequestBody Product newProduct) {
        return productService.addNewProduct(newProduct);
    }

    @PatchMapping("/update")
    public Product updateProduct(@RequestBody ProductDto updateProduct) {
        return productService.updateProduct(updateProduct.toProduct());
    }

    @DeleteMapping("/delete")
    public List<Product> deleteProduct(@RequestParam Integer productId) {
        return productService.deleteProduct(productId);
    }
}
