package com.hatsnake.capstone.controller;

import com.hatsnake.capstone.dto.TourListResponseDto;
import com.hatsnake.capstone.service.tourList.TourListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private TourListService tourListService;

    @Autowired
    private HttpSession httpSession;

    @GetMapping("")
    public String index(Model model, @RequestParam(value = "page", defaultValue = "1") Integer pageNum) {

        List<TourListResponseDto> tourListPagination = tourListService.findAllPagination(pageNum);
        Integer[] pageList = tourListService.getPageList(pageNum);
        List<TourListResponseDto> tourListAll = tourListService.findAll();

        model.addAttribute("tourListPagination", tourListPagination);
        model.addAttribute("pageList", pageList);
        model.addAttribute("currentPageNum", pageNum);
        model.addAttribute("tourListAll", tourListAll);

        return "index";
    }

    @GetMapping("/getTourList")
    @ResponseBody
    public Object getTourList(@RequestParam("id") Long id) {
        TourListResponseDto tourList = tourListService.getTourList(id);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("id", tourList.getId());
        map.put("title", tourList.getTitle());
        map.put("cotAddrNew", tourList.getCotAddrNew());
        map.put("cotTel", tourList.getCotTel());
        map.put("cotHomepage", tourList.getCotHomepage());
        map.put("wifiUse", tourList.getWifiUse());
        map.put("cotCloseDay", tourList.getCotCloseDay());
        map.put("cotUseTimeDesc", tourList.getCotUseTimeDesc());
        map.put("cotTroublemanConvenfac", tourList.getCotTroublemanConvenfac());
        return map;
    }

}
