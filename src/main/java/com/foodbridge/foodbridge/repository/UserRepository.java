package com.foodbridge.foodbridge.repository;

import com.foodbridge.foodbridge.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}