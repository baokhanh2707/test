package com.example.thi.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class ViTri {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "viTri")
    public Set<NhanVien>nhanVienSet;
    public ViTri() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<NhanVien> getNhanVienSet() {
        return nhanVienSet;
    }

    public void setNhanVienSet(Set<NhanVien> nhanVienSet) {
        this.nhanVienSet = nhanVienSet;
    }
}
