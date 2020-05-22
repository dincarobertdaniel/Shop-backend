package com.shop.backend.controller;

import com.shop.backend.model.Brand;
import com.shop.backend.model.Product;
import com.shop.backend.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/brands")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping("/search")
    public List<Brand> getAllBrands() {
        return brandService.getAllBrands();
    }

    @GetMapping("/search/{brandName}")
    public List<Product> getBrandProducts(@PathVariable String brandName) {
        return brandService.getBrandProducts(brandName);
    }

    @PostMapping("/add")
    public Brand addNewBrand(@RequestBody Brand brand) {
        return brandService.addNewBrand(brand);
    }

    @PatchMapping("/update")
    public Brand updateBrand(@RequestBody Brand brand) {
        return brandService.updateBrand(brand);
    }

    @DeleteMapping("/delete")
    public void deleteBrand(@RequestParam Integer brandId) {
         brandService.deleteBrand(brandId);
    }
}
