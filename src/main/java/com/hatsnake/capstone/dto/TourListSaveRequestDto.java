package com.hatsnake.capstone.dto;

import com.hatsnake.capstone.domain.tourList.TourList;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Getter
@NoArgsConstructor
public class TourListSaveRequestDto {

    private String cotArtId;
    private String cotMapPointX;
    private String cotMapPointY;
    private String title;
    private String cotAddrNew;
    private String cotTel;
    private String cotHomepage;
    private String cot24hService;
    private String wifiUse;
    private String cotCloseDay;
    private String cotUseTimeDesc;
    private String cotTroublemanConvenfac;
    private float avgRating;

    @Builder
    public TourListSaveRequestDto(String cotArtId, String cotMapPointX,
                                  String cotMapPointY, String title,
                                  String cotAddrNew, String cotTel,
                                  String cotHomepage, String cot24hService,
                                  String wifiUse, String cotCloseDay,
                                  String cotUseTimeDesc, String cotTroublemanConvenfac, float avgRating){
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

    public TourList toEntity() {
        return TourList.builder()
                .cotArtId(cotArtId)
                .cotMapPointX(cotMapPointX)
                .cotMapPointY(cotMapPointY)
                .title(title)
                .cotAddrNew(cotAddrNew)
                .cotTel(cotTel)
                .cotHomepage(cotHomepage)
                .cot24hService(cot24hService)
                .wifiUse(wifiUse)
                .cotCloseDay(cotCloseDay)
                .cotUseTimeDesc(cotUseTimeDesc)
                .cotTroublemanConvenfac(cotTroublemanConvenfac)
                .avgRating(avgRating)
                .build();
    }

}
