package com.foodbridge.foodbridge.controller;

import com.foodbridge.foodbridge.entity.FoodRequest;
import com.foodbridge.foodbridge.repository.FoodRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class FoodRequestController {

    @Autowired
    private FoodRequestRepository foodRequestRepository;

    @PostMapping("/requestFood")
    public String requestFood(
            @RequestParam String requesterName,
            @RequestParam String foodName,
            @RequestParam String location) {

        FoodRequest request = new FoodRequest();

        request.setRequesterName(requesterName);
        request.setFoodNeeded(foodName);
        request.setLocation(location);

        foodRequestRepository.save(request);

        System.out.println("Food Request Saved Successfully");

        return "redirect:/claim-food.html";
    }

    @GetMapping("/requests")
    @ResponseBody
    public List<FoodRequest> getRequests() {
        return foodRequestRepository.findAll();
    }
}