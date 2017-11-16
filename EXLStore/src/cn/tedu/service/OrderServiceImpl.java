package cn.tedu.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.tedu.anno.Tran;
import cn.tedu.bean.Order;
import cn.tedu.bean.OrderInfo;
import cn.tedu.bean.OrderItem;
import cn.tedu.bean.Product;
import cn.tedu.bean.SaleInfo;
import cn.tedu.dao.OrderDao;
import cn.tedu.dao.ProdDao;
import cn.tedu.exception.MsgException;
import cn.tedu.factory.BasicFactory;
import cn.tedu.utils.JDBCUtils;
import cn.tedu.utils.TranManager;

public class OrderServiceImpl implements OrderService{

	private OrderDao order_dao = BasicFactory.getFactory().getInstance(OrderDao.class);
	private ProdDao prod_dao = BasicFactory.getFactory().getInstance(ProdDao.class);
	
	@Override
	public void addOrder(Order order, List<OrderItem> list) throws MsgException {
		//����Dao��ķ�����Ӷ�����Ϣ
		order_dao.addOrder(order);
		
		for (OrderItem orderItem : list) {
			//��鹺�������Ƿ�С�ڿ������Product.pnum
			//��ȡ��������
			int buyNum = orderItem.getBuynum();
			
			//�������
			String pid = orderItem.getProduct_id();
			Product prod = prod_dao.findProdById(pid);
			int pnum = prod.getPnum();
			if(buyNum>pnum){
				throw new MsgException("����������㣬��Ʒ����"+prod.getName()+"����Ʒʣ��������"+pnum);
			}
			
			//����Dao��ķ�����Ӷ���������Ϣ
			order_dao.addOrderItem(orderItem);
			
			//�����������ӿ�������п۳�
			prod_dao.updatePnum(pid, pnum-buyNum);
		}
		
		
	}
	
	@Override
	public List<OrderInfo> findOrderInfoByUserId(int userId) {
		List<Order> orderList = order_dao.findOrderByUserId(userId);
		List<OrderInfo> orderInfoList = new ArrayList<OrderInfo>();

		if(orderList == null){
			return null;
		}
		//����ÿһ������ͨ������ID��ѯ��ǰ�����а��������ж��������Ϣ
		for (Order order : orderList) {
			List<OrderItem> orderItemList = order_dao.findOrderItemByOrderId(order.getId());
			//����ÿ�������ͨ������������ȡ��Ʒ��Ϣ����Ʒ�Ĺ�������
			Map<Product, Integer> map = new HashMap<Product, Integer>();
			if(orderItemList != null){
				for (OrderItem orderItem : orderItemList) {
					//ͨ����Ʒid��ȡ��Ʒ����
					Product prod = prod_dao.findProdById(orderItem.getProduct_id());
					//��ȡ��������
					int buyNum = orderItem.getBuynum();
					map.put(prod, buyNum);
				}
			}
			OrderInfo orderInfo = new OrderInfo();
			orderInfo.setOrder(order);
			orderInfo.setMap(map);
			
			orderInfoList.add(orderInfo);
		}
//		List<OrderItem> orderItemList = 
		
		return orderInfoList;
	}

	@Override
	public void delOrderByOid(String oid) throws MsgException {
		//������Ʒ�Ķ���id��ѯ������Ϣ
		Order order = order_dao.findOrderByOid(oid);
		//�ж϶����Ƿ����
		if(order == null){
			throw new MsgException("��ǰ���������ڣ�");
		}
		//�ж϶����Ƿ���֧��,ֻ��δ֧���Ķ����ſ���ɾ��
		if(order.getPaystate() != 0){
			throw new MsgException("ֻ��δ֧���Ķ�������ɾ����");
		}
		//���ݶ���id����ѯ��ǰ���������еĶ�����Ŀ
		List<OrderItem> items = order_dao.findOrderItemByOrderId(oid);
		//����items
		if(items != null){
			for (OrderItem item : items) {
				//��ԭ��Ʒ�Ŀ��
				prod_dao.changePnum(item.getProduct_id(), item.getBuynum());
			}
		}
		//ɾ���ö��������еĶ�����Ŀ
		order_dao.deleteOrderItemsByOid(oid);
		//ɾ��������Ϣ
		order_dao.deleteOrderByOid(oid);
	}

	@Override
	public Order findOrderByOid(String oid) {
		return order_dao.findOrderByOid(oid);
	}

	@Override
	public void updatePaystateByOid(String oid, int paystate) {
		order_dao.updatePaystateByOid(oid, paystate);
	}

	@Override
	public List<SaleInfo> findSaleInfos() {
		return order_dao.findSaleInfos();
	}

 

}
