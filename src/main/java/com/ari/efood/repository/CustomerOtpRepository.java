package com.ari.efood.repository;

import com.ari.efood.model.CustomerOtp;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerOtpRepository extends MongoRepository<CustomerOtp, String> {
    Optional<CustomerOtp> findByEmail(String email);

    Optional<CustomerOtp> findByEmailAndOtp(String email, Integer otp);
}
