package com.klef.fsad.exam.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klef.fsad.exam.model.Expert;
import com.klef.fsad.exam.model.User;

public interface ExpertRepository extends JpaRepository<Expert, Long> {
    Optional<Expert> findByUser(User user);
}