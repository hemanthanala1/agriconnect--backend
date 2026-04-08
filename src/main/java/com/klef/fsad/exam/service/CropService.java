package com.klef.fsad.exam.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.fsad.exam.model.Crop;
import com.klef.fsad.exam.repository.CropRepository;

@Service
public class CropService {

    @Autowired
    private CropRepository repo;

    public Crop addCrop(Crop crop) {
        return repo.save(crop);
    }

    public List<Crop> getAllCrops() {
        return repo.findAll();
    }

    public Crop getCropById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Crop not found"));
    }

    public Crop updateCrop(Long id, Crop newCrop) {
        Crop crop = getCropById(id);

        crop.setName(newCrop.getName());
        crop.setType(newCrop.getType());
        crop.setSeason(newCrop.getSeason());
        crop.setPrice(newCrop.getPrice());
        crop.setDescription(newCrop.getDescription());

        return repo.save(crop);
    }

    public void deleteCrop(Long id) {
        repo.deleteById(id);
    }
}