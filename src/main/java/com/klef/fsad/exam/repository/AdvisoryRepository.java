package com.klef.fsad.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klef.fsad.exam.model.Advisory;

public interface AdvisoryRepository extends JpaRepository<Advisory, Long> {
}