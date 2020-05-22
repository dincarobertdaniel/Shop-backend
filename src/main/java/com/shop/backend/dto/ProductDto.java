package com.shop.backend.dto;

import com.shop.backend.model.Brand;
import com.shop.backend.model.Product;
import com.shop.backend.model.ProductCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ProductDto implements Serializable {

    private Integer id;
    private String productName;
    private String productDescription;
    private Integer unitsInStock;
    private BigDecimal price;
    private Brand productBrand;
    private ProductCategory productCategory;

    public Product toProduct(){
        Product product = new Product();
        product.setId(this.getId());
        product.setProductName(this.getProductName());
        product.setProductDescription(this.getProductDescription());
        product.setUnitsInStock(this.getUnitsInStock());
        product.setPrice(this.getPrice());
        product.setProductBrand(this.getProductBrand());
        product.setProductCategory(this.getProductCategory());
        return product;
    }

}
