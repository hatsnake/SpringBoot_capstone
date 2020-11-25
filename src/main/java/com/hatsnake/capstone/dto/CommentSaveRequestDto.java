package com.hatsnake.capstone.dto;

import com.hatsnake.capstone.domain.comment.Comment;
import com.hatsnake.capstone.domain.tourList.TourList;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
public class CommentSaveRequestDto {


    private String name;
    private String email;
    private String picture;
    private String content;
    private Integer rating;
    private Long tourListId;

    @Builder
    public CommentSaveRequestDto(String name, String email, String picture,
                                 String content, Integer rating, Long tourListId) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.content = content;
        this.rating = rating;
        this.tourListId = tourListId;
    }

    public Comment toEntity() {
        return Comment.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .content(content)
                .rating(rating)
                .tourListId(tourListId)
                .build();
    }

}
