package com.hatsnake.capstone.service.comment;

import com.hatsnake.capstone.domain.comment.CommentRepository;
import com.hatsnake.capstone.dto.CommentListResponseDto;
import com.hatsnake.capstone.dto.CommentSaveRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Transactional
    public Long save(CommentSaveRequestDto requestDto) {
        return commentRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public List<CommentListResponseDto> findAll(Long id) {
        return commentRepository.findAll(id).stream()
                .map(CommentListResponseDto::new)
                .collect(Collectors.toList());
    }

}
