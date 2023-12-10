package com.ari.efood.controller;

import com.ari.efood.auth.JWTUtils;
import com.ari.efood.dto.CustomerDto;
import com.ari.efood.enums.Role;
import com.ari.efood.exception.CustomerException;
import com.ari.efood.exception.JWTException;
import com.ari.efood.service.CustomerService;
import com.ari.efood.service.JWTValidatorService;
import com.ari.efood.utils.ResponseWrapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.jose4j.lang.JoseException;
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
    @Autowired
    private JWTValidatorService jwtValidatorService;

    @GetMapping(value = "all")
    public ResponseEntity<ResponseWrapper<List<CustomerDto>>> getAllCustomer(
            @RequestHeader(name = JWTUtils.JWT_HEADER_KEY) String jwt
    ) throws JWTException {
        jwtValidatorService.validate(jwt);
        List<CustomerDto> response = service.getAll();
        HttpStatus status = HttpStatus.OK;
        return ResponseWrapper.entity(response, status);
    }

    @GetMapping(value = "exists")
    public ResponseEntity<ResponseWrapper<Boolean>> doesCustomerExist(
            @Email(message = "Enter a valid email") @RequestParam(value = "email") String email
    ) {
        Boolean response = service.doesCustomerExist(email);
        HttpStatus status = response ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseWrapper.entity(response, status);
    }

    @GetMapping(value = "get")
    public ResponseEntity<ResponseWrapper<CustomerDto>> getCustomer(
            @Email(message = "Enter a valid email") @RequestParam(value = "email") String email,
            HttpServletResponse httpServletResponse
    ) throws CustomerException, JoseException {
        CustomerDto response = service.getCustomer(email);
        HttpStatus status = HttpStatus.OK;

        String jwt = JWTUtils.generateJWT(response.getId(), "", response.getEmail(), Role.CUSTOMER);
        httpServletResponse.setHeader(JWTUtils.JWT_HEADER_KEY, jwt);
        return ResponseWrapper.entity(response, status, httpServletResponse.getHeaders(JWTUtils.JWT_HEADER_KEY));
    }

    @PostMapping(value = "add")
    public ResponseEntity<ResponseWrapper<CustomerDto>> addCustomer(
            @RequestBody @Valid CustomerDto customer,
            HttpServletResponse httpServletResponse
    ) throws CustomerException, JoseException {
        CustomerDto response = service.addCustomer(customer);
        HttpStatus status = HttpStatus.OK;

        String jwt = JWTUtils.generateJWT(response.getId(), "", response.getEmail(), Role.CUSTOMER);
        httpServletResponse.setHeader(JWTUtils.JWT_HEADER_KEY, jwt);
        return ResponseWrapper.entity(response, status, httpServletResponse.getHeaders(JWTUtils.JWT_HEADER_KEY));
    }

    @DeleteMapping(value = "delete")
    public ResponseEntity<ResponseWrapper<String>> deleteCustomer(
            @RequestHeader(name = JWTUtils.JWT_HEADER_KEY) String jwt,
            @Email(message = "Enter a valid email") @RequestParam(value = "email") String email
    ) throws CustomerException, JWTException {
        jwtValidatorService.validate(jwt);
        String response = service.deleteCustomer(email);
        HttpStatus status = HttpStatus.OK;
        return ResponseWrapper.entity(response, status);
    }
}
