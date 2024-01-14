package com.ari.efood.controller;

import com.ari.efood.dto.OrderRequestDto;
import com.ari.efood.dto.PaymentOrderDto;
import com.ari.efood.exception.CustomerException;
import com.ari.efood.service.RazorpayService;
import com.ari.efood.utils.ResponseWrapper;
import com.razorpay.RazorpayException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "payment")
public class RazorpayApi {

    @Autowired
    private RazorpayService service;

    @PostMapping(value = "createorder")
    public ResponseEntity<ResponseWrapper<PaymentOrderDto>> createOrder(
            @Valid @RequestBody OrderRequestDto orderRequest
    ) throws RazorpayException, CustomerException {
        PaymentOrderDto response = service.createOrder(orderRequest);
        HttpStatus status = HttpStatus.OK;
        return ResponseWrapper.entity(response, status);
    }
}
