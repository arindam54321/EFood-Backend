package com.ari.efood.service;

import com.ari.efood.exception.CustomerOtpException;

public interface CustomerOtpService {
    String generateOtp(String email);

    String validateOtp(String email, Integer otp) throws CustomerOtpException;
}
