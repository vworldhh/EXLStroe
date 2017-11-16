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
		//���ն���id
		String oid = request.getParameter("oid");
		//����ҵ�����
		OrderService service = BasicFactory.getFactory().getInstance(OrderService.class);
		//����ҵ����ɾ������
		try {
			service.delOrderByOid(oid);
			response.getWriter().write("����ɾ���ɹ���2����Զ���ת");
		} catch (MsgException e) {
			response.getWriter().write("����ɾ��ʧ�ܣ�2����Զ���ת");
		}
		response.setHeader("refresh", "2;url="+request.getContextPath()+"/servlet/OrderListServlet");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
