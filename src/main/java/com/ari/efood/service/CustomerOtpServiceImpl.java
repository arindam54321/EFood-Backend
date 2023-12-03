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
        String subject = "[%1$d] : EFood login/signup OTP".formatted(otp);
        String body = """
                Dear Customer,
                <br><br>
                Your OTP for login/signup in EFood application is <span style="color:red">%1$d</span>
                <br>
                Use this OTP to validate your email. Don't share this email to anyone.
                <br>
                <h6>Don't reply to this email</h6>
                """.formatted(otp);

        CompletableFuture.runAsync(() -> {
            try {
                emailService.sendEmail(subject, body, List.of(email), null, null);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }, executor);
    }
}
