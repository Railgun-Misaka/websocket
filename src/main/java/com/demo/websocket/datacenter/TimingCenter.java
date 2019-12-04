package com.demo.websocket.datacenter;

import java.text.SimpleDateFormat;
import java.util.Date;


import com.demo.websocket.manager.WebsocketManager;

public class TimingCenter implements Runnable{
     
	private TimingCenter() {
		
	}
	/**
	 * ����ģʽ     ��֤����һ���߳�
	 * ���Ŷ���ʱ����
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
    
/* ��JVM���ڴ��з����л���"��װ"һ���¶���ʱ���ͻ��Զ�������� readResolve��������������ָ���õĶ�����, ��������Ҳ�͵õ��˱�֤��
   readResolve()�ĳ����������Ա���п���ͨ�������л��õ��Ķ���*/
//  protected Object readResolve() {
//  System.out.println("������readResolve����");
//  return InnerClass.singletonStaticInnerSerialize;
//}
}
