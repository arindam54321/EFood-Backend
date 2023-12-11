package com.ari.efood.service;

import com.ari.efood.dto.RestaurantDto;
import com.ari.efood.exception.RestaurantException;
import com.ari.efood.model.Restaurant;
import com.ari.efood.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    private RestaurantRepository repository;
    @Autowired
    private LocationService locationService;

    @Override
    public List<RestaurantDto> getAll() {
        return repository.findAll().stream().map(Restaurant::toDto).toList();
    }

    @Override
    public List<RestaurantDto> getByLocation(String location) {
        return repository.findByLocation(location).stream().map(Restaurant::toDto).toList();
    }

    @Override
    public RestaurantDto addRestaurant(RestaurantDto restaurantDto) throws RestaurantException {
        if (!locationService.doesExist(restaurantDto.getLocation())) {
            throw new RestaurantException("Location PIN doesn't exist");
        }

        if (repository.existsById(restaurantDto.getId())) {
            throw new RestaurantException("Restaurant with same ID already exists");
        }

        return repository.save(restaurantDto.toEntity()).toDto();
    }

    @Override
    public String deleteRestaurant(String id) throws RestaurantException {
        if (!repository.existsById(id)) {
            throw new RestaurantException("No Restaurant found with the given ID");
        }

        repository.deleteById(id);
        return "Restaurant deleted with ID: " + id;
    }
}
