package com.hatsnake.capstone.dto;

import com.hatsnake.capstone.domain.comment.Comment;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
public class CommentResponseDto {

    private Long id;
    private String name;
    private String email;
    private String picture;
    private String content;
    private Integer rating;
    private Long tourListId;

    public CommentResponseDto(Comment entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.picture = entity.getPicture();
        this.content = entity.getContent();
        this.rating = entity.getRating();
        this.tourListId = entity.getTourListId();
    }

}
