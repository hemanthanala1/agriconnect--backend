package com.klef.fsad.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.klef.fsad.exam.model.ForumThread;

public interface ForumThreadRepository extends JpaRepository<ForumThread, Long> {
}