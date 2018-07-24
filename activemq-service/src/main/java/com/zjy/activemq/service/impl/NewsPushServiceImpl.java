package com.zjy.activemq.service.impl;

import com.alibaba.fastjson.JSON;
import com.zjy.activemq.domain.Client;
import com.zjy.activemq.domain.News;
import com.zjy.activemq.service.PushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * @author zhangjiuyang
 * @create 2018/7/24
 * @since 1.0.0
 */
@Service("newsPushService")
public class NewsPushServiceImpl implements PushService {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    @Qualifier("newsServiceQueue")
    private Destination destination;

    @Override
    public void push(final Object info) {
        pushService.execute(new Runnable() {
            @Override
            public void run() {
                jmsTemplate.send(destination, new MessageCreator() {
                    @Override
                    public Message createMessage(Session session) throws JMSException {
                        News p = (News) info;
                        return session.createTextMessage(JSON.toJSONString(p));
                    }
                });
            }
        });
    }
}
