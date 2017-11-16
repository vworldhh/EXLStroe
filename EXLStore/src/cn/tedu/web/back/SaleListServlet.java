package cn.tedu.web.back;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.bean.SaleInfo;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.OrderService;

public class SaleListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//����ҵ������
		OrderService service = BasicFactory.getFactory().getInstance(OrderService.class);
		//����ҵ��㷽����ѯȫ�������۰�
		List<SaleInfo> list = service.findSaleInfos();
		//��list���浽request��������
		request.setAttribute("list", list);
		//ת����sale_list.jsp
		request.getRequestDispatcher("/backend/sale_list.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
