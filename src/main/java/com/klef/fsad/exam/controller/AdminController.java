package com.klef.fsad.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import com.klef.fsad.exam.model.User;
import com.klef.fsad.exam.service.AdminService;
import com.klef.fsad.exam.util.ApiResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/admin")
@CrossOrigin(
    origins = {"http://localhost:3000", "http://localhost:5173", "http://127.0.0.1:5173"},
    methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS},
    allowCredentials = "true",
    maxAge = 3600
)
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * Get dashboard statistics
     * GET /api/v1/admin/dashboard
     */
    @GetMapping("/dashboard")
    public ApiResponse<Map<String, Object>> getDashboard() {
        try {
            Map<String, Object> stats = adminService.getDashboardStats();
            return new ApiResponse<>("success", "Dashboard stats retrieved", stats);
        } catch (Exception e) {
            return new ApiResponse<>("error", e.getMessage(), null);
        }
    }

    /**
     * Get all users
     * GET /api/v1/admin/users
     */
    @GetMapping("/users")
    public ApiResponse<List<User>> getUsers() {
        try {
            List<User> users = adminService.getAllUsers();
            return new ApiResponse<>("success", "All users retrieved", users);
        } catch (Exception e) {
            return new ApiResponse<>("error", e.getMessage(), null);
        }
    }

    /**
     * Update user
     * PUT /api/v1/admin/users/{id}
     */
    @PutMapping("/users/{id}")
    public ApiResponse<User> updateUser(@PathVariable Long id, @RequestBody User updateData) {
        try {
            User user = adminService.updateUser(id, updateData);
            return new ApiResponse<>("success", "User updated", user);
        } catch (Exception e) {
            return new ApiResponse<>("error", e.getMessage(), null);
        }
    }

    /**
     * Get moderation data
     * GET /api/v1/admin/moderation
     */
    @GetMapping("/moderation")
    public ApiResponse<Map<String, Object>> getModeration() {
        try {
            Map<String, Object> moderation = new HashMap<>();
            moderation.put("content", "Moderation data placeholder");
            return new ApiResponse<>("success", "Moderation data retrieved", moderation);
        } catch (Exception e) {
            return new ApiResponse<>("error", e.getMessage(), null);
        }
    }

    /**
     * Moderate content
     * PUT /api/v1/admin/moderation/{id}
     */
    @PutMapping("/moderation/{id}")
    public ApiResponse<String> moderateContent(@PathVariable Long id, @RequestBody Map<String, String> moderationData) {
        try {
            String action = moderationData.get("action");
            String reason = moderationData.get("reason");
            
            return new ApiResponse<>("success", "Content moderated - " + action, "Success");
        } catch (Exception e) {
            return new ApiResponse<>("error", e.getMessage(), null);
        }
    }
}