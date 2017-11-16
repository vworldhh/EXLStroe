package cn.tedu.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.factory.BasicFactory;
import cn.tedu.service.OrderService;
import cn.tedu.service.UserService; 

 
public class okServlet extends HttpServlet {
	 
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pid = request.getParameter("oid");
		if(pid == null){
		   return;
		}
		
		System.out.println("******" + pid + "***********");
		
		OrderService service = BasicFactory.getFactory().getInstance(OrderService.class);
		
		service.updatePaystateByOid(pid, 1);
		
		
		 
		response.sendRedirect(request.getContextPath()+"/ok.jsp"); 
		
		
	}

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
