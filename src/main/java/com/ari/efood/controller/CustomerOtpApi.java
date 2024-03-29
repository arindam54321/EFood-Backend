package com.ari.efood.controller;

import com.ari.efood.dto.CustomerOtpDto;
import com.ari.efood.exception.CustomerOtpException;
import com.ari.efood.exception.JWTException;
import com.ari.efood.service.CustomerOtpService;
import com.ari.efood.utils.ResponseWrapper;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@CrossOrigin
@RestController
@RequestMapping(value = "otp")
public class CustomerOtpApi {
    @Autowired
    CustomerOtpService service;

    @PostMapping(value = "generate")
    public ResponseEntity<ResponseWrapper<CustomerOtpDto>> generateOtp(
            @Email(message = "Enter a valid Email") @RequestParam("email") String email
    ) {
        CustomerOtpDto response = service.generateOtp(email);
        HttpStatus status = HttpStatus.OK;
        return ResponseWrapper.entity(response, status);
    }

    @GetMapping(value = "validate")
    public ResponseEntity<ResponseWrapper<String>> validateOtp(
            @Email(message = "Enter a valid Email") @RequestParam("email") String email,
            @RequestParam("otp") Integer otp
    ) throws CustomerOtpException, JWTException {
        String response = service.validateOtp(email, otp);
        HttpStatus status = HttpStatus.OK;
        return ResponseWrapper.entity(response, status);
    }
}
