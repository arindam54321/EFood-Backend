package com.ari.efood.service;

import com.ari.efood.dto.OrderDto;
import com.ari.efood.model.Order;
import com.ari.efood.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository repository;

    @Override
    public OrderDto placeOrder(OrderDto orderDto) {
        orderDto.setEpochTime(System.currentTimeMillis());
        Order placedOrder = repository.save(orderDto.toEntity());
        return placedOrder.toDto();
    }

    @Override
    public List<OrderDto> findByEmail(String email) {
        List<Order> orders = repository.findByEmail(email);
        return orders.stream().map(Order::toDto).toList();
    }
}
