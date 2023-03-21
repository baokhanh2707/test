package com.example.be.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

@Entity
public class StatusProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStatusProduct;
    private String nameStatusProduct;
    @OneToMany(mappedBy = "statusProduct")
    @JsonBackReference
    public Set<Product> products;

    public StatusProduct() {
    }

    public Long getIdStatusProduct() {
        return idStatusProduct;
    }

    public void setIdStatusProduct(Long idStatusProduct) {
        this.idStatusProduct = idStatusProduct;
    }

    public String getNameStatusProduct() {
        return nameStatusProduct;
    }

    public void setNameStatusProduct(String nameStatusProduct) {
        this.nameStatusProduct = nameStatusProduct;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
