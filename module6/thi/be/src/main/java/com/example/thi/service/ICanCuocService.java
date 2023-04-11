package com.example.thi.service;


import com.example.thi.model.CanCuoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface ICanCuocService {
    Page<CanCuoc> pageCanCuoc(Pageable pageable);
    Page<CanCuoc>pageCanCuocVaSearch(@Param("tenCanCuocSearch") String tenCanCuocSearch,
                                     @Param("hangSanXuatSearch")String hangSanXuatSearch,
                                     Pageable pageable);
    CanCuoc save(CanCuoc canCuoc);
}
