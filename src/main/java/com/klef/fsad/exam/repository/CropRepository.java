package com.klef.fsad.exam.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.klef.fsad.exam.model.Crop;

public interface CropRepository extends JpaRepository<Crop, Long> {
}