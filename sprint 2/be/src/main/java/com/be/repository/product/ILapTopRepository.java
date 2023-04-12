package com.be.repository.product;

import com.be.model.Laptop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ILapTopRepository extends JpaRepository<Laptop, Long> {
    @Query(value = "select * from laptop where flag_delete=false"
            , nativeQuery = true)
    Page<Laptop> getAllLaptop(Pageable pageable);

    @Query(value = "select * from laptop where flag_delete=false and laptop.name_laptop like %:nameSearch% order by laptop.id_laptop desc "
            , nativeQuery = true)
    Page<Laptop> getAllLaptopAndSearch(@Param("nameSearch") String nameSearch,Pageable pageable );

    @Query(value = "select * from laptop  where laptop.id_laptop = :idLapTop", nativeQuery = true)
    Laptop findByIdLaptop(@Param("idLapTop") Long idLapTop);

    @Query(value= "SELECT * FROM laptop WHERE laptop.type_product_id_id_type_product = (SELECT laptop.type_product_id_id_type_product FROM laptop WHERE laptop.id_laptop = :id ) and id_laptop != :id",nativeQuery = true)
    List<Laptop>getLapByIdProduct(@Param("id") Long id);
}
