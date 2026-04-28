package com.klef.fsad.exam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.klef.fsad.exam.model.FarmOperation;
import com.klef.fsad.exam.repository.FarmOperationRepository;
import java.util.List;

@Service
public class FarmOperationService {

    @Autowired
    private FarmOperationRepository repo;

    public List<FarmOperation> getUserTasks(Long userId) {
        return repo.findByUserId(userId);
    }

    public List<FarmOperation> getAllTasks() {
        return repo.findAll();
    }

    public FarmOperation createTask(FarmOperation task) {
        return repo.save(task);
    }

    public FarmOperation updateTask(Long id, FarmOperation updateData) {
        FarmOperation task = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (updateData.getTitle() != null) {
            task.setTitle(updateData.getTitle());
        }
        if (updateData.getDescription() != null) {
            task.setDescription(updateData.getDescription());
        }
        if (updateData.getDueDate() != null) {
            task.setDueDate(updateData.getDueDate());
        }
        if (updateData.getPriority() != null) {
            task.setPriority(updateData.getPriority());
        }
        if (updateData.getCompleted() != null) {
            task.setCompleted(updateData.getCompleted());
        }

        return repo.save(task);
    }

    public void deleteTask(Long id) {
        repo.deleteById(id);
    }
}