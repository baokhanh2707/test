package com.example.be.repository;

import com.example.be.model.StatusProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStatusProductRepository extends JpaRepository<StatusProduct,Long> {
}
