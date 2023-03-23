package com.example.be.controller;

import com.example.be.dto.CartDetailDto;
import com.example.be.dto.CartDto;
import com.example.be.model.Cart;
import com.example.be.model.CartDetail;
import com.example.be.model.Customer;
import com.example.be.model.Product;
import com.example.be.service.ICartDetailService;
import com.example.be.service.ICartService;
import com.example.be.service.ICustomerService;
import com.example.be.service.IProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/user/carts")
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
            cartDetail.setProduct(product.get());
            cartDetail.setCart(cart);
            cartDetailService.save(cartDetail);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("card/{idCustomer}")
    public ResponseEntity<?> getAllCard(@PathVariable("idCustomer") Long id) {
        List<CartDto> cartDtoList = cartDetailService.getAllCart(id);
        Double totalMoney = cartDetailService.getTotalMoneyCart(id);
        List<Object>objectList=new ArrayList<>();
        objectList.add(cartDtoList);
        objectList.add(totalMoney);
        return new ResponseEntity<>(objectList,HttpStatus.OK);
    }
    @PatchMapping("updateQuantity")
    public ResponseEntity<?>updateQuantity(@RequestBody CartDetailDto cartDetailDto){
        cartDetailService.updateAmountInCart(cartDetailDto.getQuantity(), cartDetailDto.getIdCartDetail());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
