package com.ari.efood.controller;

import com.ari.efood.dto.RestaurantDto;
import com.ari.efood.enums.Role;
import com.ari.efood.exception.JWTException;
import com.ari.efood.exception.RestaurantException;
import com.ari.efood.service.JWTValidatorService;
import com.ari.efood.service.RestaurantService;
import com.ari.efood.utils.ResponseWrapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@CrossOrigin
@RestController
@RequestMapping(value = "restaurant")
public class RestaurantApi {
    @Autowired
    private RestaurantService service;
    @Autowired
    private JWTValidatorService jwtValidatorService;

    @GetMapping(value = "getall")
    public ResponseEntity<ResponseWrapper<List<RestaurantDto>>> getAll() {
        List<RestaurantDto> response = service.getAll();
        HttpStatus status = HttpStatus.OK;
        return ResponseWrapper.entity(response, status);
    }

    @GetMapping(value = "get/{location}")
    public ResponseEntity<ResponseWrapper<List<RestaurantDto>>> getByLocation(
            @Pattern(regexp = "[1-9][0-9]{5}", message = "Location PIN should be 6 digit numeric")
            @PathParam(value = "location")String location
    ) {
        List<RestaurantDto> response = service.getByLocation(location);
        HttpStatus status = HttpStatus.OK;
        return ResponseWrapper.entity(response, status);
    }

    @PostMapping(value = "add")
    public ResponseEntity<ResponseWrapper<RestaurantDto>> addRestaurant(
            @Valid @RequestBody RestaurantDto restaurantDto,
            @RequestHeader(value = JWTValidatorService.JWT_HEADER_KEY) String token
    ) throws JWTException, RestaurantException {
        jwtValidatorService.validate(token, Role.ADMIN);
        RestaurantDto response = service.addRestaurant(restaurantDto);
        HttpStatus status = HttpStatus.OK;
        return ResponseWrapper.entity(response, status);
    }

    @DeleteMapping(value = "delete")
    public ResponseEntity<ResponseWrapper<String>> deleteRestaurant(
            @RequestParam(value = "id") String id,
            @RequestHeader(value = JWTValidatorService.JWT_HEADER_KEY) String token
    ) throws JWTException, RestaurantException {
        jwtValidatorService.validate(token, Role.ADMIN);
        String response = service.deleteRestaurant(id);
        HttpStatus status = HttpStatus.OK;
        return ResponseWrapper.entity(response, status);
    }
}
