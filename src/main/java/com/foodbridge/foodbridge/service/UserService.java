package com.foodbridge.foodbridge.service;

import com.foodbridge.foodbridge.User;
import com.foodbridge.foodbridge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
}