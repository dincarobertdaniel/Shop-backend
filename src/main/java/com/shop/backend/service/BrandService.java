package com.shop.backend.service;

import com.shop.backend.exceptions.BrandAlreadyExistsException;
import com.shop.backend.exceptions.BrandNotFoundException;
import com.shop.backend.model.Brand;
import com.shop.backend.repository.BrandRepository;
import com.shop.backend.repository.ProductRepository;
import com.shop.backend.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private ProductRepository productRepository;


    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    public Brand addNewBrand(Brand newBrand) {
        Brand brand = brandRepository.findByBrandName(newBrand.getBrandName());
        if (brand == null) {
            return brandRepository.save(newBrand);
        } else {
            throw new BrandAlreadyExistsException(newBrand.getBrandName());
        }
    }

    public Brand updateBrand(Brand updateBrand) {
        Brand brand = brandRepository.findById(updateBrand.getId()).orElse(null);
        if (brand != null) {
            brand.setBrandDescription(updateBrand.getBrandDescription());
            return brandRepository.save(brand);
        } else {
            throw new BrandNotFoundException(updateBrand.getBrandName());
        }
    }

    public List<Product> getBrandProducts(String brandName) {
        Brand brand = brandRepository.findByBrandName(brandName);
        if (brand != null) {
            return productRepository.findByProductBrand(brand);
        } else {
            throw new BrandNotFoundException(brandName);
        }
    }

    public void deleteBrand(Integer brandId) {
        Brand brand = brandRepository.findById(brandId).orElse(null);
        if (brand != null) {
            brandRepository.delete(brand);
        } else {
            throw new BrandNotFoundException(brandId);
        }
    }
}
