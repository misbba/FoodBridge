package com.foodbridge.foodbridge.repository;

import com.foodbridge.foodbridge.entity.FoodClaim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodClaimRepository extends JpaRepository<FoodClaim, Long> {

}