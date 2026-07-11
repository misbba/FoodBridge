package com.foodbridge.foodbridge.controller;

import com.foodbridge.foodbridge.entity.FoodRequest;
import com.foodbridge.foodbridge.repository.FoodRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.foodbridge.foodbridge.service.EmailService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class FoodRequestController {

    @Autowired
    private FoodRequestRepository foodRequestRepository;
    @Autowired
    private EmailService emailService;
    @PostMapping("/requestFood")
    public String requestFood(
            @RequestParam String requesterName,
            @RequestParam String foodNeeded,
            @RequestParam String quantity,
            @RequestParam String location) {

        FoodRequest request = new FoodRequest();

        request.setRequesterName(requesterName);
        request.setFoodNeeded(foodNeeded);
        request.setQuantity(quantity);
        request.setLocation(location);

        foodRequestRepository.save(request);

        emailService.sendMail(
                "nishamisbba@gmail.com",
                "FoodBridge - New Food Request",
                "Requester Name: " + requesterName +
                        "\nFood Needed: " + foodNeeded +
                        "\nQuantity: " + quantity +
                        "\nLocation: " + location
        );

        return "redirect:/view-requests.html";
    }


    @GetMapping("/foodRequests")
    @ResponseBody
    public List<FoodRequest> getRequests() {
        return foodRequestRepository.findAll();
    }
    @GetMapping("/totalRequests")
    @ResponseBody
    public long totalRequests() {
        return foodRequestRepository.count();
    }
}