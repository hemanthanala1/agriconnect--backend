package com.klef.fsad.exam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.fsad.exam.model.Advisory;
import com.klef.fsad.exam.model.Expert;
import com.klef.fsad.exam.model.Farmer;
import com.klef.fsad.exam.model.User;
import com.klef.fsad.exam.repository.AdvisoryRepository;
import com.klef.fsad.exam.repository.ExpertRepository;
import com.klef.fsad.exam.repository.FarmerRepository;
import com.klef.fsad.exam.repository.UserRepository;

@Service
public class AdvisoryService {

    @Autowired
    private AdvisoryRepository advisoryRepo;

    @Autowired
    private FarmerRepository farmerRepo;

    @Autowired
    private ExpertRepository expertRepo;

    @Autowired
    private UserRepository userRepo;

    // 🔥 Farmer asks question
    public Advisory askQuestion(String email, String question) {

        User user = userRepo.findByEmail(email).orElseThrow();
        Farmer farmer = farmerRepo.findByUser(user).orElseThrow();

        Advisory advisory = new Advisory();
        advisory.setQuestion(question);
        advisory.setFarmer(farmer);

        return advisoryRepo.save(advisory);
    }

    // 🔥 Expert answers
    public Advisory answerQuestion(Long id, String email, String answer) {

        Advisory advisory = advisoryRepo.findById(id).orElseThrow();

        User user = userRepo.findByEmail(email).orElseThrow();
        Expert expert = expertRepo.findByUser(user).orElseThrow();

        advisory.setAnswer(answer);
        advisory.setExpert(expert);

        return advisoryRepo.save(advisory);
    }

    public List<Advisory> getAll() {
        return advisoryRepo.findAll();
    }
}