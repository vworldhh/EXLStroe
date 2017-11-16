package cn.tedu.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.bean.Product;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.ProdService;

public class ProdListByConditionServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//1.��ȡ��ѯ��������Ʒ�����ƣ�name������Ʒ�ķ��ࣨcategory������Ʒ����ͼ۸�minprice������Ʒ����߼۸�maxprice��
		String name = request.getParameter("name");
		String category = request.getParameter("category");
		String minprice = request.getParameter("minprice");
		String maxprice = request.getParameter("maxprice");
		//2.�Բ�ѯ�������д���
		String _name = "";
		String _category = "";
		double _minprice = 0;
		double _maxprice = Double.MAX_VALUE;
		if(name != null && !"".equals(name)){
			_name = name;
		}
		if(category != null && !"".equals(category)){
			_category = category;
		}
		String reg = "^(0(\\.[0-9]+)?)|([1-9][0-9]*(\\.[0-9]+)?)$";//У��С��
		if(minprice != null && !"".equals(minprice)){
			if(minprice.matches(reg)){
				_minprice = Double.parseDouble(minprice);
			}
		}
		if(maxprice != null && !"".equals(maxprice)){
			if(maxprice.matches(reg) && Double.parseDouble(maxprice) >= _minprice){
				_maxprice = Double.parseDouble(maxprice);
			}
		}
		
		//3.����������ѯ���з�����������Ʒ
		ProdService service = BasicFactory.getFactory().getInstance(ProdService.class);
		List<Product> list = service.findAllByCondition(_name, _category, _minprice, _maxprice);
		//4.������������Ʒ��list���ϴ���request��
		request.setAttribute("list", list);
		//5.ͨ��ת����������Ʒ����ǰ̨����Ʒ�б�ҳ��
		request.getRequestDispatcher("/prod_list.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
