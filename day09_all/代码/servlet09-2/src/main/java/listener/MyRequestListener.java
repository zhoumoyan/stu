package listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class MyRequestListener implements ServletRequestListener{

	public void requestDestroyed(ServletRequestEvent sre) {
		System.out.println("监听到Request被销毁");
	}

	public void requestInitialized(ServletRequestEvent sre) {
		System.out.println("监听到Request被创建");
	}

}
