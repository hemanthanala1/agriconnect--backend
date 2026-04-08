package com.klef.fsad.exam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klef.fsad.exam.model.Advisory;
import com.klef.fsad.exam.service.AdvisoryService;
import com.klef.fsad.exam.util.ApiResponse;

@RestController
@RequestMapping("/api/v1/advice")
public class AdvisoryController {

    @Autowired
    private AdvisoryService service;

    // Farmer asks question
    @PostMapping("/ask")
    public ApiResponse<Advisory> ask(@RequestBody String question, Authentication auth) {
        Advisory adv = service.askQuestion(auth.getName(), question);
        return new ApiResponse<>("success", "Question asked", adv);
    }

    // Expert answers
    @PostMapping("/answer/{id}")
    public ApiResponse<Advisory> answer(@PathVariable Long id,
                                       @RequestBody String answer,
                                       Authentication auth) {
        Advisory adv = service.answerQuestion(id, auth.getName(), answer);
        return new ApiResponse<>("success", "Answered successfully", adv);
    }

    // Get all advice
    @GetMapping
    public ApiResponse<List<Advisory>> getAll() {
        return new ApiResponse<>("success", "All advice", service.getAll());
    }
}