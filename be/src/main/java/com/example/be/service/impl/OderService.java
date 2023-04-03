package com.example.be.service.impl;

import com.example.be.dto.OderDto;
import com.example.be.model.Oder;
import com.example.be.repository.IOderRepository;
import com.example.be.service.IOderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OderService implements IOderService {
    @Autowired
    private IOderRepository oderRepository;

    @Override
    public void save(Oder oder) {
        oderRepository.save(oder);
    }

    @Override
    public Page<OderDto> listOderHistory(Long id, Pageable pageable) {
        return oderRepository.listOderHistory(id,pageable);
    }

}
