package com.shop.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "brand")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonIgnore
    private Integer id;

    @Column(name = "brand_name")
    private String brandName;

    @Column(name = "brand_description")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonIgnore
    private String brandDescription;

    @OneToMany(mappedBy = "productBrand")
    private Set<Product> products;

    @JsonIgnore
    public Set<Product> getProducts() {
        return products;
    }

}
