package com.hatsnake.capstone.controller;

import com.hatsnake.capstone.config.auth.LoginUser;
import com.hatsnake.capstone.config.dto.SessionUser;
import com.hatsnake.capstone.dto.TourListResponseDto;
import com.hatsnake.capstone.service.tourList.TourListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private TourListService tourListService;

    @Autowired
    private HttpSession httpSession;

    @GetMapping("")
    public String index(Model model, @RequestParam(value = "page", defaultValue = "1") Integer pageNum, @LoginUser SessionUser user) {

        List<TourListResponseDto> tourListPagination = tourListService.findAllPagination(pageNum);
        Integer[] pageList = tourListService.getPageList(pageNum);
        List<TourListResponseDto> tourListAll = tourListService.findAll();

        if(user != null) {
            model.addAttribute("userPicture", user.getPicture());
            model.addAttribute("userName", user.getName());
        }

        model.addAttribute("tourListPagination", tourListPagination);
        model.addAttribute("pageList", pageList);
        model.addAttribute("currentPageNum", pageNum);
        model.addAttribute("tourListAll", tourListAll);

        return "index";
    }

}
