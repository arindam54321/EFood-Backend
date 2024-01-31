package com.ari.efood.controller;

import com.ari.efood.auth.JWTUtils;
import com.ari.efood.dto.FoodDto;
import com.ari.efood.enums.Food;
import com.ari.efood.enums.Role;
import com.ari.efood.exception.FoodException;
import com.ari.efood.exception.JWTException;
import com.ari.efood.service.FoodService;
import com.ari.efood.service.JWTValidatorService;
import com.ari.efood.utils.ResponseWrapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@CrossOrigin
@RestController
@RequestMapping(value = "food")
public class FoodApi {
    @Autowired
    private FoodService service;
    @Autowired
    private JWTValidatorService jwtValidatorService;

    @PostMapping(value = "add")
    public ResponseEntity<ResponseWrapper<FoodDto>> addFood(
            @Valid @RequestBody FoodDto foodDto,
            @RequestHeader(name = JWTUtils.JWT_HEADER_KEY) String token
    ) throws JWTException, FoodException {
        jwtValidatorService.validate(token, Role.ADMIN);
        FoodDto response = service.addFood(foodDto);
        HttpStatus status = HttpStatus.OK;
        return ResponseWrapper.entity(response, status);
    }

    @GetMapping(value = "findbyid")
    public ResponseEntity<ResponseWrapper<FoodDto>> findById(
            @RequestParam(value = "id") String id
    ) throws FoodException {
        FoodDto response = service.findById(id);
        HttpStatus status = HttpStatus.OK;
        return ResponseWrapper.entity(response, status);
    }

    @GetMapping(value = "getall")
    public ResponseEntity<ResponseWrapper<List<FoodDto>>> getAll() {
        List<FoodDto> response = service.getAll();
        HttpStatus status = HttpStatus.OK;
        return ResponseWrapper.entity(response, status);
    }

    @GetMapping(value = "findbytypeandlocation")
    public ResponseEntity<ResponseWrapper<List<FoodDto>>> findByTypeAndLocation(
            @RequestParam(value = "type") Food type,
            @Pattern(regexp = "[1-9][0-9]{5}", message = "Location PIN should be 6 digit numeric")
            @RequestParam(value = "location") String location
    ) {
        List<FoodDto> response = service.findByTypeAndLocation(type, location);
        HttpStatus status = HttpStatus.OK;
        return ResponseWrapper.entity(response, status);
    }

    @GetMapping(value = "findbylocation")
    public ResponseEntity<ResponseWrapper<List<FoodDto>>> findByLocation(
            @Pattern(regexp = "[1-9][0-9]{5}", message = "Location PIN should be 6 digit numeric")
            @RequestParam(value = "location") String location
    ) {
        List<FoodDto> response = service.findByLocation(location);
        HttpStatus status = HttpStatus.OK;
        return ResponseWrapper.entity(response, status);
    }

    @GetMapping(value = "findbyrestaurant")
    public ResponseEntity<ResponseWrapper<List<FoodDto>>> getByRestaurant(
            @RequestParam(name = "restaurant") String restaurant
    ) throws FoodException {
        List<FoodDto> response = service.getByRestaurant(restaurant);
        HttpStatus status = HttpStatus.OK;
        return ResponseWrapper.entity(response, status);
    }

    @GetMapping(value = "findbytype")
    public ResponseEntity<ResponseWrapper<List<FoodDto>>> getByFoodType(
            @RequestParam(name = "type") Food food
    ) throws FoodException {
        List<FoodDto> response = service.getByFoodType(food);
        HttpStatus status = HttpStatus.OK;
        return ResponseWrapper.entity(response, status);
    }
}
