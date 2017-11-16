package cn.tedu.web.back;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.bean.User;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.ProdService;
import cn.tedu.service.UserService;
 
public class UserListServlet extends HttpServlet {
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  
		UserService service = BasicFactory.getFactory().getInstance(UserService.class);
	 
		
		List<User> userList = service.findAllUsers();	
		
		 
		
		request.setAttribute("userList", userList);
		
		request.getRequestDispatcher("/backend/userlist.jsp").forward(request, response);
		
	}

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 
		doGet(request, response);
	}

}
