package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent sce) {
		// 添加ServletContext对象被创建后马上执行的逻辑
		System.out.println("监听到SC被创建");
	}

	public void contextDestroyed(ServletContextEvent sce) {
		// 添加ServletContext对象被销毁后马上执行的逻辑
		System.out.println("监听到SC被销毁");
	}

}
