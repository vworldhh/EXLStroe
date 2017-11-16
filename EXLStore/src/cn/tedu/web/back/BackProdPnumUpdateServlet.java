package cn.tedu.web.back;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.factory.BasicFactory;
import cn.tedu.service.ProdService;

public class BackProdPnumUpdateServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.��ȡ��Ʒ��id����Ҫ�޸�Ϊ��������
		String pid = request.getParameter("pid");
		int pnum = Integer.parseInt(request.getParameter("pnum"));
		//2.����service��ķ����޸�ָ����Ʒ�Ŀ������
		ProdService service = BasicFactory.getFactory().getInstance(ProdService.class);
		boolean result = service.updatePnum(pid,pnum);
		//3.������Ӧ
		response.getWriter().write(result+"");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
