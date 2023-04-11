package com.example.demo.controller;

import com.example.demo.model.LoHang;
import com.example.demo.service.ILoHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("lo-hang")
@CrossOrigin("*")
public class LoHangController {
    @Autowired
    private ILoHangService loHangService;

    @GetMapping("")
    public ResponseEntity<Page<LoHang>> getList(@RequestParam(defaultValue = "1000-01-01", required = false) String day1,
                                                @RequestParam(defaultValue = "2100-01-01", required = false) String day2,
                                                @RequestParam(defaultValue = "-1", required = false) Long idSanPham,
                                                @RequestParam(defaultValue = "0", required = false) int page) {
        Pageable pageable = Pageable.ofSize(5);
        Page<LoHang> loHangPage = null;
        if (idSanPham == -1) {
            loHangPage = loHangService.pageLoHang(day1, day2, pageable.withPage(page));
        } else if (idSanPham != -1) {
            loHangPage = loHangService.pageLoHangDateAndID(day1, day2,idSanPham, pageable.withPage(page));
        }
        if (loHangPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(loHangPage, HttpStatus.OK);
    }

//    @PostMapping("/save")
//    public ResponseEntity<?> save(@RequestBody LoHang loHang) {
//
//        loHangService.save(loHang);
//
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
//
//    @PatchMapping("{id}")
//    public ResponseEntity<LoHang> update(@RequestBody LoHang loHang, @PathVariable("id") Long id) {
//        LoHang loHang1 = loHangService.finById(id);
//        if (loHang1 == null){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        loHangService.update(loHang);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<LoHang> findById(@PathVariable("id") Long id) {
//        LoHang loHang = loHangService.finById(id);
//        if (loHang == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(loHang, HttpStatus.OK);
//    }
//    @DeleteMapping("{id}")
//    public ResponseEntity<LoHang> delete(@PathVariable("id") Long id) {
//        LoHang loHang = loHangService.finById(id);
//        if (loHang == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        loHangService.remove(id);
//        return new ResponseEntity<>(loHang, HttpStatus.OK);
//    }
}