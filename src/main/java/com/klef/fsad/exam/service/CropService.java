package com.klef.fsad.exam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.klef.fsad.exam.model.Crop;
import com.klef.fsad.exam.repository.CropRepository;
import java.util.List;

@Service
public class CropService {

    @Autowired
    private CropRepository repo;

    // Add Crop
    public Crop addCrop(Crop crop) {
        return repo.save(crop);
    }

    // Get All Crops
    public List<Crop> getAllCrops() {
        return repo.findAll();
    }

    // Get Crop By ID
    public Crop getCropById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Crop not found"));
    }

    // Get User Crops
    public List<Crop> getUserCrops(Long userId) {
        return repo.findByUserId(userId);
    }

    // Update Crop - 🔥 FIXED
    public Crop updateCrop(Long id, Crop newCrop) {
        Crop crop = getCropById(id);
        
        // Update only the fields that exist in Crop model
        if (newCrop.getName() != null) {
            crop.setName(newCrop.getName());
        }
        if (newCrop.getVariety() != null) {
            crop.setVariety(newCrop.getVariety());
        }
        if (newCrop.getArea() != null) {
            crop.setArea(newCrop.getArea());
        }
        if (newCrop.getSoilType() != null) {
            crop.setSoilType(newCrop.getSoilType());
        }
        if (newCrop.getIrrigationType() != null) {
            crop.setIrrigationType(newCrop.getIrrigationType());
        }
        if (newCrop.getHarvestDate() != null) {
            crop.setHarvestDate(newCrop.getHarvestDate());
        }

        return repo.save(crop);
    }

    // Delete Crop
    public void deleteCrop(Long id) {
        repo.deleteById(id);
    }
}