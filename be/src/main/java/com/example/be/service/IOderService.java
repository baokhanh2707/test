package com.example.be.service;

import com.example.be.dto.OderDto;
import com.example.be.model.Oder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IOderService {
    void save(Oder oder);
    Page<OderDto> listOderHistory(Long id, Pageable pageable);
}
