package com.zjy.activemq.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//推送的接口
public interface PushService {
    public final ExecutorService pushService = Executors.newFixedThreadPool(10);
    public void push(Object info);
}
