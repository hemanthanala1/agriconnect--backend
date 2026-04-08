package com.klef.fsad.exam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.fsad.exam.model.ForumReply;
import com.klef.fsad.exam.model.ForumThread;
import com.klef.fsad.exam.model.User;
import com.klef.fsad.exam.repository.ForumReplyRepository;
import com.klef.fsad.exam.repository.ForumThreadRepository;
import com.klef.fsad.exam.repository.UserRepository;

@Service
public class ForumService {

    @Autowired
    private ForumThreadRepository threadRepo;

    @Autowired
    private ForumReplyRepository replyRepo;

    @Autowired
    private UserRepository userRepo;

    // CREATE THREAD
    public ForumThread createThread(String email, ForumThread thread) {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        thread.setUser(user);

        return threadRepo.save(thread);
    }

    // GET ALL THREADS
    public List<ForumThread> getAllThreads() {
        return threadRepo.findAll();
    }

    // ADD REPLY
    public ForumReply addReply(String email, Long threadId, String content) {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ForumThread thread = threadRepo.findById(threadId)
                .orElseThrow(() -> new RuntimeException("Thread not found"));

        ForumReply reply = new ForumReply();
        reply.setContent(content);
        reply.setUser(user);
        reply.setThread(thread);

        return replyRepo.save(reply);
    }
}