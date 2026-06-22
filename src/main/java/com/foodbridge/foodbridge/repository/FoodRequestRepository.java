package com.foodbridge.foodbridge.repository;

import com.foodbridge.foodbridge.entity.FoodRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRequestRepository extends JpaRepository<FoodRequest, Long> {

}