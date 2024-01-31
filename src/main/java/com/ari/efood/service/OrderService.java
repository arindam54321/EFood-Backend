package com.ari.efood.service;

import com.ari.efood.dto.OrderDto;

import java.util.List;

public interface OrderService {
    OrderDto placeOrder(OrderDto orderDto);

    List<OrderDto> findByEmail(String email);
}
