package cn.tedu.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.tedu.bean.User;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.UserService;
import cn.tedu.utils.JDBCUtils;
import cn.tedu.utils.WebUtils;
 
public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 
	    request.setCharacterEncoding("utf-8");
		 
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String remname = request.getParameter("remname");
		System.out.println("username"+ username + "----------" + "password" + password);
		password = WebUtils.md5(password);
		 
		//静态工厂
		UserService service = BasicFactory.getFactory().getInstance(UserService.class);
		User user = service.loginUser(username, password);
		
		if(user != null){  
			if("true".equals(remname)){
				Cookie cookie = new Cookie("remname", URLEncoder.encode(username, "utf-8"));
				cookie.setPath(request.getContextPath()+"/");
				cookie.setMaxAge(3600 * 24 * 30);
				response.addCookie(cookie);
			}else{ 
				Cookie cookie = new Cookie("remname", "");
				cookie.setPath(request.getContextPath()+"/");
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
			 
   
			 
			request.getSession().setAttribute("user", user);
			
		 
			response.sendRedirect(request.getContextPath()+"/index.jsp");
			
		}else{ 
			request.setAttribute("msg", "用户名或密码错误!");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		
	}

}
