package com.klef.fsad.exam.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klef.fsad.exam.util.ApiResponse;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {

    @GetMapping("/hello")
    public ApiResponse<String> hello() {
        return new ApiResponse<>("success", "Test API working", "JWT Working!");
    }
}