package cn.tedu.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.bean.OrderInfo;
import cn.tedu.bean.User;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.OrderService;
 
public class OrderListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1、设置接收参数的字符集编码格式
		response.setContentType("text/html;charset=utf-8");
		 
		//判断用户是否登录
		User user = (User) request.getSession().getAttribute("user");
      if(user == null){
			
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
			
		}
	 
		 //工厂模式将服务层注入进来
		OrderService service = BasicFactory.getFactory().getInstance(OrderService.class);
		 //通过用户的id将个人订单加入订单列表中
		List<OrderInfo> list = service.findOrderInfoByUserId(user.getId());
		 
		request.setAttribute("list", list);
		  
		//页面的跳转
		request.getRequestDispatcher("/order_list.jsp").forward(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
