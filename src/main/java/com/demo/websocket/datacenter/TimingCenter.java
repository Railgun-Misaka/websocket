package com.demo.websocket.datacenter;

import java.text.SimpleDateFormat;
import java.util.Date;


import com.demo.websocket.manager.WebsocketManager;

public class TimingCenter implements Runnable{
     
	private TimingCenter() {
		
	}
	/**
	 * 单例模式     保证开放一条线程
	 * 开放多条时报错
	 * @author Fly
	 *
	 */
	private static class TimingCentermpl{
		private static TimingCenter timingCenter = new TimingCenter();
		private static Thread thread = new Thread(timingCenter);
	}
	
    public static void startup(){
    	TimingCentermpl.thread.start();
    }
    
    public void run() {
		while (true) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(WebsocketManager.getTotal() == 0) {
				continue ;
			}
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String now = sdf.format(date);
			WebsocketManager.broadCast(now);
		}
	}
    
/* 当JVM从内存中反序列化地"组装"一个新对象时，就会自动调用这个 readResolve方法来返回我们指定好的对象了, 单例规则也就得到了保证。
   readResolve()的出现允许程序员自行控制通过反序列化得到的对象。*/
//  protected Object readResolve() {
//  System.out.println("调用了readResolve方法");
//  return InnerClass.singletonStaticInnerSerialize;
//}
}
