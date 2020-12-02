package com.hatsnake.capstone.service.tourList;

import com.hatsnake.capstone.domain.comment.Comment;
import com.hatsnake.capstone.domain.comment.CommentRepository;
import com.hatsnake.capstone.domain.tourList.TourList;
import com.hatsnake.capstone.domain.tourList.TourListRepository;
import com.hatsnake.capstone.dto.CommentListResponseDto;
import com.hatsnake.capstone.dto.TourListResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TourListService {

    @Autowired
    private TourListRepository tourListRepository;

    @Autowired
    private CommentRepository commentRepository;

    //오류 고쳐야함
    private static int BLOCK_PAGE_NUM_COUNT; //블럭에 존재하는 페이지수
    private static final int PAGE_POST_COUNT = 10; //한페이지에 존재하는 게시글수

    // 페이지네이션과 검색기능이 들어간 리스트
    @Transactional(readOnly = true)
    public List<TourListResponseDto> findAllPagination(Integer pageNum, String keyword, String condition) {

        Page<TourList> page = null;

        if(condition.equals("all")) {
            page = tourListRepository.findAllByTitleContaining(PageRequest.of(pageNum - 1, PAGE_POST_COUNT, Sort.by(Sort.Direction.ASC, "id")), keyword);
        } else if(condition.equals("area")) {
            page = tourListRepository.findAllByCotAddrNewContaining(PageRequest.of(pageNum - 1, PAGE_POST_COUNT, Sort.by(Sort.Direction.ASC, "id")), keyword);
        }
        List<TourList> tourLists = page.getContent();
        List<TourListResponseDto> tourListResponseDtoList = new ArrayList<>();

        for(TourList tourList : tourLists) {
            //tourListResponseDtoList.add(this.convertEntityToDto(tourList));
            List<Comment> commentList = commentRepository.findAllById(tourList.getId());

            float rating = 0;
            int i = 0;
            for(Comment comment : commentList) {
                rating = rating + comment.getRating();
                i = i + 1;
            }
            float AvgRating = (float) (Math.round((rating/i)*100)/100.0);
            tourList.setAvgRating(AvgRating);
            tourListResponseDtoList.add(this.convertEntityToDto(tourList));
        }

        return tourListResponseDtoList;
    }

    // 지도에 모든 위치표시
    @Transactional(readOnly = true)
    public List<TourListResponseDto> findAll() {
        return tourListRepository.findAll().stream()
                .map(TourListResponseDto::new)
                .collect(Collectors.toList());
    }

    private TourListResponseDto convertEntityToDto(TourList tourList) {
        return TourListResponseDto.builder()
                .id(tourList.getId())
                .cotArtId(tourList.getCotArtId())
                .cotMapPointX(tourList.getCotMapPointX())
                .cotMapPointY(tourList.getCotMapPointY())
                .title(tourList.getTitle())
                .cotAddrNew(tourList.getCotAddrNew())
                .cotTel(tourList.getCotTel())
                .cotHomepage(tourList.getCotHomepage())
                .cot24hService(tourList.getCot24hService())
                .wifiUse(tourList.getWifiUse())
                .cotCloseDay(tourList.getCotCloseDay())
                .cotUseTimeDesc(tourList.getCotUseTimeDesc())
                .cotTroublemanConvenfac(tourList.getCotTroublemanConvenfac())
                .avgRating(tourList.getAvgRating())
                .picture(tourList.getPicture())
                .build();
    }

    @Transactional
    public Integer[] getPageList(Integer curPageNum, String keyword, String condition) {

        int getBoardCount = 0;

        if(condition.equals("all")) {
            getBoardCount = tourListRepository.findAllByTitleContaining(keyword).size();
        } else if(condition.equals("area")) {
            getBoardCount = tourListRepository.findAllByCotAddrNewContaining(keyword).size();
        }
        //총게시글수
        Double postsTotalCount = Double.valueOf(getBoardCount);
        BLOCK_PAGE_NUM_COUNT = getBoardCount;
        Integer[] pageList = new Integer[BLOCK_PAGE_NUM_COUNT];
        //총게시글수를 기준으로 계산한 마지막 페이지 번호 계산
        Integer totalLastPageNum = (int)(Math.ceil((postsTotalCount/PAGE_POST_COUNT)));
        //현재페이지를 기준으로 블럭의 마지막 페이지번호 계산
        Integer blockLastPageNum = (totalLastPageNum > curPageNum + BLOCK_PAGE_NUM_COUNT)
                ? curPageNum + BLOCK_PAGE_NUM_COUNT : totalLastPageNum;
        //페이지 시작번호 조정
        curPageNum = (curPageNum<=3) ? 1 : curPageNum-2;
        //페이지 번호 할당
        for(int val=curPageNum, i=0; val<=curPageNum+4; val++,i++) {
            if(blockLastPageNum >= val)
                pageList[i] = val;
        }

        return pageList;
    }

    @Transactional
    public TourListResponseDto getTourList(Long id) {
        Optional<TourList> tourListWrapper = tourListRepository.findById(id);
        TourList tourList = tourListWrapper.get();

        TourListResponseDto tourListDto = TourListResponseDto.builder()
            .id(tourList.getId())
            .cotArtId(tourList.getCotArtId())
            .cotMapPointX(tourList.getCotMapPointX())
            .cotMapPointY(tourList.getCotMapPointY())
            .title(tourList.getTitle())
            .cotAddrNew(tourList.getCotAddrNew())
            .cotTel(tourList.getCotTel())
            .cotHomepage(tourList.getCotHomepage())
            .cot24hService(tourList.getCot24hService())
            .wifiUse(tourList.getWifiUse())
            .cotCloseDay(tourList.getCotCloseDay())
            .cotUseTimeDesc(tourList.getCotUseTimeDesc())
            .cotTroublemanConvenfac(tourList.getCotTroublemanConvenfac())
            .avgRating(tourList.getAvgRating())
            .picture(tourList.getPicture())
            .build();

        return tourListDto;
    }

}
