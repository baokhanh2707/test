package com.example.demo.service.impl;

import com.example.demo.model.LoHang;
import com.example.demo.repository.ILoHangRepository;
import com.example.demo.service.ILoHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Date;
@Service
public class LoHangService implements ILoHangService {
    @Autowired
    private ILoHangRepository iLoHangRepository;
    @Override
    public Page<LoHang> pageLoHangDateAndID(String day1, String day2, Long idSanPham, Pageable pageable) {
        return iLoHangRepository.pageLoHangDateAndID(day1,day2,idSanPham,pageable) ;
    }
    @Override
    public Page<LoHang> pageLoHang(String day1,String day2,Pageable pageable) {
        return iLoHangRepository.pageLoHang(day1,day2,pageable);
    }
}
