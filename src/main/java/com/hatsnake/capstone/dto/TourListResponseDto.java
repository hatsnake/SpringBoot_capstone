package com.hatsnake.capstone.dto;

import com.hatsnake.capstone.domain.tourList.TourList;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Getter
public class TourListResponseDto {

    private Long id;
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
    private String picture;

    public TourListResponseDto(TourList entity) {
        this.id = entity.getId();
        this.cotArtId = entity.getCotArtId();
        this.cotMapPointX = entity.getCotMapPointX();
        this.cotMapPointY = entity.getCotMapPointY();
        this.title = entity.getTitle();
        this.cotAddrNew = entity.getCotAddrNew();
        this.cotTel = entity.getCotTel();
        this.avgRating = entity.getAvgRating();
        this.picture = entity.getPicture();

        // http나 https가 없는 주소에 http를 붙혀줌
        if(entity.getCotHomepage() == null) {
            this.cotHomepage = entity.getCotHomepage();
        } else if((entity.getCotHomepage()).contains("http://") || (entity.getCotHomepage()).contains("https://")) {
            this.cotHomepage = entity.getCotHomepage();
        } else {
            this.cotHomepage = "http://"+entity.getCotHomepage();
        }

        this.cot24hService = entity.getCot24hService();
        this.wifiUse = entity.getWifiUse();
        this.cotCloseDay = entity.getCotCloseDay();
        this.cotUseTimeDesc = entity.getCotUseTimeDesc();
        this.cotTroublemanConvenfac = entity.getCotTroublemanConvenfac();
        this.avgRating = entity.getAvgRating();
        this.picture = entity.getPicture();
    }

    @Builder
    public TourListResponseDto(Long id, String cotArtId, String cotMapPointX,
                    String cotMapPointY, String title,
                    String cotAddrNew, String cotTel,
                    String cotHomepage, String cot24hService,
                    String wifiUse, String cotCloseDay,
                    String cotUseTimeDesc, String cotTroublemanConvenfac, float avgRating,
                    String picture) {
        this.id = id;
        this.cotArtId = cotArtId;
        this.cotMapPointX = cotMapPointX;
        this.cotMapPointY = cotMapPointY;
        this.title = title;
        this.cotAddrNew = cotAddrNew;
        this.cotTel = cotTel;

        // http나 https가 없는 주소에 http를 붙혀줌
        if(cotHomepage == null) {
            this.cotHomepage = cotHomepage;
        } else if((cotHomepage).contains("http://") || (cotHomepage).contains("https://")) {
            this.cotHomepage = cotHomepage;
        } else {
            this.cotHomepage = "http://"+cotHomepage;
        }

        this.cot24hService = cot24hService;
        this.wifiUse = wifiUse;
        this.cotCloseDay = cotCloseDay;
        this.cotUseTimeDesc = cotUseTimeDesc;
        this.cotTroublemanConvenfac = cotTroublemanConvenfac;
        this.avgRating = avgRating;
        this.picture = picture;
    }

}
