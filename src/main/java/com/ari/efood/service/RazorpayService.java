package com.ari.efood.service;

import com.ari.efood.dto.OrderRequestDto;
import com.ari.efood.dto.PaymentOrderDto;
import com.ari.efood.exception.CustomerException;
import com.razorpay.RazorpayException;

public interface RazorpayService {
    PaymentOrderDto createOrder(OrderRequestDto orderRequest) throws RazorpayException, CustomerException;
}
