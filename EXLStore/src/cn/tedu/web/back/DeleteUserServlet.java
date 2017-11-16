package cn.tedu.web.back;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.factory.BasicFactory;
import cn.tedu.service.ProdService;
import cn.tedu.service.UserService;
 
public class DeleteUserServlet extends HttpServlet {
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid = request.getParameter("pid");
		 System.out.println("*********" + pid);
		UserService service = BasicFactory.getFactory().getInstance(UserService.class);  
		boolean result = service.deleteUserBid(pid);
		
		if(result){
			request.getRequestDispatcher("/servlet/UserListServlet").forward(request,response);
		}else{
			System.out.println("删除失败");
		}
		
	
	}

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		doGet(request, response);
	}

}
