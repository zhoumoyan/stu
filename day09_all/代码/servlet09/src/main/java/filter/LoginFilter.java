package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	
	/**
	 * HttpServletRequest继承了ServletRequest
	 * 	提供了处理Http请求相关的API
	 * 	例如  getSession()，getMethod()
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		// 将ServletRequest强转为 HttpServletRequest
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse resp=(HttpServletResponse)response;
		
		// 判断用户是否已经登录
		HttpSession session=req.getSession(false);
		
		if(session==null || 
				session.getAttribute("user")==null) {
			// 未登录 -> 重定向到登录页面
			resp.sendRedirect(req.getContextPath()+"/login.jsp");
		}else {
			// 登录 -> 放行
			chain.doFilter(request, response);
		}

	}

	public void destroy() {
		
	}



	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
