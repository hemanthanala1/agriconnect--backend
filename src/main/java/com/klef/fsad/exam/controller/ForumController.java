package com.klef.fsad.exam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.klef.fsad.exam.model.*;
import com.klef.fsad.exam.service.ForumService;

@RestController
@RequestMapping("/api/v1/forum")
public class ForumController {

    @Autowired
    private ForumService service;

    // CREATE THREAD
    @PostMapping("/thread")
    public ForumThread create(@RequestBody ForumThread thread, Authentication auth) {
        return service.createThread(auth.getName(), thread);
    }

    // GET ALL THREADS
    @GetMapping("/thread")
    public List<ForumThread> getAll() {
        return service.getAllThreads();
    }

    // ADD REPLY
    @PostMapping("/reply/{threadId}")
    public ForumReply reply(@PathVariable Long threadId,
                           @RequestBody String content,
                           Authentication auth) {
        return service.addReply(auth.getName(), threadId, content);
    }
}