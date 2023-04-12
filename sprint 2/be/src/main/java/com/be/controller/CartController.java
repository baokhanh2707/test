package com.be.controller;

import com.be.dto.customer.CartDetailDto;
import com.be.dto.customer.CartDto;
import com.be.model.Cart;
import com.be.model.CartDetail;
import com.be.model.Customer;
import com.be.model.Laptop;
import com.be.service.ICartDetailService;
import com.be.service.ICartService;
import com.be.service.ICustomerService;
import com.be.service.ILapTopService;
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
    private ILapTopService lapTopService;
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
        Laptop laptop = lapTopService.findByIdLaptop(cartDetailDto.getIdLaptop());
        if (laptop == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        boolean check = cartDetailService.existsByLaptopIdLaptopAndCartCustomerIdCustomer(cartDetailDto.getIdLaptop(), cartDetailDto.getIdCustomer());
        if (check) {
            Optional<CartDetail> cartDetail1 = cartDetailService.findByLaptopIdLaptopAndCartCustomerIdCustomer(cartDetailDto.getIdLaptop(), cartDetailDto.getIdCustomer());
            if (!cartDetail1.isPresent()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            cartDetail1.get().setQuantity(cartDetail1.get().getQuantity() + cartDetailDto.getQuantity());
            cartDetailService.save(cartDetail1.get());
        } else {
            cart.setCustomer(customer.get());
            cartService.save(cart);
            cartDetail.setLaptop(lapTopService.findByIdLaptop(cartDetailDto.getIdLaptop()));
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

