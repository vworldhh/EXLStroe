package cn.tedu.dao;

import java.sql.Connection;
import java.util.List;

import cn.tedu.bean.Order;
import cn.tedu.bean.OrderItem;
import cn.tedu.bean.SaleInfo;

public interface OrderDao extends Dao {

	/**
	 * ������Ӷ�����Ϣ��orders��
	 * @param order ������Ϣ
	 */
	void addOrder(Order order);

	/**
	 * ��Ӷ�������Ϣ��orderitem��
	 * @param orderItem
	 */
	void addOrderItem(OrderItem orderItem);

	List<Order> findOrderByUserId(int userId);

	/**
	 * ���ݶ���id��ѯ���еĶ�������Ϣ
	 * @param orderId
	 * @return
	 */
	List<OrderItem> findOrderItemByOrderId(String orderId);

	/**
	 * ���ݶ���id��ѯ�����������Ϣ��orders��
	 * @param oid ����id
	 * @return ��װ�˶�Ӧ�����������Ϣ��Order�����
	 */
	Order findOrderByOid(String oid);

	/**
	 * ���ݶ���idɾ���ö����µ����ж�����Ŀ
	 * @param oid ����id
	 */
	void deleteOrderItemsByOid(String oid);

	/**
	 * ���ݶ���idɾ���ö�����Ϣ
	 * @param oid ����id
	 */
	void deleteOrderByOid(String oid);

	/**
	 * �޸Ķ�����֧��״̬
	 * @param oid ����id
	 * @param paystate �޸ĺ��֧��״̬
	 */
	void updatePaystateByOid(String oid, int paystate);

	/**
	 * ��ѯ���е����۰��б�
	 * @return ���۱��б��Ӧ����
	 */
	List<SaleInfo> findSaleInfos();

	 

	
}
