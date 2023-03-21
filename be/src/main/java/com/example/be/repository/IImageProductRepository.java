package com.example.be.repository;

import com.example.be.model.ImageProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IImageProductRepository extends JpaRepository<ImageProduct,Long> {
}
