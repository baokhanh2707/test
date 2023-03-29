package com.example.be.repository;

import com.example.be.dto.OderDto;
import com.example.be.model.Oder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IOderRepository extends JpaRepository<Oder,Long> {
    @Query(value = "select * from oder where customer_id_customer= :id",nativeQuery = true)
    List<OderDto>listOder( Long id);

}
