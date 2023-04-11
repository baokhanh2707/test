package com.example.demo.service;

import com.example.demo.model.LoHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ILoHangService {
    Page<LoHang> pageLoHangDateAndID(@Param("day1") String day1, @Param("day2") String day2, @Param("idSanPham") Long idSanPham, Pageable pageable);

    Page<LoHang> pageLoHang(String day1,String day2,Pageable pageable);
}
