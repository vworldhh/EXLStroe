package cn.tedu.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.bean.Order;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.OrderService;
import cn.tedu.utils.PaymentUtil;
import cn.tedu.utils.PropUtils;

public class PayServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 
		String oid = request.getParameter("orderid");
		String money  = request.getParameter("moneyinp");
		System.out.println("oid" + oid + "********" + "money" + money);
		
		request.setAttribute("p2_Order",  oid);
		request.setAttribute("p3_Amt",  money);

		request.getRequestDispatcher("/confirm.jsp").forward(request, response);
	}

}
