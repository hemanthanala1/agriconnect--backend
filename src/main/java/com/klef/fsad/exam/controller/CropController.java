package com.klef.fsad.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.klef.fsad.exam.model.Crop;
import com.klef.fsad.exam.service.CropService;
import com.klef.fsad.exam.util.ApiResponse;
import java.util.List;

@RestController
@RequestMapping("/api/v1/crops")
@CrossOrigin(
    origins = {"http://localhost:3000", "http://localhost:5173", "http://127.0.0.1:5173"},
    methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS},
    allowCredentials = "true",
    maxAge = 3600
)
public class CropController {

    @Autowired
    private CropService service;

    @PostMapping("/add")
    public ApiResponse<Crop> addCrop(@RequestBody Crop crop) {
        return new ApiResponse<>("success", "Crop added", service.addCrop(crop));
    }

    @GetMapping
    public ApiResponse<List<Crop>> getAllCrops() {
        return new ApiResponse<>("success", "All crops", service.getAllCrops());
    }

    @GetMapping("/{id}")
    public ApiResponse<Crop> getCropById(@PathVariable Long id) {
        return new ApiResponse<>("success", "Crop found", service.getCropById(id));
    }

    // 🔥 ADD THIS ENDPOINT
    @GetMapping("/user/{userId}")
    public ApiResponse<List<Crop>> getUserCrops(@PathVariable Long userId) {
        return new ApiResponse<>("success", "User crops", service.getUserCrops(userId));
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<String> deleteCrop(@PathVariable Long id) {
        service.deleteCrop(id);
        return new ApiResponse<>("success", "Deleted Successfully", null);
    }
}