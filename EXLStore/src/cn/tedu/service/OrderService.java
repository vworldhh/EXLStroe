package cn.tedu.service;

import java.util.List;

import cn.tedu.anno.Tran;
import cn.tedu.bean.Order;
import cn.tedu.bean.OrderInfo;
import cn.tedu.bean.OrderItem;
import cn.tedu.bean.SaleInfo;
import cn.tedu.bean.User;
import cn.tedu.exception.MsgException;

public interface OrderService extends Service {

	/**
	 * 
	 * @param order
	 * @param list
	 * @throws MsgException 
	 */
	@Tran
	void addOrder(Order order, List<OrderItem> list) throws MsgException;

	/**
	 * �����û���Ϣ��ѯ���еĶ�����Ϣ
	 * @param user
	 * @return
	 */
	List<OrderInfo> findOrderInfoByUserId(int userId);

	/**
	 * ���ݶ���idɾ�������������Ϣ��orders��orderitem��
	 * ��ԭ��Ʒ���products
	 * @param oid ����id
	 * @throws MsgException ɾ�����������ڻ��߶�����֧��
	 */
	@Tran
	void delOrderByOid(String oid) throws MsgException;

	/**
	 * ���ݶ�����id��ѯ��������ϸ��Ϣ
	 * @param oid
	 * @return ������Ϣ��Ӧ��Order����Ϣ
	 */
	Order findOrderByOid(String oid);

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
