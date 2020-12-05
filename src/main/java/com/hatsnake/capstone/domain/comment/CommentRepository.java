package com.hatsnake.capstone.domain.comment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Page<Comment> findAllByTourListIdOrderByIdDesc(Pageable pageable, Long id);

    @Query("SELECT c FROM Comment c WHERE c.tourListId = ?1")
    List<Comment> findAllById(Long id);
}
