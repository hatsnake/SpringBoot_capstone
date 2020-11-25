package com.hatsnake.capstone.dto;

import com.hatsnake.capstone.domain.comment.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentListResponseDto {
    private Long id;
    private String name;
    private String email;
    private String picture;
    private String content;
    private Integer rating;
    private Long tourListId;
    private LocalDateTime modifiedDate;

    public CommentListResponseDto(Comment entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.picture = entity.getPicture();
        this.content = entity.getContent();
        this.rating = entity.getRating();
        this.tourListId = entity.getTourListId();
        this.modifiedDate = entity.getModifiedDate();
    }

}
