package listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class MySessionListener implements HttpSessionAttributeListener{

	public void attributeAdded(HttpSessionBindingEvent se) {
		// HttpSessionBindingEvent代表了本次事件
		// 对象中封装了本次事件的具体信息
		
		String name=se.getName();
		Object value=se.getValue();
		
		System.out.println("监听到Session中添加了值:"+name+"~~~"+value);
	}

	public void attributeRemoved(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
		
	}

	public void attributeReplaced(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
		
	}

}
