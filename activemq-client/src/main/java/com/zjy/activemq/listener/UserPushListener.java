package com.zjy.activemq.listener;

import com.alibaba.fastjson.JSON;
import com.zjy.activemq.controller.websocket.WebsocketController;
import com.zjy.activemq.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @author zhangjiuyang
 * @create 2018/7/24
 * @since 1.0.0
 */
@Component("userPushListener")
public class UserPushListener implements MessageListener {
    protected static final Logger logger = LoggerFactory.getLogger(UserPushListener.class);

    @Override
    public void onMessage(Message message) {
        logger.info("[UserPushListener.onMessage]:begin onMessage.");
        TextMessage textMessage = (TextMessage) message;
        try {
            String jsonStr = textMessage.getText();
            logger.info("[UserPushListener.onMessage]:receive message is,"+ jsonStr);
            if (jsonStr != null) {
                User user = JSON.parseObject(jsonStr, User.class);
                System.out.println("==============================接受到的用户信息 开始====================================");
                System.out.println(user.toString());
                System.out.println("==============================接受到的用户信息 结束====================================");
                WebsocketController.broadcast("user", jsonStr);
            }
        } catch (JMSException e) {
            logger.error("[UserPushListener.onMessage]:receive message occured an exception",e);
        }
        logger.info("[UserPushListener.onMessage]:end onMessage.");
    }
}
