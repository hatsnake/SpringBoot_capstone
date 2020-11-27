package com.hatsnake.capstone.domain.tourList;

import com.hatsnake.capstone.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class TourList extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cotArtId;
    private String cotMapPointX;
    private String cotMapPointY;
    private String title;
    private String cotAddrNew;
    private String cotTel;
    @Column(columnDefinition = "TEXT")
    private String cotHomepage;
    private String cot24hService;
    private String wifiUse;
    @Column(columnDefinition = "TEXT")
    private String cotCloseDay;
    @Column(columnDefinition = "TEXT")
    private String cotUseTimeDesc;
    @Column(columnDefinition = "TEXT")
    private String cotTroublemanConvenfac;

    @Column(columnDefinition = "double default 0")
    private float avgRating;

    @Builder
    public TourList(String cotArtId, String cotMapPointX,
                    String cotMapPointY, String title,
                    String cotAddrNew, String cotTel,
                    String cotHomepage, String cot24hService,
                    String wifiUse, String cotCloseDay,
                    String cotUseTimeDesc, String cotTroublemanConvenfac, float avgRating) {
        this.cotArtId = cotArtId;
        this.cotMapPointX = cotMapPointX;
        this.cotMapPointY = cotMapPointY;
        this.title = title;
        this.cotAddrNew = cotAddrNew;
        this.cotTel = cotTel;
        this.cotHomepage = cotHomepage;
        this.cot24hService = cot24hService;
        this.wifiUse = wifiUse;
        this.cotCloseDay = cotCloseDay;
        this.cotUseTimeDesc = cotUseTimeDesc;
        this.cotTroublemanConvenfac = cotTroublemanConvenfac;
        this.avgRating = avgRating;
    }

}
