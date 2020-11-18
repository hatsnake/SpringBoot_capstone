package com.hatsnake.capstone.controller;

import com.hatsnake.capstone.config.auth.LoginUser;
import com.hatsnake.capstone.config.dto.SessionUser;
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

    @GetMapping("/getTourList")
    @ResponseBody
    public Object getTourList(@RequestParam("id") Long id) {
        TourListResponseDto tourList = tourListService.getTourList(id);

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("id", tourList.getId());
        map.put("title","<i class=\"map marker alternate icon\"></i> " +tourList.getTitle());
        map.put("cotAddrNew", "<i class=\"map icon\"></i> " +tourList.getCotAddrNew());
        if(tourList.getWifiUse().equals("Y")) {
            map.put("wifiUse", "<i class=\"wifi icon\"></i> 와이파이");
        } else {
            map.put("wifiUse", "<i class=\"ban icon\"></i> 와이파이");
        }
        if(tourList.getCotTel() != null) {
            map.put("cotTel", "<i class=\"phone icon\"></i> " + tourList.getCotTel());
        }
        //홈페이지 (http://)추가
        if(tourList.getCotHomepage() != null) {
            map.put("cotHomepage", "<i class=\"laptop house icon\"></i> <a href=\"" + tourList.getCotHomepage() + "\">링크</a>");
        }
        if(tourList.getCotCloseDay() != null) {
            map.put("cotCloseDay", "<i class=\"door closed icon\"></i> " + tourList.getCotCloseDay());
        }
        if(tourList.getCotUseTimeDesc() != null) {
            map.put("cotUseTimeDesc", "<i class=\"door open icon\"></i><br/> " + tourList.getCotUseTimeDesc());
        }
        if(tourList.getCotTroublemanConvenfac() != null) {
            map.put("cotTroublemanConvenfac", "<i class=\"wheelchair icon\"></i><br/> " + tourList.getCotTroublemanConvenfac());
        }
        return map;
    }

}
