package com.zjy.activemq.controller;

import com.zjy.activemq.response.ResultResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhangjiuyang
 * @create 2018/7/24
 * @since 1.0.0
 */
@Controller
@RequestMapping("/index")
public class IndexController {
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ResultResponse index() {
        ResultResponse response = new ResultResponse();
        response.setData("欢迎来到我的界面");
        return response;
    }
}
