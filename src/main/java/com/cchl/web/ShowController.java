package com.cchl.web;

import com.cchl.dto.DataWithPage;
import com.cchl.eumn.Dictionary;
import com.cchl.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

/**
 * 公共信息展示
 */
@Controller
@RequestMapping(value = "/show")
public class ShowController {

    @Autowired
    private InfoService infoService;

    @RequestMapping(value = "/title")
    public String title() {
        return "title";
    }

    @RequestMapping(value = "/getTitle")
    @ResponseBody
    public DataWithPage getTitle(@RequestParam(value = "page")Integer page,
                              @RequestParam(value = "limit")Integer limit,
                              @SessionAttribute(value = "user_id", required = false)Integer userId) {
        try {
            //TODO 测试用的userId
            userId = 1007;
            if (userId == null) {
                return new DataWithPage(Dictionary.ILLEGAL_VISIT);
            }
            return new DataWithPage<>(0, infoService.totalNumber(0, userId), infoService.titles(page, limit, userId));
        } catch (Exception e) {
            return new DataWithPage(Dictionary.SYSTEM_ERROR);
        }
    }
}