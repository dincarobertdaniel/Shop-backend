package com.shop.backend.service;

import com.shop.backend.model.Brand;
import com.shop.backend.model.Product;
import com.shop.backend.model.ProductCategory;
import com.shop.backend.repository.BrandRepository;
import com.shop.backend.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.times;


public class BrandServiceTest {

    @InjectMocks
    private BrandService brandService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private BrandRepository brandRepository;


    private Brand dummyBrand = new Brand(0, "DummyBrand", "Dummy", null);

    private List<Product> listOfProducts;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);

        List<Brand> brandList = new ArrayList<>();
        Brand brand = new Brand(0, "FirstBrandName", "FirstDescription", null);
        Brand brand2 = new Brand(1, "SecondBrandName", "SecondDescription", null);

        brandList.add(brand);
        brandList.add(brand2);

        Mockito.when(brandRepository.findAll()).thenReturn(brandList);

        Mockito.when(brandRepository.save(dummyBrand)).thenReturn(dummyBrand);

        Mockito.when(brandRepository.findById(dummyBrand.getId())).thenReturn(Optional.of(dummyBrand));

        listOfProducts = new ArrayList<>();
        Product product1 = new Product(1, "TestProd1", "ProdDesc1", 1, new BigDecimal(1000), dummyBrand, null, ProductCategory.ELECTRONICS);
        Product product2 = new Product(2, "TestProd2", "ProdDesc2", 2, new BigDecimal(2000), dummyBrand, null, ProductCategory.ELECTRONICS);
        listOfProducts.add(product1);
        listOfProducts.add(product2);

        Mockito.when(brandRepository.findByBrandName(dummyBrand.getBrandName())).thenReturn(dummyBrand);

        Mockito.when(productRepository.findByProductBrand(dummyBrand)).thenReturn(listOfProducts);
    }

    @Test
    public void testGetAllBrands() {
        List<Brand> list = brandService.getAllBrands();
        Assertions.assertEquals(2, list.size());
        Mockito.verify(brandRepository, times(1)).findAll();
    }

    @Test
    public void testAddNewBrand() {
        Mockito.when(brandRepository.findByBrandName(dummyBrand.getBrandName())).thenReturn(null);

        Brand brand = brandService.addNewBrand(dummyBrand);

        Assertions.assertEquals(dummyBrand.getBrandName(), brand.getBrandName());
        Assertions.assertEquals(dummyBrand.getId(), brand.getId());
        Assertions.assertEquals(dummyBrand.getBrandDescription(), brand.getBrandDescription());
        Assertions.assertEquals(dummyBrand.getProducts(), brand.getProducts());

        Mockito.verify(brandRepository,times(1)).findByBrandName(dummyBrand.getBrandName());
        Mockito.verify(brandRepository,times(1)).save(dummyBrand);
    }

    @Test
    public void testUpdateBrand() {
        Brand updateDummyBrand = new Brand(0, "DummyBrand", "updatedDescription", null);

        brandService.updateBrand(updateDummyBrand);

        Brand initialDummyBrand = new Brand(0, "DummyBrand", "Dummy", null);

        Assertions.assertEquals(dummyBrand.getBrandName(), initialDummyBrand.getBrandName());
        Assertions.assertEquals(dummyBrand.getId(), initialDummyBrand.getId());
        Assertions.assertNotEquals(dummyBrand.getBrandDescription(), initialDummyBrand.getBrandDescription());
        Assertions.assertEquals(dummyBrand.getProducts(), initialDummyBrand.getProducts());
    }

    @Test
    public void testGetBrandProducts() {
        List<Product> list = brandService.getBrandProducts(dummyBrand.getBrandName());
        Assertions.assertEquals(listOfProducts, list);
    }

    @Test
    public void testDeleteBrand() {
        brandService.deleteBrand(dummyBrand.getId());
        Mockito.verify(brandRepository, times(1)).delete(dummyBrand);
    }
}


