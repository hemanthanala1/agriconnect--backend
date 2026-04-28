package com.klef.fsad.exam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.klef.fsad.exam.model.User;
import com.klef.fsad.exam.repository.UserRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepo;

    /**
     * Get all users
     */
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    /**
     * Get dashboard statistics
     */
    public Map<String, Object> getDashboardStats() {
        Map<String, Object> stats = new HashMap<>();
        
        long totalUsers = userRepo.count();
        stats.put("totalUsers", totalUsers);
        stats.put("activeUsers", totalUsers);
        stats.put("totalCrops", 0);
        stats.put("totalForumPosts", 0);
        
        return stats;
    }

    /**
     * Update user by ID
     */
    public User updateUser(Long userId, User updateData) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        if (updateData.getFirstName() != null) {
            user.setFirstName(updateData.getFirstName());
        }
        if (updateData.getLastName() != null) {
            user.setLastName(updateData.getLastName());
        }
        if (updateData.getEmail() != null) {
            user.setEmail(updateData.getEmail());
        }
        
        return userRepo.save(user);
    }
}