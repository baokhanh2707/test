package com.example.be.controller;
import com.example.be.dto.ImageByIdDto;
import com.example.be.dto.ProductDto;
import com.example.be.dto.ProductInType;
import com.example.be.dto.ProductInfoById;
import com.example.be.dto.ProductHomeOne;
import com.example.be.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/public/product")
@CrossOrigin("*")
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping("")
    public ResponseEntity<Page<ProductDto>> getAllProduct(@RequestParam(defaultValue = "") String nameTypeProduct,
                                                          @RequestParam(defaultValue = "") String nameStatusProduct,
                                                          @RequestParam(defaultValue = "") String nameYearProduct,
                                                          @PageableDefault(size = 6) Pageable pageable
    ) {
        Page<ProductDto> productPage;
        if (nameTypeProduct != null && nameStatusProduct != null && nameStatusProduct != null) {
            productPage = productService.pageProductAndSearch(nameTypeProduct, nameStatusProduct, nameYearProduct, pageable);
        } else {
            productPage = productService.pageProduct(pageable);
        }
        if (productPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productPage, HttpStatus.OK);
    }

    @GetMapping("/detail")
    public ResponseEntity<ProductInfoById> findByIdProduct(@RequestParam Long idProduct) {
        ProductInfoById product = productService.selectProductById(idProduct);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/image")
    public ResponseEntity<List<ImageByIdDto>> imageFindByIdProduct(@RequestParam Long idProduct) {
        List<ImageByIdDto> image = productService.selectImageById(idProduct);
        return new ResponseEntity<>(image, HttpStatus.OK);
    }

    @GetMapping("/related")
    public ResponseEntity<List<ProductInType>>findIdType(@RequestParam Long idProduct){
        List<ProductInType>product=productService.selectProductByIdType(idProduct);
        if (product.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
    @GetMapping("/oneProduct")
    public ResponseEntity<ProductHomeOne> productOne() {
        ProductHomeOne product = productService.selectProduct();
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
