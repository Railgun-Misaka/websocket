package com.demo.websocket.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import com.demo.websocket.server.WebsocketServer;
 /**
  * websocket����
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
        System.out.println("�������Ӽ��룡 ��ǰ���������ǣ�"+ servers.size());
    }
    public static void remove(WebsocketServer server){
        servers.remove(server);
        System.out.println("�������˳��� ��ǰ���������ǣ�"+ servers.size());
    }
     
}
