package com.zjy.activemq.controller.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhangjiuyang
 * @create 2018/7/24
 * @since 1.0.0
 */
@ServerEndpoint("/websocket/myWebsocket")
public class WebsocketController {
    private static final Logger logger = LoggerFactory.getLogger(WebsocketController.class);

    public static Map<String, Session> clients = new ConcurrentHashMap<>();
    
    /*  
     * 打开连接时触发
     * @author zhangjiuyang
     * @date 2018/7/24 17:25
     * @param [myWebsocket, session]
     * @return void
     */
    @OnOpen
    public void onOpen(@PathParam("myWebsocket") String myWebsocket, Session session) {
        logger.info("Websocket Start Connecting:" + myWebsocket);
        System.out.println("进入："+myWebsocket);
        clients.put(myWebsocket, session);
    }

    /*  
     * 收到客户端消息时触发
     * @author zhangjiuyang
     * @date 2018/7/24 17:27
     * @param [myWebsocket, message]
     * @return java.lang.String
     */
    @OnMessage
    public String onMessage(@PathParam("myWebsocket") String myWebsocket, String message) {
        return "Got your message ("+ message +").Thanks !";
    }

    /*
     * 异常时触发
     * @author zhangjiuyang
     * @date 2018/7/24 17:28
     * @param [myWebsocket, throwable]
     * @return void
     */
    @OnError
    public void onError(@PathParam("myWebsocket") String myWebsocket, Throwable throwable) {
        logger.info("Websocket Connection Exception:" + myWebsocket);
        logger.info(throwable.getMessage(), throwable);
        clients.remove(myWebsocket);
    }

    /*  
     * 关闭连接时触发
     * @author zhangjiuyang
     * @date 2018/7/24 17:31
     * @param [myWebsocket]
     * @return void
     */
    @OnClose
    public void onClose(@PathParam("myWebsocket") String myWebsocket) {
        logger.info("Websocket Close Connection:" + myWebsocket);
        clients.remove(myWebsocket);
    }

    /*  
     * 将数据传回到客户端
     * @author zhangjiuyang
     * @date 2018/7/24 17:33
     * @param [myWebsocket, message]
     * @return void
     */
    public static void broadcast(String myWebsocket, String message) {
        if (clients.containsKey(myWebsocket)) {
            clients.get(myWebsocket).getAsyncRemote().sendText(message);
        } else {
            throw new NullPointerException("[" + myWebsocket +"]Connection does not exist");
        }
    }
}
