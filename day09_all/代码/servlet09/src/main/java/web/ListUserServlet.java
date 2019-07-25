package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import domain.User;
import util.DBUtils;

public class ListUserServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 从数据库查询所有用户信息
		UserDAO dao=new UserDAO();
		List<User> list=dao.listAllUser();
		
		// 将数据绑定到request中
		request.setAttribute("list", list);
		
		// 将请求转发给listUsers.jsp
		request.getRequestDispatcher("/WEB-INF/listUsers.jsp")
				.forward(request, response);
	}

}





