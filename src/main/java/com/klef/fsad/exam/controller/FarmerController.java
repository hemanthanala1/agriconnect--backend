package com.klef.fsad.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klef.fsad.exam.model.Farmer;
import com.klef.fsad.exam.service.FarmerService;
import com.klef.fsad.exam.util.ApiResponse;

@RestController
@RequestMapping("/api/v1/farmer")
public class FarmerController {

    @Autowired
    private FarmerService service;

    @PostMapping("/profile")
    public ApiResponse<Farmer> createProfile(@RequestBody Farmer farmer, Authentication auth) {
        Farmer saved = service.createOrUpdateFarmer(auth.getName(), farmer);
        return new ApiResponse<>("success", "Profile saved", saved);
    }

    @GetMapping("/profile")
    public ApiResponse<Farmer> getProfile(Authentication auth) {
        return new ApiResponse<>("success", "Profile fetched", service.getFarmerProfile(auth.getName()));
    }
}