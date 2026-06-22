package com.foodbridge.foodbridge.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "food_claim")
public class FoodClaim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "claimer_name")
    private String claimerName;

    @Column(name = "food_name")
    private String foodName;

    @Column(name = "location")
    private String location;

    public Long getId() {
        return id;
    }

    public String getClaimerName() {
        return claimerName;
    }

    public void setClaimerName(String claimerName) {
        this.claimerName = claimerName;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}