package cn.tedu.web.back;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.bean.Product;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.ProdService;
 
public class BackProdListServlet extends HttpServlet {
	int allPage;
	int line;
	 
	int page;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 
		
		String showpage = request.getParameter("showpage");
		
		if(showpage == null){
			page = 1;
		}else{
			page = Integer.parseInt(showpage);
		}
		
		
		ProdService service = BasicFactory.getFactory().getInstance(ProdService.class);
		
		allPage = service.countpages();
		
		List<Product> list = service.findAll(page);
		 
		request.setAttribute("list", list);
		request.setAttribute("allPage", allPage);
		 
		request.getRequestDispatcher("/backend/prod_list.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
