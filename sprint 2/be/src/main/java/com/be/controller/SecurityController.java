package com.be.controller;

import com.be.dto.acount.request.SignInForm;
import com.be.dto.acount.response.JwtResponse;
import com.be.dto.customer.GetIdCustomer;
import com.be.jwt.jwt.JwtProvider;
import com.be.jwt.jwt.JwtTokenFilter;
import com.be.jwt.userprincal.AccountPrinciple;
import com.be.model.Customer;
import com.be.service.IAccountService;
import com.be.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin(value = "*", allowedHeaders = "*")
@RequestMapping("api/public")
public class SecurityController {
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private IAccountService iAccountService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenFilter jwtTokenFilter;
    @Autowired
    private ICustomerService customerService;

    @PostMapping("/signin")
    public ResponseEntity<?> login(@Valid @RequestBody SignInForm signInForm) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInForm.getUsername(), signInForm.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.createToken(authentication);
        Optional<GetIdCustomer> idCustomer = customerService.findByAccount(signInForm.getUsername());
        AccountPrinciple accountPrinciple = (AccountPrinciple) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(token,
                accountPrinciple.getName(),
                accountPrinciple.getAuthorities(),
                accountPrinciple.getUsernameAccount(),
                accountPrinciple.getIdAccount(),
                accountPrinciple.getEmail(),idCustomer));
    }
}
