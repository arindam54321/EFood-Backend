package com.ari.efood.config;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RazorpayConfigurations {
    @Bean
    public RazorpayClient razorpayClient(
            @Value("${razorpay.arindam.id}") String id,
            @Value("${razorpay.arindam.secret}") String secret
    ) throws RazorpayException {
        return new RazorpayClient(id, secret);
    }
}
