package cn.nulladev.test;

import java.awt.Robot;
import java.awt.event.KeyEvent;

public class AutoPress extends Thread {
	
	public int t = 2000;
	
	public synchronized void run() {
		while(true) {
			try {
				System.out.println("自动保存小助手 正在运行");
				Robot r = new Robot();
				r.keyPress(KeyEvent.VK_CONTROL);
				r.delay(50);
				r.keyPress(KeyEvent.VK_S);
				r.delay(50);
				r.keyRelease(KeyEvent.VK_S);
				r.delay(50);
				r.keyRelease(KeyEvent.VK_CONTROL);
				r.delay(50);
				sleep(t);
			} catch(Exception e) {
				e.getStackTrace();
			}
		}
	}

}
