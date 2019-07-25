package web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 解决乱码
		// 处理post请求乱码
		request.setCharacterEncoding("utf-8");
		// 处理响应乱码
		response.setContentType("text/html;charset=utf-8");
		
		// 接收请求参数
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		// 执行具体逻辑
		UserDAO dao=new UserDAO();
		boolean flag=dao.getUserByUAP(username, password);
		
		// 返回响应内容
		if(flag) {
			// 向Session中添加登录状态
			request.getSession().setAttribute("user", username);
			
			response.getWriter().write("登录成功");
		}else {
			// 将请求转发给login.jsp
			// 1. 向request中绑定参数
			request.setAttribute("msg", "用户名或密码错误");
			
			// 2. 获取请求的转发器 -> path对应转发的目的地
			RequestDispatcher rd=
					request.getRequestDispatcher("/login.jsp");
		
			// 3. 转发请求
			rd.forward(request, response);
		}
		
		
		
		
		
		
		//String result=flag?"登录成功":"登录失败";
		//response.getWriter().write(result);
		
	}

}
