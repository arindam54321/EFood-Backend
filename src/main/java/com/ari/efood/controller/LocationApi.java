package com.ari.efood.controller;

import com.ari.efood.auth.JWTUtils;
import com.ari.efood.dto.LocationDto;
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
            @RequestHeader(name = JWTUtils.JWT_HEADER_KEY) String jwt,
            @Valid @RequestBody LocationDto locationDto
    ) throws LocationException, JWTException {
        jwtValidatorService.validate(jwt);
        LocationDto response = service.addLocation(locationDto);
        HttpStatus status = HttpStatus.OK;
        return ResponseWrapper.entity(response, status);
    }

    @GetMapping(value = "getall")
    public ResponseEntity<ResponseWrapper<List<LocationDto>>> getAllLocations(
            @RequestHeader(name = JWTUtils.JWT_HEADER_KEY) String jwt
    ) throws JWTException {
        jwtValidatorService.validate(jwt);
        List<LocationDto> response = service.getAllLocations();
        HttpStatus status = HttpStatus.OK;
        return ResponseWrapper.entity(response, status);
    }
}
