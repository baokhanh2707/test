package com.example.be.service;

import com.example.be.dto.OderDetailDto;
import com.example.be.model.OderDetail;

import java.util.List;

public interface IOderDetailService {
    List<OderDetailDto> listOderDetailHistory(Long id);
    void save(OderDetail oderDetail);
}
