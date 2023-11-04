package com.ari.efood.controller;

import com.ari.efood.dto.CustomerDto;
import com.ari.efood.exception.CustomerException;
import com.ari.efood.service.CustomerService;
import com.ari.efood.utils.ResponseWrapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@CrossOrigin
@RestController
@RequestMapping(value = "customer")
public class CustomerApi {
    @Autowired
    private CustomerService service;

    @GetMapping(value = "all")
    public ResponseEntity<ResponseWrapper<List<CustomerDto>>> getAllCustomer() {
        List<CustomerDto> response = service.getAll();
        HttpStatus status = HttpStatus.OK;
        return ResponseWrapper.entity(response, status);
    }

    @PostMapping(value = "add")
    public ResponseEntity<ResponseWrapper<CustomerDto>> addCustomer(
            @RequestBody @Valid CustomerDto customer
    ) throws CustomerException {
        CustomerDto response = service.addCustomer(customer);
        HttpStatus status = HttpStatus.OK;
        return ResponseWrapper.entity(response, status);
    }

    @DeleteMapping(value = "delete")
    public ResponseEntity<ResponseWrapper<String>> deleteCustomer(
            @Email(message = "Enter a valid email") @RequestParam(value = "email") String email
    ) throws CustomerException {
        String response = service.deleteCustomer(email);
        HttpStatus status = HttpStatus.OK;
        return ResponseWrapper.entity(response, status);
    }
}
