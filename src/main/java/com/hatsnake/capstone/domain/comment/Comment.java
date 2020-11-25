package com.hatsnake.capstone.domain.comment;

import com.hatsnake.capstone.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.awt.*;

@Getter
@NoArgsConstructor
@Entity
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(columnDefinition = "TEXT")
    private String picture;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private Integer rating;

    private Long tourListId;

    @Builder
    public Comment(String name, String email, String picture,
                   String content, Integer rating, Long tourListId) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.content = content;
        this.rating = rating;
        this.tourListId = tourListId;
    }
}
