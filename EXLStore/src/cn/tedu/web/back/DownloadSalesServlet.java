package cn.tedu.web.back;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.bean.SaleInfo;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.OrderService;

public class DownloadSalesServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//����ҵ������
		OrderService service = BasicFactory.getFactory().getInstance(OrderService.class);
		//����ҵ��㷽����ѯȫ�������۰񵥼��϶���
		List<SaleInfo> list = service.findSaleInfos();
		//���屣�����۰����ݵĶ���
		StringBuffer buf = new StringBuffer("��Ʒid,��Ʒ����,��������\n");
		//����list���ϣ�ƴ������
		for (SaleInfo info : list) {
			buf.append(info.getProd_id()).append(",").append(info.getProd_name()).append(",").append(info.getSale_num()).append("\n");
		}
		//׼���ļ�����
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String fname = "SaleList"+sdf.format(date)+".csv";
		//�Ը�֪������Ը������ط�ʽ��
		response.setHeader("Content-Disposition", "attachment;filename="+fname);
		//�����ļ����ݵ�����
		response.setContentType("text/html;charset=gbk");
		response.getWriter().write(buf.toString());
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
