package web;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int count; // 代表本次是第几次访问
		
		// 获取ServletContext对象
		ServletContext sc=getServletContext();
		
		String encode=sc.getInitParameter("encode");
		
		
		// 获取sc中保存的count的值
		Integer i=(Integer) sc.getAttribute("count");
		
		if(i==null) {//第一次
			count=1;
		}else {
			count=++i;
		}
		
		// 将count的值存入ServletContext中
		sc.setAttribute("count", count);
		
		// 生成响应内容
		response.getWriter().write("count="+count);
		
		
	}

}
