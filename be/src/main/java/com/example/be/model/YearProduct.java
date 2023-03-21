package com.example.be.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

@Entity
public class YearProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idYear;
    private String nameYear;
    @OneToMany(mappedBy = "modelYear")
    @JsonBackReference
    public Set<Product> products;

    public YearProduct() {
    }

    public Long getIdYear() {
        return idYear;
    }

    public void setIdYear(Long idYear) {
        this.idYear = idYear;
    }

    public String getNameYear() {
        return nameYear;
    }

    public void setNameYear(String nameYear) {
        this.nameYear = nameYear;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
