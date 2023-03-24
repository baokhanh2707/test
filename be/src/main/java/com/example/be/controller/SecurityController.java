package com.example.be.controller;

import com.example.be.dto.GetIdCustomer;
import com.example.be.dto.acount.request.SignInForm;
import com.example.be.dto.acount.response.JwtResponse;
import com.example.be.jwt.jwt.JwtProvider;
import com.example.be.jwt.jwt.JwtTokenFilter;
import com.example.be.jwt.userprincal.AccountPrinciple;
import com.example.be.service.IAccountService;
import com.example.be.service.impl.CustomerService;
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
    private CustomerService customerService;

    @PostMapping("/signin")
    public ResponseEntity<?> login(@Valid @RequestBody SignInForm signInForm) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInForm.getUsername(), signInForm.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.createToken(authentication);
        AccountPrinciple accountPrinciple = (AccountPrinciple) authentication.getPrincipal();
        Long idCustomer = customerService.findByAccount(signInForm.getUsername());
        return ResponseEntity.ok(new JwtResponse(token,
                accountPrinciple.getName(),
                accountPrinciple.getAuthorities(),
                accountPrinciple.getUsernameAccount(),
                accountPrinciple.getIdAccount(),
                accountPrinciple.getEmail(),
                idCustomer));

    }
}
