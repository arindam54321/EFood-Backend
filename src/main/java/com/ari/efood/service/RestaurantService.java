package com.ari.efood.service;

import com.ari.efood.dto.RestaurantDto;
import com.ari.efood.exception.RestaurantException;

import java.util.List;

public interface RestaurantService {
    List<RestaurantDto> getAll();
    List<RestaurantDto> getByLocation(String location);
    RestaurantDto addRestaurant(RestaurantDto restaurantDto) throws RestaurantException;
    String deleteRestaurant(String id) throws RestaurantException;
}
