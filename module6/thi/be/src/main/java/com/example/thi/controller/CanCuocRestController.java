package com.example.thi.controller;
import com.example.thi.model.CanCuoc;
import com.example.thi.service.ICanCuocService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("can-cuoc")
@CrossOrigin("*")
public class CanCuocRestController {
    @Autowired
    private ICanCuocService canCuocService;
    @GetMapping("")
    public ResponseEntity<Page<CanCuoc>> getAllCanCuoc(@RequestParam(defaultValue = "") String tenCanCuocSearch,
                                                        @RequestParam(defaultValue = "") String hangSanXuatSearch,
                                                        @PageableDefault(size = 5) Pageable pageable) {
        Page<CanCuoc> pageCanCuoc;
        if (tenCanCuocSearch != null && hangSanXuatSearch != null ) {
            pageCanCuoc = canCuocService.pageCanCuocVaSearch(
                    tenCanCuocSearch,
                    hangSanXuatSearch,
                    pageable);
        } else {
            pageCanCuoc = canCuocService.pageCanCuoc(pageable);
        }
        if (pageCanCuoc.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pageCanCuoc, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<CanCuoc> createDataForm(@RequestBody CanCuoc canCuoc) {
        canCuocService.save(canCuoc);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
