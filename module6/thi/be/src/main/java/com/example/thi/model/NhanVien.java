package com.example.thi.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class NhanVien {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String maNhanVien;
    private String tenNhanVien;
    @ManyToOne
    private ViTri viTri;
    @OneToMany(mappedBy = "nhanVien")
    public Set<ThongTinMuon> thongTinMuonSet;

    public NhanVien() {
    }

    public Set<ThongTinMuon> getThongTinMuonSet() {
        return thongTinMuonSet;
    }

    public void setThongTinMuonSet(Set<ThongTinMuon> thongTinMuonSet) {
        this.thongTinMuonSet = thongTinMuonSet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public ViTri getViTri() {
        return viTri;
    }

    public void setViTri(ViTri viTri) {
        this.viTri = viTri;
    }
}
