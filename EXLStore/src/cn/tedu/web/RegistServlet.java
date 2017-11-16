package cn.tedu.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import cn.tedu.bean.User;
import cn.tedu.exception.MsgException;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.UserService;
import cn.tedu.utils.WebUtils;

public class RegistServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1、设置接收参数的字符集编码格式
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
       //创建User对象 
		User user = new User();
		 
		try {
			//直接获取对象
			BeanUtils.populate(user, request.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} 
		try {
			user.checkData();
			 
			user.setPassword(WebUtils.md5(user.getPassword()));
			
			System.out.println("注册的对象是" + user.toString());
			UserService service = BasicFactory.getFactory().getInstance(UserService.class);
			service.registUser(user);
		} catch (MsgException e) {
		 
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("/regist.jsp").forward(request, response);
			return;
		}
		
		
		//页面跳转 
		response.getWriter().write("<h1 style='color:red;text-align:center'>注册成功</h1>");
		response.setHeader("refresh", "3;url="+request.getContextPath()+"/index.jsp");
	}

}
