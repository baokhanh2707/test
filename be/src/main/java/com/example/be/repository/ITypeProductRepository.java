package com.example.be.repository;

import com.example.be.model.TypeProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITypeProductRepository extends JpaRepository<TypeProduct,Long> {
}
