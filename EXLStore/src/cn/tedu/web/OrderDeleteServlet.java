package cn.tedu.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.exception.MsgException;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.OrderService;

public class OrderDeleteServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//接收订单id
		String oid = request.getParameter("oid");
		//创建业务对象
		OrderService service = BasicFactory.getFactory().getInstance(OrderService.class);
		//调用业务层的删除方法
		try {
			service.delOrderByOid(oid);
			response.getWriter().write("订单删除成功，2秒后自动跳转");
		} catch (MsgException e) {
			response.getWriter().write("订单删除失败，2秒后自动跳转");
		}
		response.setHeader("refresh", "2;url="+request.getContextPath()+"/servlet/OrderListServlet");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
