package com.foodbridge.foodbridge.controller;

import com.foodbridge.foodbridge.entity.Food;
import com.foodbridge.foodbridge.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class FoodController {

    @Autowired
    private FoodRepository foodRepository;

    @GetMapping("/")
    public String home() {
        return "redirect:/index.html";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "redirect:/dashboard.html";
    }

    @GetMapping("/donate")
    public String donate() {
        return "redirect:/donate-food.html";
    }

    @GetMapping("/request")
    public String request() {
        return "redirect:/request-food.html";
    }

    @GetMapping("/claim")
    public String claim() {
        return "redirect:/claim-food.html";
    }

    @PostMapping("/donateFood")
    public String donateFood(
            @RequestParam String foodName,
            @RequestParam String quantity,
            @RequestParam String location) {

        Food food = new Food();

        food.setFoodName(foodName);
        food.setQuantity(quantity);
        food.setLocation(location);

        foodRepository.save(food);

        System.out.println("Food Saved Successfully");

        return "redirect:/request-food.html";
    }

    @GetMapping("/foods")
    @ResponseBody
    public List<Food> getFoods() {
        return foodRepository.findAll();
    }
}