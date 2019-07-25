package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;

public class DelUserServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		// 1. 接收请求参数 id
		String idStr=request.getParameter("id");
		
		// 2. 数据类型转换
		int id=Integer.parseInt(idStr);
		
		// 3. 调用DAO对应的方法
		UserDAO dao=new UserDAO();
		boolean flag=dao.delUser(id);
		
		// 4. 根据执行结果，返回响应内容
		if(flag) {
			// 重定向到用户列表 -> ListUserServlet
			response.sendRedirect(request.getContextPath()+"/user/listUser");
		}else {
			response.getWriter().write("删除失败！");
		}

		
	}
	
	
	

}
