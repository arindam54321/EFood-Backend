package com.ari.efood.service;

import com.ari.efood.dto.FoodDto;
import com.ari.efood.dto.RestaurantDto;
import com.ari.efood.exception.FoodException;
import com.ari.efood.model.Food;
import com.ari.efood.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodServiceImpl implements FoodService {
    @Autowired
    private FoodRepository repository;
    @Autowired
    private RestaurantService restaurantService;

    @Override
    public FoodDto addFood(FoodDto foodDto) throws FoodException {
        Optional<RestaurantDto> restaurant = restaurantService.getById(foodDto.getRestaurant());
        if (restaurant.isEmpty()) {
            throw new FoodException("Please enter a valid Restaurant code");
        }

        foodDto.setPin(restaurant.get().getLocation());
        var food = repository.save(foodDto.toEntity());
        return food.toDto();
    }

    @Override
    public List<FoodDto> getAll() {
        return repository.findAll().stream().map(Food::toDto).toList();
    }

    @Override
    public List<FoodDto> getByRestaurant(String restaurant) throws FoodException {
        if (!restaurantService.doesExist(restaurant)) {
            throw new FoodException("Please enter a valid Restaurant code");
        }

        return repository.findByRestaurant(restaurant).stream().map(Food::toDto).toList();
    }

    @Override
    public List<FoodDto> getByFoodType(com.ari.efood.enums.Food food) {
        return repository.findByType(food).stream().map(Food::toDto).toList();
    }

    @Override
    public FoodDto findById(String id) throws FoodException {
        if (!repository.existsById(id)) {
            throw new FoodException("Invalid Id");
        }
        return repository.findById(id).get().toDto();
    }

    @Override
    public List<FoodDto> findByTypeAndLocation(com.ari.efood.enums.Food type, String location) {
        return repository.findByTypeAndPin(type, location).stream().map(Food::toDto).toList();
    }

    @Override
    public List<FoodDto> findByLocation(String location) {
        return repository.findByPin(location).stream().map(Food::toDto).toList();
    }
}
