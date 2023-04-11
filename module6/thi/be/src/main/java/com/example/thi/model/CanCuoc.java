package com.example.thi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

@Entity
public class CanCuoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String maCanCuoc;
    private String tenCanCuoc;
    private String hangSanXuat;
    private Long soLuong;
    private String donVi;
    @JsonBackReference
    @OneToMany(mappedBy = "canCuoc")
    public Set<ThongTinMuon>thongTinMuonSet;

    public CanCuoc() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaCanCuoc() {
        return maCanCuoc;
    }

    public void setMaCanCuoc(String maCanCuoc) {
        this.maCanCuoc = maCanCuoc;
    }

    public String getTenCanCuoc() {
        return tenCanCuoc;
    }

    public void setTenCanCuoc(String tenCanCuoc) {
        this.tenCanCuoc = tenCanCuoc;
    }

    public String getHangSanXuat() {
        return hangSanXuat;
    }

    public void setHangSanXuat(String hangSanXuat) {
        this.hangSanXuat = hangSanXuat;
    }

    public Long getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Long soLuong) {
        this.soLuong = soLuong;
    }

    public Set<ThongTinMuon> getThongTinMuonSet() {
        return thongTinMuonSet;
    }

    public void setThongTinMuonSet(Set<ThongTinMuon> thongTinMuonSet) {
        this.thongTinMuonSet = thongTinMuonSet;
    }

    public String getDonVi() {
        return donVi;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }
}
