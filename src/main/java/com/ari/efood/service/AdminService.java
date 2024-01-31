package com.ari.efood.service;

import com.ari.efood.dto.AdminDto;
import com.ari.efood.exception.AdminException;

public interface AdminService {
    boolean doesExist(String username, String password, String mfa);

    AdminDto add(AdminDto admin) throws AdminException;
}
