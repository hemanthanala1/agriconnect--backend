package com.klef.fsad.exam.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klef.fsad.exam.model.Farmer;
import com.klef.fsad.exam.model.User;

public interface FarmerRepository extends JpaRepository<Farmer, Long> {

    Optional<Farmer> findByUser(User user);
}