package com.ari.efood.service;

import com.ari.efood.dto.CustomerDto;
import com.ari.efood.exception.CustomerException;
import com.ari.efood.model.Customer;
import com.ari.efood.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository repository;

    @Override
    public List<CustomerDto> getAll() {
        return repository.findAll().stream().map(Customer::toDto).toList();
    }

    @Override
    public CustomerDto addCustomer(CustomerDto customer) throws CustomerException {
        Optional<Customer> customer1 = repository.findByEmail(customer.getEmail());

        if (customer1.isPresent()) {
            throw new CustomerException("Customer already found with given email id");
        }

        return repository.save(customer.toEntity()).toDto();
    }

    @Override
    public String deleteCustomer(String email) throws CustomerException {
        Optional<Customer> customer = repository.findByEmail(email);

        if (customer.isEmpty()) {
            throw new CustomerException("No customer found with the given email id");
        }

        repository.delete(customer.get());
        return "Customer deleted with Email: " + email;
    }

    @Override
    public boolean doesCustomerExist(String email) {
        Optional<Customer> customer = repository.findByEmail(email);
        return customer.isPresent();
    }

    @Override
    public CustomerDto getCustomer(String email) throws CustomerException {
        Optional<Customer> customer = repository.findByEmail(email);
        if (customer.isEmpty()) {
            throw new CustomerException("No customer found with the given email id");
        }
        return customer.get().toDto();
    }

    @Override
    public boolean doesExists(String id, String name, String email) {
        Optional<Customer> customer = repository.findByEmail(email);
        if (customer.isEmpty() || !Objects.equals(customer.get().getId(), id)) {
            return false;
        }
        return true;
    }
}
