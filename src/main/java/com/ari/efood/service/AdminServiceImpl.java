package com.ari.efood.service;

import com.ari.efood.dto.AdminDto;
import com.ari.efood.exception.AdminException;
import com.ari.efood.model.Admin;
import com.ari.efood.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository repository;

    @Override
    public boolean doesExist(String username, String password, String mfa) {
        return repository.findByUsernameAndPasswordAndMfa(username, password, mfa).isPresent();
    }

    @Override
    public AdminDto add(AdminDto adminDto) throws AdminException {
        Optional<Admin> admin = repository.findByUsername(adminDto.getUsername());

        if (admin.isPresent()) {
            throw new AdminException("Admin with same username already exists");
        }

        return repository.save(adminDto.toEntity()).toDto();
    }
}
