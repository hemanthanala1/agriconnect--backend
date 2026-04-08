package com.klef.fsad.exam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.fsad.exam.model.Expert;
import com.klef.fsad.exam.model.User;
import com.klef.fsad.exam.repository.ExpertRepository;
import com.klef.fsad.exam.repository.UserRepository;

@Service
public class ExpertService {

    @Autowired
    private ExpertRepository expertRepo;

    @Autowired
    private UserRepository userRepo;

    public Expert createOrUpdateExpert(String email, Expert data) {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Expert expert = expertRepo.findByUser(user).orElse(new Expert());

        expert.setUser(user);
        expert.setSpecialization(data.getSpecialization());
        expert.setExperience(data.getExperience());
        expert.setBio(data.getBio());

        return expertRepo.save(expert);
    }

    public Expert getProfile(String email) {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return expertRepo.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Expert profile not found"));
    }
}