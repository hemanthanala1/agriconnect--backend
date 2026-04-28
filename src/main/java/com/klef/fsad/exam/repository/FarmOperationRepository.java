package com.klef.fsad.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.klef.fsad.exam.model.FarmOperation;
import java.util.List;

public interface FarmOperationRepository extends JpaRepository<FarmOperation, Long> {
    List<FarmOperation> findByUserId(Long userId);
}