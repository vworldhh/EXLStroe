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
		//创建业务层对象
		OrderService service = BasicFactory.getFactory().getInstance(OrderService.class);
		//调用业务层方法查询全部的销售榜单集合对象
		List<SaleInfo> list = service.findSaleInfos();
		//定义保存销售榜单数据的对象
		StringBuffer buf = new StringBuffer("商品id,商品名称,销售数量\n");
		//遍历list集合，拼接数据
		for (SaleInfo info : list) {
			buf.append(info.getProd_id()).append(",").append(info.getProd_name()).append(",").append(info.getSale_num()).append("\n");
		}
		//准备文件名称
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String fname = "SaleList"+sdf.format(date)+".csv";
		//以告知浏览器以附件下载方式打开
		response.setHeader("Content-Disposition", "attachment;filename="+fname);
		//处理文件内容的乱码
		response.setContentType("text/html;charset=gbk");
		response.getWriter().write(buf.toString());
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
