package com.example.thi.repository;


import com.example.thi.model.CanCuoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ICanCuocRepository extends JpaRepository<CanCuoc,Long> {
@Query(value = "select * from can_cuoc",nativeQuery = true)
    Page<CanCuoc>pageCanCuoc(Pageable pageable);

@Query(value = "select * from can_cuoc where can_cuoc.ten_can_cuoc like %:tenCanCuocSearch% " +
        "and can_cuoc.hang_san_xuat like %:hangSanXuatSearch%",nativeQuery = true)
    Page<CanCuoc>pageCanCuocVaSearch(@Param("tenCanCuocSearch") String tenCanCuocSearch,
                                     @Param("hangSanXuatSearch")String hangSanXuatSearch,
                                     Pageable pageable);
}
