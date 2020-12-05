package com.hatsnake.capstone.controller;

import com.hatsnake.capstone.config.auth.LoginUser;
import com.hatsnake.capstone.config.dto.SessionUser;
import com.hatsnake.capstone.domain.comment.Comment;
import com.hatsnake.capstone.dto.CommentListResponseDto;
import com.hatsnake.capstone.dto.CommentResponseDto;
import com.hatsnake.capstone.dto.CommentSaveRequestDto;
import com.hatsnake.capstone.dto.TourListResponseDto;
import com.hatsnake.capstone.service.comment.CommentService;
import com.hatsnake.capstone.service.tourList.TourListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private TourListService tourListService;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private CommentService commentService;

    @GetMapping("")
    public String index(Model model, @RequestParam(value = "page", defaultValue = "1") Integer pageNum,
                        @LoginUser SessionUser user, @RequestParam(value = "keyword", defaultValue = "") String keyword,
                        @RequestParam(value = "condition", defaultValue = "all") String condition) {
        List<TourListResponseDto> tourListPagination = tourListService.findAllPagination(pageNum, keyword, condition);
        Integer[] pageList = tourListService.getPageList(pageNum, keyword, condition);
        List<TourListResponseDto> tourListAll = tourListService.findAll();

        Integer lastPageNum = (int)(Math.ceil((double)pageList.length/10));

        if(user != null) {
            model.addAttribute("userPicture", user.getPicture());
            model.addAttribute("userName", user.getName());
            model.addAttribute("userEmail", user.getEmail());
        }

        model.addAttribute("tourListPagination", tourListPagination);
        model.addAttribute("pageList", pageList);
        model.addAttribute("currentPageNum", pageNum);
        model.addAttribute("lastPageNum", lastPageNum);
        model.addAttribute("tourListAll", tourListAll);
        model.addAttribute("keyword", keyword);
        model.addAttribute("condition", condition);

        return "index";
    }

    @GetMapping("/getTourList")
    @ResponseBody
    public Object getTourList(@RequestParam("id") Long id) {
        TourListResponseDto tourList = tourListService.getTourList(id);

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("id", tourList.getId());
        if(tourList.getTitle() != null) {
            map.put("title", " <i class=\"map marker alternate icon\"></i> " + tourList.getTitle());
        }
        if(tourList.getCotAddrNew() != null) {
            map.put("cotAddrNew", " <i class=\"map icon\"></i> " + tourList.getCotAddrNew());
        }
        if(tourList.getWifiUse().equals("Y")) {
            map.put("wifiUse", " <i class=\"wifi icon\"></i> 와이파이");
        } else {
            map.put("wifiUse", " <i class=\"ban icon\"></i> 와이파이");
        }
        if(tourList.getCotTel() != null) {
            map.put("cotTel", " <i class=\"phone icon\"></i> " + tourList.getCotTel());
        }
        //홈페이지 (http://)추가
        if(tourList.getCotHomepage() != null) {
            map.put("cotHomepage", " <i class=\"laptop house icon\"></i> <a href=\"" + tourList.getCotHomepage() + "\"  target=\"_blank\">링크</a>");
        }
        if(tourList.getCotCloseDay() != null) {
            map.put("cotCloseDay", " <i class=\"door closed icon\"></i> " + tourList.getCotCloseDay());
        }
        if(tourList.getCotUseTimeDesc() != null) {
            map.put("cotUseTimeDesc", " <i class=\"door open icon\"></i><br/> " + tourList.getCotUseTimeDesc());
        }
        if(tourList.getCotTroublemanConvenfac() != null) {
            map.put("cotTroublemanConvenfac", " <i class=\"wheelchair icon\"></i><br/> " + tourList.getCotTroublemanConvenfac());
        }
        if(tourList.getPicture() != null) {
            map.put("picture", tourList.getPicture());
        } else {
            map.put("picture", "/images/noImage.jpg");
        }

        return map;
    }

    @PostMapping("/saveComment")
    @ResponseBody
    public Long saveComment(@RequestBody CommentSaveRequestDto requestDto) {
        return commentService.save(requestDto);
    }

    @GetMapping("/getCommentList")
    @ResponseBody
    public List<CommentListResponseDto> getCommentList(@RequestParam("id") Long id,
                                                       @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<CommentListResponseDto> commentList = commentService.findAll(id, pageNum);

        return commentList;
    }

    @GetMapping("/deleteComment")
    @ResponseBody
    public void deleteComment(@RequestParam("deleteId") Long deleteId) {
        commentService.delete(deleteId);
    }
}
