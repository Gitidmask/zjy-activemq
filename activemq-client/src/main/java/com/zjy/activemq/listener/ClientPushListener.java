package com.zjy.activemq.listener;

import com.alibaba.fastjson.JSON;
import com.zjy.activemq.controller.websocket.WebsocketController;
import com.zjy.activemq.domain.Client;
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
@Component("clientPushListener")
public class ClientPushListener implements MessageListener {
    protected static final Logger logger = LoggerFactory.getLogger(ClientPushListener.class);

    @Override
    public void onMessage(Message message) {
        logger.info("[ClientPushListener.onMessage]:begin onMessage.");
        TextMessage textMessage = (TextMessage) message;
        try {
            String jsonStr = textMessage.getText();
            logger.info("[ClientPushListener.onMessage]:receive message is,"+ jsonStr);
            if (jsonStr != null) {
                Client client = JSON.parseObject(jsonStr, Client.class);
                System.out.println("==============================接受到的客户信息 开始====================================");
                System.out.println(client.toString());
                System.out.println("==============================接受到的客户信息 结束====================================");
                WebsocketController.broadcast("client", jsonStr);
            }
        } catch (JMSException e) {
            logger.error("[ClientPushListener.onMessage]:receive message occured an exception",e);
        }
        logger.info("[ClientPushListener.onMessage]:end onMessage.");
    }
}
