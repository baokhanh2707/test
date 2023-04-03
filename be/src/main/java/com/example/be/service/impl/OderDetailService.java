package com.example.be.service.impl;

import com.example.be.dto.OderDetailDto;
import com.example.be.model.OderDetail;
import com.example.be.repository.IOderDetailRepository;
import com.example.be.service.IOderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OderDetailService implements IOderDetailService {
    @Autowired
    private IOderDetailRepository oderDetailRepository;

    @Override
    public List<OderDetailDto> listOderDetailHistory(Long id) {
        return oderDetailRepository.listOderDetailHistory(id);
    }

    @Override
    public void save(OderDetail oderDetail) {
        oderDetailRepository.save(oderDetail);
    }
}
