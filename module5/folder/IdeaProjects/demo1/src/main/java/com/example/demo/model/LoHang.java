package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class LoHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String maLoHang;
    @ManyToOne
    @JsonManagedReference
    private SanPham sanPham;
    private int soLuong;
    private String ngayNhapHang;
    private String ngaySanXuat;
    private String ngayHetHan;
    private boolean flagDelete = false;


    public LoHang() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaLoHang() {
        return maLoHang;
    }

    public void setMaLoHang(String maLoHang) {
        this.maLoHang = maLoHang;
    }

    public SanPham getTenSanPham() {
        return sanPham;
    }

    public void setTenSanPham(SanPham tenSanPham) {
        this.sanPham = tenSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getNgayNhapHang() {
        return ngayNhapHang;
    }

    public void setNgayNhapHang(String ngayNhapHang) {
        this.ngayNhapHang = ngayNhapHang;
    }

    public String getNgaySanXuat() {
        return ngaySanXuat;
    }

    public void setNgaySanXuat(String ngaySanXuat) {
        this.ngaySanXuat = ngaySanXuat;
    }

    public String getNgayHetHan() {
        return ngayHetHan;
    }

    public void setNgayHetHan(String ngayHetHan) {
        this.ngayHetHan = ngayHetHan;
    }

    public boolean isFlagDelete() {
        return flagDelete;
    }

    public void setFlagDelete(boolean flagDelete) {
        this.flagDelete = flagDelete;
    }
}