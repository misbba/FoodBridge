package com.foodbridge.foodbridge.controller;

import com.foodbridge.foodbridge.entity.Food;
import com.foodbridge.foodbridge.repository.FoodRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import com.foodbridge.foodbridge.service.EmailService;

import java.util.List;

@Controller
public class FoodController {

    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private EmailService emailService;

    @GetMapping("/")
    public String home() {
        return "redirect:/index.html";
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
            @RequestParam String location,
            @RequestParam String expiryDate,
            @RequestParam("image") MultipartFile image) throws IOException {

        String imageName = image.getOriginalFilename();
        String uploadDir = "src/main/resources/static/uploads";



        File folder = new File(uploadDir);

        if (!folder.exists()) {
            folder.mkdirs();
        }

        File destination = new File(folder, imageName);

        image.transferTo(destination);
        Food food = new Food();

        food.setFoodName(foodName);
        food.setQuantity(quantity);
        food.setLocation(location);
        food.setExpiryDate(expiryDate);
        food.setStatus("Available");
        food.setImageName(imageName);

        foodRepository.save(food);

// Send Email Notification
        emailService.sendMail(
                "nishamisbba@gmail.com",
                "FoodBridge - New Food Donation",
                "A new food donation has been added.\n\n" +
                        "Food Name: " + foodName +
                        "\nQuantity: " + quantity +
                        "\nLocation: " + location +
                        "\nExpiry Date: " + expiryDate
        );

        System.out.println("Food Saved Successfully");
        System.out.println("Donation Email Sent Successfully");

        return "redirect:/view-donations.html";

    }

    @GetMapping("/foods")
    @ResponseBody
    public List<Food> getFoods() {
        return foodRepository.findAll();
    }
    @PostMapping("/acceptDonation/{id}")
    @ResponseBody
    public String acceptDonation(@PathVariable Long id,
                                 @RequestParam String claimerName){

        Food food = foodRepository.findById(id).orElse(null);

        if(food != null){

            food.setStatus("Claimed");
            food.setClaimerName(claimerName);

            foodRepository.save(food);

            emailService.sendMail(
                    "nishamisbba@gmail.com",
                    "FoodBridge - Food Claimed",
                    "Food Name: " + food.getFoodName() +
                            "\nClaimer: " + claimerName +
                            "\nLocation: " + food.getLocation()
            );

            return "Donation Accepted Successfully";
        }

        return "Food Not Found";

    }

    @GetMapping("/searchFood")
    @ResponseBody
    public List<Food> searchFood(@RequestParam String keyword) {

        return foodRepository.findByFoodNameContainingIgnoreCase(keyword);

    }

    @GetMapping("/totalDonations")
    @ResponseBody
    public long totalDonations() {
        return foodRepository.count();
    }

    @GetMapping("/availableFoods")
    @ResponseBody
    public long availableFoods() {
        return foodRepository.countByStatus("Available");
    }
    @GetMapping("/claims")
    @ResponseBody
    public List<Food> getClaims() {

        return foodRepository.findByStatus("Claimed");

    }
    @GetMapping("/claimedFoods")
    @ResponseBody
    public long claimedFoods() {
        return foodRepository.countByStatus("Claimed");
    }

    @GetMapping("/recentDonations")
    @ResponseBody
    public List<Food> recentDonations() {
        return foodRepository.findTop5ByOrderByIdDesc();
    }

    @PostMapping("/deleteDonation/{id}")
    @ResponseBody
    public String deleteDonation(@PathVariable Long id){

        foodRepository.deleteById(id);

        return "Donation Deleted Successfully";

    }
    @PostMapping("/updateDonation")
    @ResponseBody
    public String updateDonation(@RequestParam Long id,
                                 @RequestParam String foodName,
                                 @RequestParam String quantity,
                                 @RequestParam String location,
                                 @RequestParam String expiryDate)

    {

        Food food = foodRepository.findById(id).orElse(null);

        if(food != null){

            food.setFoodName(foodName);
            food.setQuantity(quantity);
            food.setLocation(location);
            food.setExpiryDate(expiryDate);

            foodRepository.save(food);

            return "Donation Updated Successfully";
        }

        return "Food Not Found";
    }
    @PostMapping("/claimFood")
    public String claimFood(@RequestParam String claimerName,
                            @RequestParam String foodName,
                            @RequestParam String location) {

        Food food = foodRepository.findByFoodName(foodName);

        if (food != null) {

            food.setClaimerName(claimerName);
            food.setStatus("Claimed");

            foodRepository.save(food);

            emailService.sendMail(
                    "nishamisbba@gmail.com",
                    "FoodBridge - Food Claimed",
                    "Food Name: " + food.getFoodName() +
                            "\nClaimer Name: " + claimerName +
                            "\nLocation: " + food.getLocation()
            );

            System.out.println("Email Sent Successfully");
        }

        return "redirect:/view-claims.html";
    }
    @GetMapping("/expiredFoods")
    @ResponseBody
    public List<Food> expiredFoods() {

        List<Food> foods = foodRepository.findAll();

        LocalDate today = LocalDate.now();

        List<Food> expired = new ArrayList<>();

        for (Food food : foods) {

            if (LocalDate.parse(food.getExpiryDate()).isBefore(today)) {
                expired.add(food);
            }

        }

        return expired;
    }

    }

