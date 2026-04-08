package com.klef.fsad.exam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.fsad.exam.model.Farmer;
import com.klef.fsad.exam.model.User;
import com.klef.fsad.exam.repository.FarmerRepository;
import com.klef.fsad.exam.repository.UserRepository;

@Service
public class FarmerService {

    @Autowired
    private FarmerRepository farmerRepo;

    @Autowired
    private UserRepository userRepo;

    // CREATE / UPDATE PROFILE
    public Farmer createOrUpdateFarmer(String email, Farmer farmerData) {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Farmer farmer = farmerRepo.findByUser(user).orElse(new Farmer());

        farmer.setUser(user);
        farmer.setLandDetails(farmerData.getLandDetails());
        farmer.setLocation(farmerData.getLocation());
        farmer.setCropsGrown(farmerData.getCropsGrown());

        return farmerRepo.save(farmer);
    }

    // GET PROFILE
    public Farmer getFarmerProfile(String email) {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return farmerRepo.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Farmer profile not found"));
    }

    // 🔥 ADD TASK
    public Farmer addTask(String email, String task) {

        Farmer farmer = getFarmerProfile(email);

        farmer.getTasks().add(task);

        return farmerRepo.save(farmer);
    }

    // 🔥 GET TASKS
    public List<String> getTasks(String email) {

        return getFarmerProfile(email).getTasks();
    }

    // 🔥 DELETE TASK
    public Farmer deleteTask(String email, String task) {

        Farmer farmer = getFarmerProfile(email);

        farmer.getTasks().remove(task);

        return farmerRepo.save(farmer);
    }
}