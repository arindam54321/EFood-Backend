package com.ari.efood.service;

import com.ari.efood.dto.CustomerDto;
import com.ari.efood.dto.OrderRequestDto;
import com.ari.efood.dto.PaymentOrderDto;
import com.ari.efood.dto.RZPOrder;
import com.ari.efood.exception.CustomerException;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RazorpayServiceImpl implements RazorpayService {

    @Value("${razorpay.arindam.id}")
    private String rzp_key;

    @Autowired
    private RazorpayClient client;
    @Autowired
    private CustomerService customerService;

    @Override
    public PaymentOrderDto createOrder(OrderRequestDto orderRequest) throws RazorpayException, CustomerException {
        CustomerDto customer = customerService.getCustomer(orderRequest.getCustomerEmail());

        JSONObject razorpayOrderRequest = new JSONObject();
        razorpayOrderRequest.put("amount", orderRequest.getAmount());
        razorpayOrderRequest.put("currency", "INR");
        razorpayOrderRequest.put("receipt", "HH#" + System.currentTimeMillis());

        Order order = client.orders.create(razorpayOrderRequest);
        PaymentOrderDto response = PaymentOrderDto.builder()
                .razorpay_key(rzp_key)
                .order(RZPOrder.serialize(order))
                .customer(customer)
                .build();
        return response;
    }
}
