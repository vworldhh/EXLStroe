package cn.tedu.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.bean.Product;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.ProdService;
 
public class CartUpdateServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		String pid = request.getParameter("pid");
		int buyNum = Integer.parseInt(request.getParameter("buyNum"));
	 
		ProdService service = BasicFactory.getFactory().getInstance(ProdService.class);
		Product prod = service.findProdById(pid);
	 
		Map<Product, Integer> map = (Map<Product, Integer>) request.getSession().getAttribute("cartmap");
		 

		if(buyNum < 0){
			map.remove(prod);
		}else{
			if(map.containsKey(prod)){
				map.put(prod, map.get(prod)+buyNum);
			}else{
				map.put(prod, buyNum);
			}
		}
		 
		response.sendRedirect(request.getContextPath()+"/cart.jsp");
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
