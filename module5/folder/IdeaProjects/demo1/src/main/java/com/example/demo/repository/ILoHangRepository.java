package com.example.demo.repository;

import com.example.demo.model.LoHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ILoHangRepository extends JpaRepository<LoHang, Long> {
    @Query(value = "select *,san_pham.ten_san_pham from lo_hang join san_pham on lo_hang.san_pham_id = san_pham.id where lo_hang.flag_delete=false and  (date(lo_hang.ngay_san_xuat) between date(:day1) and date(:day2)) and san_pham.id = :idSanPham",
            countQuery = "select *,san_pham.ten_san_pham from lo_hang join san_pham on lo_hang.san_pham_id = san_pham.id where lo_hang.flag_delete=false and  (date(lo_hang.ngay_san_xuat) between date(:day1) and date(:day2)) and san_pham.id = :idSanPham",
            nativeQuery = true)
    Page<LoHang> pageLoHangDateAndID(@Param("day1") String day1, @Param("day2") String day2, @Param("idSanPham") Long idSanPham, Pageable pageable);

    @Query(value = "select *,san_pham.ten_san_pham from lo_hang join san_pham on lo_hang.san_pham_id = san_pham.id where lo_hang.flag_delete=false and  date(lo_hang.ngay_san_xuat) between date(:day1) and date(:day2)",
            countQuery = "select *,san_pham.ten_san_pham from lo_hang join san_pham on lo_hang.san_pham_id = san_pham.id where lo_hang.flag_delete=false and  date(lo_hang.ngay_san_xuat) between date(:day1) and date(:day2)",
            nativeQuery = true)
    Page<LoHang> pageLoHang(@Param("day1") String day1, @Param("day2") String day2, Pageable pageable);

//    @Transactional
//    @Modifying
//    @Query(value = "INSERT INTO lo_hang (" +
//            "flag_delete," +
//            " ma_lo_hang," +
//            " ngay_het_han," +
//            " ngay_san_xuat," +
//            " ngay_nhap_hang," +
//            " so_luong," +
//            " address_employee," +
//            " account_id_account," +
//            " division_id_division) " +
//            "VALUES " +
//            " (:#{#employee.codeEmployee}," +
//            " :#{#employee.nameEmployee}," +
//            " :#{#employee.emailEmployee}," +
//            " :#{#employee.dateOfBirth}" +
//            " :#{#employee.genderEmployee}," +
//            " :#{#employee.phoneEmployee}," +
//            " :#{#employee.addressEmployee}," +
//            " :#{#employee.account.idAccount}," +
//            " :#{#employee.division.idDivision})",
//            nativeQuery = true)
//    void saveLoHang(
//            @Param("loHang") LoHang loHang
//    );
}