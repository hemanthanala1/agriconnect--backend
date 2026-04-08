package com.klef.fsad.exam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klef.fsad.exam.model.Crop;
import com.klef.fsad.exam.service.CropService;
import com.klef.fsad.exam.util.ApiResponse;

@RestController
@RequestMapping("/api/v1/crops")
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

    @DeleteMapping("/delete/{id}")
    public ApiResponse<String> deleteCrop(@PathVariable Long id) {
        service.deleteCrop(id);
        return new ApiResponse<>("success", "Deleted Successfully", null);
    }
}