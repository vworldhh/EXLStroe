package cn.tedu.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.bean.Product;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.ProdService;
/**
 * �����ѯָ��id��Ʒ����Ϣ
 * @author ���Ѻ�
 *
 */
public class ProdInfoServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.��ȡ��Ҫ��ѯ����Ʒ��id
		String pid = request.getParameter("pid");
		//2.����service��ķ�������id��ѯָ������Ʒ��Ϣ
		ProdService service = BasicFactory.getFactory().getInstance(ProdService.class);
		Product prod = service.findProdById(pid);
		//3.����Ʒ�������request����
		request.setAttribute("prod", prod);
		//4.ͨ��ת������Ʒ��Ϣ������Ʒ������ҳ�棨prod_info.jsp��
		request.getRequestDispatcher("/prod_info.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
