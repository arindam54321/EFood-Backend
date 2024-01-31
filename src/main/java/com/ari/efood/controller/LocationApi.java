package com.ari.efood.controller;

import com.ari.efood.auth.JWTUtils;
import com.ari.efood.dto.LocationDto;
import com.ari.efood.enums.Role;
import com.ari.efood.exception.JWTException;
import com.ari.efood.exception.LocationException;
import com.ari.efood.service.JWTValidatorService;
import com.ari.efood.service.LocationService;
import com.ari.efood.utils.ResponseWrapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@CrossOrigin
@RestController
@RequestMapping(value = "location")
public class LocationApi {
    @Autowired
    private LocationService service;
    @Autowired
    private JWTValidatorService jwtValidatorService;

    @PostMapping(value = "add")
    public ResponseEntity<ResponseWrapper<LocationDto>> addLocation(
            @Valid @RequestBody LocationDto locationDto,
            @RequestHeader(name = JWTUtils.JWT_HEADER_KEY) String token
    ) throws LocationException, JWTException {
        jwtValidatorService.validate(token, Role.ADMIN);
        LocationDto response = service.addLocation(locationDto);
        HttpStatus status = HttpStatus.OK;
        return ResponseWrapper.entity(response, status);
    }

    @GetMapping(value = "getall")
    public ResponseEntity<ResponseWrapper<List<LocationDto>>> getAllLocations() throws JWTException {
        List<LocationDto> response = service.getAllLocations();
        HttpStatus status = HttpStatus.OK;
        return ResponseWrapper.entity(response, status);
    }

    @DeleteMapping(value = "delete")
    public ResponseEntity<ResponseWrapper<String>> deleteLocation(
            String pin,
            @RequestHeader(name = JWTUtils.JWT_HEADER_KEY) String token
    ) throws LocationException, JWTException {
        jwtValidatorService.validate(token, Role.ADMIN);
        String response = service.deleteLocation(pin);
        HttpStatus status = HttpStatus.OK;
        return ResponseWrapper.entity(response, status);
    }
}
