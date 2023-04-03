package com.example.be.controller;

import com.example.be.dto.*;
import com.example.be.model.*;
import com.example.be.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/api/public/carts")
@CrossOrigin("*")
public class CartController {
    @Autowired
    private ICartDetailService cartDetailService;
    @Autowired
    private IProductService productService;
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private ICartService cartService;
    @Autowired
    private IOderService oderService;
    @Autowired
    private IOderDetailService oderDetailService;

    @PostMapping("")
    public ResponseEntity<?> addToCart(@RequestBody CartDetailDto cartDetailDto) {
        CartDetail cartDetail = new CartDetail();
        Cart cart = new Cart();
        Optional<Customer> customer = customerService.findByIdCustomer(cartDetailDto.getIdCustomer());
        if (!customer.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        BeanUtils.copyProperties(cartDetailDto, cartDetail);
        Optional<Product> product = productService.findByIdProduct(cartDetailDto.getIdProduct());
        if (!product.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        boolean check = cartDetailService.existsByProductIdProductAndCartCustomerIdCustomer(cartDetailDto.getIdProduct(), cartDetailDto.getIdCustomer());
        if (check) {
            Optional<CartDetail> cartDetail1 = cartDetailService.findByProductIdProductAndCartCustomerIdCustomer(cartDetailDto.getIdProduct(), cartDetailDto.getIdCustomer());
            if (!cartDetail1.isPresent()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            cartDetail1.get().setQuantity(cartDetail1.get().getQuantity() + cartDetailDto.getQuantity());
            cartDetailService.save(cartDetail1.get());
        } else {
            cart.setCustomer(customer.get());
            cartService.save(cart);
            cartDetail.setPrice(cartDetailDto.getPrice());
            cartDetail.setProduct(product.get());
            cartDetail.setCart(cart);
            cartDetailService.save(cartDetail);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{idCustomer}")
    public ResponseEntity<?> getAllCard(@PathVariable("idCustomer") Long id) {
        List<CartDto> cartDtoList = cartDetailService.getAllCart(id);
        Double totalMoney = cartDetailService.getTotalMoneyCart(id);
        List<Object> objectList = new ArrayList<>();
        objectList.add(cartDtoList);
        objectList.add(totalMoney);
        return new ResponseEntity<>(objectList, HttpStatus.OK);
    }

    @PatchMapping("updateQuantity")
    public ResponseEntity<?> updateQuantity(@RequestBody CartDetailDto cartDetailDto) {
        cartDetailService.updateAmountInCart(cartDetailDto.getQuantity(), cartDetailDto.getIdCartDetail());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/amount-exist/{idCartDetail}")
    public ResponseEntity<?> getAmountExist(@PathVariable("idCartDetail") Integer id) {
        Optional<AmountProductDto> productInfo = productService.getAmountExist(id);
        if (!productInfo.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(productInfo.get(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idCartDetail}")
    public ResponseEntity<?> deleteCartDetailById(@PathVariable Long idCartDetail) {
        CartDetail cartDetail = cartDetailService.findByIdCartDetail(idCartDetail);
        if (cartDetail == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        cartDetailService.deleteByIdCartDetail(idCartDetail);
        return new ResponseEntity<>(cartDetail, HttpStatus.OK);
    }

    @PostMapping("/payment")
    public ResponseEntity<?> paymentCart(@RequestBody PayDto payDto) {
        Oder oder = new Oder();
        Optional<Customer> customer = customerService.findByIdCustomer(payDto.getIdCustomer());
        if (!customer.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        oder.setCustomer(customer.get());
        oder.setNamePay(payDto.getNameCustomer());
        oder.setPhonePay(payDto.getPhoneNumber());
        oder.setEmailPay(payDto.getEmail());
        oder.setAddressPay(payDto.getAddress());
        oder.setTotalCart(payDto.getTotal());
        oder.setNote(payDto.getNote());
        oder.setCodePay(ThreadLocalRandom.current().nextInt(0, 999999));
        oderService.save(oder);
        List<CartDto> cartDtoList = cartDetailService.getAllCart(payDto.getIdCustomer());
        List<Product> productList = productService.getAllProduct();
        for (CartDto cartDto : cartDtoList) {
            for (Product product : productList) {
                if (Objects.equals(cartDto.getIdProduct(), product.getIdProduct())) {
                    product.setAmountExist(product.getAmountExist() - cartDto.getQuantity());
                }
                productService.save(product);
            }
        }
        for (CartDto cartDto : cartDtoList) {
            OderDetail oderDetail = new OderDetail();
            Optional<CartDetail> cartDetail = cartDetailService.findByProductIdProductAndCartCustomerIdCustomer(cartDto.getIdProduct(), payDto.getIdCustomer());
            if (!cartDetail.isPresent()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            cartDetail.get().setFlagStatus(true);
            cartDetailService.save(cartDetail.get());
            oderDetail.setProduct(productService.findByIdProduct(cartDto.getIdProduct()).get());
            oderDetail.setOder(oder);
            oderDetail.setQuantity(cartDto.getQuantity());
            oderDetail.setStatusPay(true);
            oderDetail.setFlagDelete(false);
            oderDetail.setCreateDate(LocalDateTime.now());
            oderDetail.setModifyDate(LocalDateTime.now());
            oderDetail.setStatusShipping(false);
            oderDetail.setPrice(cartDto.getPrice());
            oderDetailService.save(oderDetail);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/history/{idCustomer}")
    public ResponseEntity<Page<OderDto>>getHistoryPay(@PathVariable("idCustomer")Long id, @PageableDefault(size = 5) Pageable pageable){
        Page<OderDto>oderDtoPage=oderService.listOderHistory(id,pageable);
        return new ResponseEntity<>(oderDtoPage,HttpStatus.OK);
    }
    @GetMapping("/detail/{idCustomer}")
    public ResponseEntity<List<OderDetailDto>>getHistoryDetailPay(@PathVariable("idCustomer")Long id){
        List<OderDetailDto>oderDetailDtoList=oderDetailService.listOderDetailHistory(id);
        return new ResponseEntity<>(oderDetailDtoList,HttpStatus.OK);
    }
}