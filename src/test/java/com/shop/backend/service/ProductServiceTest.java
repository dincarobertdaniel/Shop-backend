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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private BrandRepository brandRepository;

    private Brand brand = new Brand(0, "DummyBrand", "Dummy", null);

    private Product product = new Product(1, "TestProd", "ProdDesc1",
            1, new BigDecimal(1000), brand, null, ProductCategory.ELECTRONICS);

    private List<Product> allProducts;
    private List<Product> listOfProductsByName;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);

        Product product2 = new Product(2, "TestProd", "ProdDesc2",
                2, new BigDecimal(2000), brand, null, ProductCategory.ELECTRONICS);

        Product product3 = new Product(3, "TestProd3", "ProdDesc3",
                3, new BigDecimal(3000), brand, null, ProductCategory.ELECTRONICS);

        allProducts = new ArrayList<>();
        allProducts.add(product);
        allProducts.add(product2);
        allProducts.add(product3);

        listOfProductsByName = new ArrayList<>();
        listOfProductsByName.add(product);
        listOfProductsByName.add(product2);


        Mockito.when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        Mockito.when(productRepository.findAll()).thenReturn(allProducts);
        Mockito.when(productRepository.findByProductNameAndPrice(product.getProductName(),product.getPrice())).thenReturn(product);

        Mockito.when(productRepository.findByProductName(product.getProductName())).thenReturn(Optional.of(listOfProductsByName));

        Mockito.when(productRepository.save(product)).thenReturn(product);
        Mockito.when(brandRepository.findByBrandName(brand.getBrandName())).thenReturn(brand);
    }

    @Test
    void testGetProductByName() {
        List<Product> productList = productService.getProductByName(this.product.getProductName());
        Assertions.assertEquals(productList,this.listOfProductsByName);
    }

    @Test
    void testGetAllProducts() {
        List<Product> productList = productService.getAllProducts();
        Assertions.assertEquals(productList,this.allProducts);
    }

    @Test
    void testAddNewProduct() {
        Mockito.when(productRepository.findByProductNameAndPrice(this.product.getProductName(),this.product.getPrice())).thenReturn(null);
        Product product = productService.addNewProduct(this.product);
        Assertions.assertEquals(this.product,product);
    }

    @Test
    void testUpdateProduct() {
        Product updateProductFields = new Product();
        updateProductFields.setId(1);
        updateProductFields.setProductDescription("UpdateDescriptionTest");

        Product expectedProduct = new Product(1, "TestProd", "UpdateDescriptionTest",
                1, new BigDecimal(1000), brand, null, ProductCategory.ELECTRONICS);

        Mockito.when(productRepository.save(any(Product.class))).thenReturn(expectedProduct);

        Product updatedProduct = productService.updateProduct(updateProductFields);

        Assertions.assertEquals(updatedProduct,expectedProduct);

        Mockito.verify(productRepository,times(1)).save(updateProductFields);
    }

    @Test
    void testDeleteProduct() {
        productService.deleteProduct(product.getId());
        Mockito.verify(productRepository,times(1)).delete(product);
    }
}
