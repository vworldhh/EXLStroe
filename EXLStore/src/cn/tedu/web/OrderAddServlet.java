package cn.tedu.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.bean.Order;
import cn.tedu.bean.OrderItem;
import cn.tedu.bean.Product;
import cn.tedu.bean.User;
import cn.tedu.exception.MsgException;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.OrderService;
 
public class OrderAddServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		
		request.setCharacterEncoding("utf-8");
		
		User user = (User) request.getSession().getAttribute("user");
		if(user == null){
			
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			
		}
		 
	 
		Order order = new Order();
		
		order.setId(UUID.randomUUID().toString());
		
		order.setReceiverinfo(request.getParameter("receiverinfo"));
		
		order.setPaystate(0);
		
		order.setUser_id(user.getId());
	 
		double totalMoney = 0;
		List<OrderItem> list = new ArrayList<OrderItem>();
		Map<Product, Integer> cartmap = (Map<Product, Integer>) request.getSession().getAttribute("cartmap");
		for (Map.Entry<Product, Integer> entry : cartmap.entrySet()) {
			
			OrderItem item = new OrderItem();
			item.setOrder_id(order.getId());
			item.setProduct_id(entry.getKey().getId());
			item.setBuynum(entry.getValue());
			totalMoney += entry.getKey().getPrice() * entry.getValue();
			list.add(item);
		}
		order.setMoney(totalMoney);
		 
		OrderService service = BasicFactory.getFactory().getInstance(OrderService.class);
		try {
			service.addOrder(order, list);
			 
			cartmap.clear();
			 
			response.sendRedirect(request.getContextPath()+"/servlet/OrderListServlet");

		} catch (MsgException e) {
			
			request.setAttribute("msg", e.getMessage());
			
			request.getRequestDispatcher("/cart.jsp").forward(request, response);
			
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
