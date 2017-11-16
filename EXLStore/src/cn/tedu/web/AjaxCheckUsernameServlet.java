package cn.tedu.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.factory.BasicFactory;
import cn.tedu.service.UserService;
import cn.tedu.utils.JDBCUtils;

public class AjaxCheckUsernameServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		
		UserService service = BasicFactory.getFactory().getInstance(UserService.class);
		boolean result = service.hasUser(username);
		/*if(result){
			response.getWriter().write("�û����Ѵ���");
		}else{
			response.getWriter().write("�û�������ʹ��");
		}*/
		
		//利用三目运算简化代码
		response.getWriter().write(result ? "用户名已经存在！！" : "该名字可以使用"); 
	}

}
