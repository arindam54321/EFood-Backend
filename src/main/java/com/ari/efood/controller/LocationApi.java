package com.ari.efood.controller;

import com.ari.efood.dto.LocationDto;
import com.ari.efood.exception.LocationException;
import com.ari.efood.service.LocationService;
import com.ari.efood.utils.ResponseWrapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@CrossOrigin
@RestController
@RequestMapping(value = "location")
public class LocationApi {
    @Autowired
    private LocationService service;

    @PostMapping(value = "add")
    public ResponseEntity<ResponseWrapper<LocationDto>> addLocation(
            @Valid @RequestBody LocationDto locationDto
    ) throws LocationException {
        LocationDto response = service.addLocation(locationDto);
        HttpStatus status = HttpStatus.OK;
        return ResponseWrapper.entity(response, status);
    }
}
