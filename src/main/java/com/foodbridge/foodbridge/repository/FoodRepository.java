package com.foodbridge.foodbridge.repository;

import com.foodbridge.foodbridge.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {

    Food findByFoodName(String foodName);

    long countByStatus(String status);

    List<Food> findByStatus(String status);
    List<Food> findTop5ByOrderByIdDesc();
    List<Food> findByFoodNameContainingIgnoreCase(String foodName);

}