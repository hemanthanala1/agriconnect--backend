package com.klef.fsad.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.klef.fsad.exam.model.ForumReply;

public interface ForumReplyRepository extends JpaRepository<ForumReply, Long> {
}