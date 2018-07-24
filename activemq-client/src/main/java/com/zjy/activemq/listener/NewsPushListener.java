package com.zjy.activemq.listener;

import com.alibaba.fastjson.JSON;
import com.zjy.activemq.controller.websocket.WebsocketController;
import com.zjy.activemq.domain.News;
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
@Component("newsPushListener")
public class NewsPushListener implements MessageListener {
    protected static final Logger logger = LoggerFactory.getLogger(NewsPushListener.class);

    @Override
    public void onMessage(Message message) {
        logger.info("[NewsPushListener.onMessage]:begin onMessage.");
        TextMessage textMessage = (TextMessage) message;
        try {
            String jsonStr = textMessage.getText();
            logger.info("[NewsPushListener.onMessage]:receive message is,"+ jsonStr);
            if (jsonStr != null) {
                News news = JSON.parseObject(jsonStr, News.class);
                System.out.println("==============================接受到的新闻信息 开始====================================");
                System.out.println(news.toString());
                System.out.println("==============================接受到的新闻信息 结束====================================");
                WebsocketController.broadcast("news", jsonStr);
            }
        } catch (JMSException e) {
            logger.error("[NewsPushListener.onMessage]:receive message occured an exception",e);
        }
        logger.info("[NewsPushListener.onMessage]:end onMessage.");
    }
}
