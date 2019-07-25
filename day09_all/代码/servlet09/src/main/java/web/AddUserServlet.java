package web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.runtime.HttpJspBase;

import dao.UserDAO;
import domain.User;
import util.DBUtils;

public class AddUserServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 需求：添加用户信息到数据库
		
		// 解决请求乱码
		request.setCharacterEncoding("utf-8");
		
		
		// 1. 接收用户的请求参数
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String email=request.getParameter("email");

		System.out.println(username+","+password+","+email);
		
		// 2. 表单验证(非空、格式验证...)
		// username的非空验证
		if(username==null || "".equals(username)) {
			// 向Request中添加错误提示信息
			request.setAttribute("msg_usr", "用户名不能为空");
			// 将请求转发 addUser.jsp
			request.getRequestDispatcher("/user/addUser.jsp")
								.forward(request, response);
			// 保证转发语句后面的代码不再执行
			return;
			
		}
		
		// 验证用户名是否重复
		UserDAO dao=new UserDAO();
		
		try {
			boolean hasUser=dao.getUserByUsername(username);
			if(hasUser) {
				// 添加错误提示信息
				request.setAttribute("msg_usr", "用户名已存在");
				// 请求转发addUser.jsp
				request.getRequestDispatcher("/user/addUser.jsp").forward(request, response);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg_usr", "添加出现异常，请稍后重试");
			request.getRequestDispatcher("/user/addUser.jsp").forward(request, response);
			return;
		}
		
		// 3. 将数据存入数据库
		
		User user=new User(-1, username, password, email);
		boolean flag = dao.saveUser(user);
		
		// 4. 生成响应内容
		// 通知Tomcat使用UTF-8对响应内容进行编码
		// response.setCharacterEncoding("UTF-8");
		// 通知浏览器使用UTF-8对响应内容进行解码
		response.setContentType("text/html;charset=utf-8");
		
		if(flag) {
		    // 重定向到用户列表
			// url的值是 ListUserServlet映射的url
			   response.sendRedirect(request.getContextPath()+"/user/listUser");
		}else {
			response.getWriter().write("添加失败！");
		}
	}
	
	
	

}
