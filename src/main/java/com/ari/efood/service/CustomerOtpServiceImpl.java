package com.ari.efood.service;

import com.ari.efood.dto.CustomerOtpDto;
import com.ari.efood.exception.CustomerOtpException;
import com.ari.efood.model.CustomerOtp;
import com.ari.efood.repository.CustomerOtpRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class CustomerOtpServiceImpl implements CustomerOtpService {

    @Autowired
    CustomerOtpRepository repository;
    @Autowired
    EmailService emailService;
    @Autowired
    ThreadPoolTaskExecutor executor;

    static Integer getOtp() {
        int min = 100001;
        int max = 999999;
        return min + (int)(Math.random() * ((max - min) + 1));
    }

    @Override
    public CustomerOtpDto generateOtp(String email) {
        Integer otp = CustomerOtpServiceImpl.getOtp();
        Long validTill = System.currentTimeMillis() + CustomerOtpService.VALID_FOR_IN_SECONDS * 1000;
        Optional<CustomerOtp> customerOtp = repository.findByEmail(email);
        CustomerOtp data;
        if (customerOtp.isPresent()) {
            data = customerOtp.get();
            data.setOtp(otp);
            data.setValidTill(validTill);
        } else {
            data = CustomerOtp.builder()
                    .email(email)
                    .otp(otp)
                    .validTill(validTill)
                    .build();
        }
        repository.save(data);

        this.sendOtp(email, otp);
        return CustomerOtpDto.builder()
                .email(email)
                .validFor(CustomerOtpService.VALID_FOR_IN_SECONDS)
                .build();
    }

    @Override
    public String validateOtp(String email, Integer otp) throws CustomerOtpException {
        Optional<CustomerOtp> customerOtp = repository.findByEmailAndOtp(email, otp);
        if (customerOtp.isPresent() &&
            customerOtp.get().getValidTill() >= System.currentTimeMillis()) {
            return "OTP validated";
        } else {
            throw new CustomerOtpException("OTP invalid");
        }
    }

    private void sendOtp(String email, Integer otp) {
        String subject = "Hungry Hub Login/Signup OTP";
        String body = """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>OTP Verification</title>
                    <style>
                        body {
                            font-family: Arial, sans-serif;
                            background-color: #f4f4f4;
                            margin: 0;
                            padding: 0;
                        }
                
                        .container {
                            max-width: 600px;
                            margin: 50px auto;
                            background-color: #ffffff;
                            padding: 20px;
                            border-radius: 5px;
                            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                        }
                
                        h1 {
                            color: #333333;
                        }
                
                        p {
                            color: #666666;
                        }
                
                        .otp-code {
                            font-size: 24px;
                            font-weight: bold;
                            color: #009688;
                        }
                
                        .btn {
                            display: inline-block;
                            padding: 10px 20px;
                            background-color: #009688;
                            color: #ffffff;
                            text-decoration: none;
                            border-radius: 3px;
                        }
                
                        .footer {
                            margin-top: 20px;
                            text-align: center;
                            color: #999999;
                        }
                    </style>
                </head>
                
                <body>
                    <div class="container">
                        <h1>OTP Verification</h1>
                        <p>Your OTP for Login/Signup in HungryHub application is:</p>
                        <p class="otp-code">%1$d</p>
                        <p>This code is valid for <span style="color:#009688">%2$d Minutes</span></p>
                        <p>Please use this code to verify your email address.</p>
                        <p>If you didn't request this code, you can safely ignore this email.</p>
                        <p>Thank you!</p>
                        <p class="footer">This email was sent by HungryHub</p>
                        <hr>
                        <h6>This email is system generated. Don't reply to this email<h6>
                    </div>
                </body>
                </html>
                """.formatted(otp, CustomerOtpService.VALID_FOR_IN_SECONDS / 60);

        CompletableFuture.runAsync(() -> {
            try {
                emailService.sendEmail(subject, body, List.of(email), null, null);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }, executor);
    }
}
