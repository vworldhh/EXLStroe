package cn.tedu.web.back;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.factory.BasicFactory;
import cn.tedu.service.ProdService;

public class BackProdDelServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    //得到商品的id
		String pid = request.getParameter("pid");
		
		String stat = request.getParameter("stat");
		 System.out.println("*********" + pid);
		 System.out.println("*********" + stat); 
		 
		 
		ProdService service = BasicFactory.getFactory().getInstance(ProdService.class); 
	    
		boolean result =  service.UpdataProdStat(pid,stat);
		 
		 System.out.println(result);
		 
		 
	   request.getRequestDispatcher("/servlet/BackProdListServlet").forward(request,response);
		 
		 
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
