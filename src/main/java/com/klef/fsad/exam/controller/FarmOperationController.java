package com.klef.fsad.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.klef.fsad.exam.model.FarmOperation;
import com.klef.fsad.exam.service.FarmOperationService;
import com.klef.fsad.exam.util.ApiResponse;
import java.util.List;

@RestController
@RequestMapping("/api/v1/farm-data")
@CrossOrigin(
    origins = {"http://localhost:3000", "http://localhost:5173", "http://127.0.0.1:5173"},
    methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS},
    allowCredentials = "true",
    maxAge = 3600
)
public class FarmOperationController {

    @Autowired
    private FarmOperationService service;

    /**
     * Get user tasks
     * GET /api/v1/farm-data/user/{userId}/tasks
     */
    @GetMapping("/user/{userId}/tasks")
    public ApiResponse<List<FarmOperation>> getUserTasks(@PathVariable Long userId) {
        try {
            List<FarmOperation> tasks = service.getUserTasks(userId);
            return new ApiResponse<>("success", "User tasks retrieved", tasks);
        } catch (Exception e) {
            return new ApiResponse<>("error", e.getMessage(), null);
        }
    }

    /**
     * Get all tasks
     * GET /api/v1/farm-data/tasks
     */
    @GetMapping("/tasks")
    public ApiResponse<List<FarmOperation>> getAllTasks() {
        try {
            List<FarmOperation> tasks = service.getAllTasks();
            return new ApiResponse<>("success", "All tasks retrieved", tasks);
        } catch (Exception e) {
            return new ApiResponse<>("error", e.getMessage(), null);
        }
    }

    /**
     * Create task
     * POST /api/v1/farm-data/tasks
     */
    @PostMapping("/tasks")
    public ApiResponse<FarmOperation> createTask(@RequestBody FarmOperation task) {
        try {
            FarmOperation createdTask = service.createTask(task);
            return new ApiResponse<>("success", "Task created", createdTask);
        } catch (Exception e) {
            return new ApiResponse<>("error", e.getMessage(), null);
        }
    }

    /**
     * Update task
     * PUT /api/v1/farm-data/tasks/{id}
     */
    @PutMapping("/tasks/{id}")
    public ApiResponse<FarmOperation> updateTask(@PathVariable Long id, @RequestBody FarmOperation updateData) {
        try {
            FarmOperation updatedTask = service.updateTask(id, updateData);
            return new ApiResponse<>("success", "Task updated", updatedTask);
        } catch (Exception e) {
            return new ApiResponse<>("error", e.getMessage(), null);
        }
    }

    /**
     * Delete task
     * DELETE /api/v1/farm-data/tasks/{id}
     */
    @DeleteMapping("/tasks/{id}")
    public ApiResponse<String> deleteTask(@PathVariable Long id) {
        try {
            service.deleteTask(id);
            return new ApiResponse<>("success", "Task deleted", "Success");
        } catch (Exception e) {
            return new ApiResponse<>("error", e.getMessage(), null);
        }
    }
}