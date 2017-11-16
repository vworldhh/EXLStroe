package cn.tedu.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.tedu.bean.Order;
import cn.tedu.bean.OrderItem;
import cn.tedu.bean.SaleInfo;
import cn.tedu.utils.BeanHandler;
import cn.tedu.utils.BeanListHandler;
import cn.tedu.utils.JDBCUtils;

public class OrderDaoImpl implements OrderDao {

	@Override
	public void addOrder(Order order) {
		try {
			String sql = "insert into orders values(?,?,?,?,null,?)";
			JDBCUtils.update(sql, order.getId(), order.getMoney(), order.getReceiverinfo(), order.getPaystate(), order.getUser_id());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void addOrderItem(OrderItem orderItem) {
		try {
			String sql = "insert into orderitem values(?, ?, ?)";
			JDBCUtils.update(sql, orderItem.getOrder_id(), orderItem.getProduct_id(), orderItem.getBuynum());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Order> findOrderByUserId(int userId) {
		try {
			String sql = "select * from orders where user_id=?";
			return JDBCUtils.query(sql, new BeanListHandler<Order>(Order.class), userId);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<OrderItem> findOrderItemByOrderId(String orderId) {
		try {
			String sql = "select * from orderitem where order_id=?";
			return JDBCUtils.query(sql, new BeanListHandler<OrderItem>(OrderItem.class), orderId);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}


	@Override
	public Order findOrderByOid(String oid) {
		String sql = "select * from orders where id=?";
		try {
			return JDBCUtils.query(sql, new BeanHandler<Order>(Order.class), oid);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void deleteOrderItemsByOid(String oid) {
		String sql = "delete from orderitem where order_id=?";
		try {
			JDBCUtils.update(sql, oid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteOrderByOid(String oid) {
		String sql = "delete from orders where id=?";
		try {
			JDBCUtils.update(sql, oid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updatePaystateByOid(String oid, int paystate) {
		String sql = "update orders set paystate=? where id=?";
		try {
			JDBCUtils.update(sql, paystate, oid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<SaleInfo> findSaleInfos() {
		String sql = "select pd.id prod_id,pd.name prod_name,sum(oi.buynum) sale_num from products pd,orderitem oi,orders od where pd.id=oi.product_id and oi.order_id=od.id and od.paystate=1 group by prod_id order by sale_num desc";
		try {
			return JDBCUtils.query(sql, new BeanListHandler<SaleInfo>(SaleInfo.class));
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<SaleInfo>();
		}
	}

	 
}
