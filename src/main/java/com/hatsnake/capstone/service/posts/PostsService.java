package com.hatsnake.capstone.service.posts;

import com.hatsnake.capstone.domain.posts.Posts;
import com.hatsnake.capstone.domain.posts.PostsRepository;
import com.hatsnake.capstone.dto.PostsResponseDto;
import com.hatsnake.capstone.dto.PostsSaveRequestDto;
import com.hatsnake.capstone.dto.PostsUpdateRequestDto;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//@RequiredArgsConstructor
@Service
public class PostsService {

    @Autowired
    private PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        return new PostsResponseDto(entity);
    }


}
