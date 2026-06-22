package com.foodbridge.foodbridge.controller;

import com.foodbridge.foodbridge.entity.FoodClaim;
import com.foodbridge.foodbridge.repository.FoodClaimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class FoodClaimController {

    @Autowired
    private FoodClaimRepository foodClaimRepository;

    @PostMapping("/claimFood")
    public String claimFood(
            @RequestParam String claimerName,
            @RequestParam String foodName,
            @RequestParam String location) {

        FoodClaim claim = new FoodClaim();

        claim.setClaimerName(claimerName);
        claim.setFoodName(foodName);
        claim.setLocation(location);

        foodClaimRepository.save(claim);

        System.out.println("Food Claimed Successfully");

        return "redirect:/view-claims.html";
    }

    @GetMapping("/claims")
    @ResponseBody
    public List<FoodClaim> getClaims() {
        return foodClaimRepository.findAll();
    }
}