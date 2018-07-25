package com.zjy.activemq.controller;

import com.zjy.activemq.domain.Client;
import com.zjy.activemq.domain.News;
import com.zjy.activemq.domain.User;
import com.zjy.activemq.result.ResultResponse;
import com.zjy.activemq.service.PushService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author zhangjiuyang
 * @create 2018/7/24
 * @since 1.0.0
 */
@Controller
@RequestMapping("/push")
public class PushController {
    @Resource(name = "userPushService")
    private PushService userPushService;

    @Resource(name = "newsPushService")
    private PushService newsPushService;

    @Resource(name = "clientPushService")
    private PushService clientPushService;

    /*
     * 用户推送
     * @author zhangjiuyang
     * @date 2018/7/24 18:28
     * @param [user]
     * @return com.zjy.activemq.result.ResultResponse
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseBody
    public ResultResponse userPush(User user) {
        ResultResponse resultResponse = new ResultResponse();
        try {
            userPushService.push(user);
            resultResponse.setData(user);
        } catch (Exception e) {
            e.printStackTrace();
            resultResponse = new ResultResponse(false, e.getMessage());
        }
        return resultResponse;
    }

    /*
     * 新闻推送
     * @author zhangjiuyang
     * @date 2018/7/24 18:28
     * @param [news]
     * @return com.zjy.activemq.result.ResultResponse
     */
    @RequestMapping(value = "/news", method = RequestMethod.POST)
    @ResponseBody
    public ResultResponse newsPush(News news) {
        ResultResponse resultResponse = new ResultResponse();
        try {
            newsPushService.push(news);
            resultResponse.setData(news);
        } catch (Exception e) {
            e.printStackTrace();
            resultResponse = new ResultResponse(false, e.getMessage());
        }
        return resultResponse;
    }

    /*
     * 客户推送
     * @author zhangjiuyang
     * @date 2018/7/24 18:29
     * @param [client]
     * @return com.zjy.activemq.result.ResultResponse
     */
    @RequestMapping(value = "/client", method = RequestMethod.POST)
    @ResponseBody
    public ResultResponse clientPush(Client client) {
        ResultResponse resultResponse = new ResultResponse();
        try {
            clientPushService.push(client);
            resultResponse.setData(client);
        } catch (Exception e) {
            e.printStackTrace();
            resultResponse = new ResultResponse(false, e.getMessage());
        }
        return resultResponse;
    }
}
