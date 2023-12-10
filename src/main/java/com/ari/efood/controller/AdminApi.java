package com.ari.efood.controller;

import com.ari.efood.auth.JWTUtils;
import com.ari.efood.dto.AdminDto;
import com.ari.efood.enums.Role;
import com.ari.efood.exception.AdminException;
import com.ari.efood.exception.JWTException;
import com.ari.efood.service.AdminService;
import com.ari.efood.service.JWTValidatorService;
import com.ari.efood.utils.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "admin")
public class AdminApi {
    @Autowired
    private AdminService service;
    @Autowired
    private JWTValidatorService jwtValidatorService;

    @PostMapping(value = "add")
    public ResponseEntity<ResponseWrapper<AdminDto>> addAdmin(
            @RequestHeader(name = JWTUtils.JWT_HEADER_KEY) String jwt,
            @RequestBody AdminDto admin
    ) throws AdminException, JWTException {
        jwtValidatorService.validate(jwt, Role.ADMIN);
        AdminDto response = service.add(admin);
        HttpStatus status = HttpStatus.OK;
        return ResponseWrapper.entity(response, status);
    }
}
