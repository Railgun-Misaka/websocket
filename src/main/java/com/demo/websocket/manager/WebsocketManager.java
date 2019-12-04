package com.demo.websocket.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import com.demo.websocket.server.WebsocketServer;
 /**
  * websocket管理
  * @author Fly
  *
  */
public class WebsocketManager {
 
    private static Collection<WebsocketServer> servers = Collections.synchronizedCollection(new ArrayList<WebsocketServer>());
     
    public static void broadCast(String msg){
        for (WebsocketServer server : servers) {
            try {
                server.sendMessage(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
     
    public static int getTotal(){
        return servers.size();
    }
    public static void add(WebsocketServer server){
        servers.add(server);
        System.out.println("有新连接加入！ 当前总连接数是："+ servers.size());
    }
    public static void remove(WebsocketServer server){
        servers.remove(server);
        System.out.println("有连接退出！ 当前总连接数是："+ servers.size());
    }
     
}
