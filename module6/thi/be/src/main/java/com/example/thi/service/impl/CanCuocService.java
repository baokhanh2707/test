package com.example.thi.service.impl;


import com.example.thi.model.CanCuoc;
import com.example.thi.repository.ICanCuocRepository;
import com.example.thi.service.ICanCuocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CanCuocService implements ICanCuocService {
    @Autowired
    private ICanCuocRepository canCuocRepository;

    @Override
    public Page<CanCuoc> pageCanCuoc(Pageable pageable) {
        return canCuocRepository.pageCanCuoc(pageable);
    }

    @Override
    public Page<CanCuoc> pageCanCuocVaSearch(String tenCanCuocSearch, String hangSanXuatSearch, Pageable pageable) {
        return canCuocRepository.pageCanCuocVaSearch(tenCanCuocSearch,hangSanXuatSearch,pageable);
    }
    @Override
    public CanCuoc save(CanCuoc canCuoc) {
        return canCuocRepository.save(canCuoc);
    }
}
