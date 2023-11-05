package com.ari.efood.service;

import com.ari.efood.dto.CustomerDto;
import com.ari.efood.exception.CustomerException;

import java.util.List;

public interface CustomerService {
    List<CustomerDto> getAll();

    CustomerDto addCustomer(CustomerDto customer) throws CustomerException;

    String deleteCustomer(String email) throws CustomerException;

    boolean doesCustomerExist(String email);

    CustomerDto getCustomer(String email) throws CustomerException;
}
