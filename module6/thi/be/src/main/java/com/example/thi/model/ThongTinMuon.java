package com.example.thi.model;

import javax.persistence.*;

@Entity
public class ThongTinMuon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long soLuongMuon;
    private String ngayMuon;
    private String ngayTra;
    @ManyToOne
    private CanCuoc canCuoc;
    @ManyToOne
    private NhanVien nhanVien;

    public ThongTinMuon() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSoLuongMuon() {
        return soLuongMuon;
    }

    public void setSoLuongMuon(Long soLuongMuon) {
        this.soLuongMuon = soLuongMuon;
    }

    public String getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(String ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public String getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(String ngayTra) {
        this.ngayTra = ngayTra;
    }

    public CanCuoc getCanCuoc() {
        return canCuoc;
    }

    public void setCanCuoc(CanCuoc canCuoc) {
        this.canCuoc = canCuoc;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }
}
