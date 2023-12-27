package com.ari.efood.repository;

import com.ari.efood.model.Food;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends MongoRepository<Food, String> {
    List<Food> findByRestaurant(String restaurant);
    List<Food> findByType(com.ari.efood.enums.Food food);
    List<Food> findByTypeAndPin(com.ari.efood.enums.Food type, String location);
}
