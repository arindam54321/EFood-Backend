package com.ari.efood.controller;

import com.ari.efood.dto.OrderDto;
import com.ari.efood.service.OrderService;
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
@RequestMapping(value = "order")
public class OrderApi {

    @Autowired
    private OrderService service;

    @PostMapping(value = "place")
    ResponseEntity<ResponseWrapper<OrderDto>> placeOrder(
            @Valid @RequestBody OrderDto orderDto
    ) {
        OrderDto response = service.placeOrder(orderDto);
        HttpStatus status = HttpStatus.OK;
        return ResponseWrapper.entity(response, status);
    }

    @GetMapping(value = "findbyemail")
    ResponseEntity<ResponseWrapper<List<OrderDto>>> findByEmail(
            @Email(message = "Please enter a valid Email") @RequestParam(name = "email") String email
    ) {
        List<OrderDto> response = service.findByEmail(email);
        HttpStatus status = HttpStatus.OK;
        return ResponseWrapper.entity(response, status);
    }
}
