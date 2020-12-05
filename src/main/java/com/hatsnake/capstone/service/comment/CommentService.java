package com.hatsnake.capstone.service.comment;

import com.hatsnake.capstone.domain.comment.Comment;
import com.hatsnake.capstone.domain.comment.CommentRepository;
import com.hatsnake.capstone.dto.CommentListResponseDto;
import com.hatsnake.capstone.dto.CommentSaveRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    public List<CommentListResponseDto> findAll(Long id, Integer pageNum) {
        return commentRepository.findAllByTourListIdOrderByIdDesc(PageRequest.of(pageNum - 1, 10, Sort.by(Sort.Direction.DESC, "id")), id).stream()
                .map(CommentListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long deleteId) {
        Comment comment = commentRepository.findById(deleteId)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 게시글이 없습니다. deleteId="+deleteId));

        commentRepository.delete(comment);
    }

}
