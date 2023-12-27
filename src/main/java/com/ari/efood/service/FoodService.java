package com.ari.efood.service;

import com.ari.efood.dto.FoodDto;
import com.ari.efood.enums.Food;
import com.ari.efood.exception.FoodException;

import java.util.List;

public interface FoodService {
    FoodDto addFood(FoodDto foodDto) throws FoodException;

    List<FoodDto> getAll();

    List<FoodDto> getByRestaurant(String restaurant) throws FoodException;

    List<FoodDto> getByFoodType(Food food);

    FoodDto findById(String id) throws FoodException;

    List<FoodDto> findByTypeAndLocation(Food type, String location);
}
