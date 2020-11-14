package com.hatsnake.capstone.controller;

import com.hatsnake.capstone.dto.PostsResponseDto;
import com.hatsnake.capstone.dto.PostsSaveRequestDto;
import com.hatsnake.capstone.dto.PostsUpdateRequestDto;
import com.hatsnake.capstone.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// 이 어노테이션은 초기화 되지않은 final 필드나,
// @NonNull 이 붙은 필드에 대해 생성자를 생성
//@RequiredArgsConstructor
@RestController
public class PostsApiController {

    @Autowired
    private PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

}
