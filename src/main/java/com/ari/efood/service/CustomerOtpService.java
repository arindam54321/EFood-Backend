package com.ari.efood.service;

import com.ari.efood.dto.CustomerOtpDto;
import com.ari.efood.exception.CustomerOtpException;

public interface CustomerOtpService {
    public static final Integer VALID_FOR_IN_SECONDS = 300;
    CustomerOtpDto generateOtp(String email);
    String validateOtp(String email, Integer otp) throws CustomerOtpException;
}
