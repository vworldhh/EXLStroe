package cn.tedu.bean;

import java.util.Map;

public class OrderInfo {

	private Order order;
	private Map<Product, Integer> map;
	
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Map<Product, Integer> getMap() {
		return map;
	}
	public void setMap(Map<Product, Integer> map) {
		this.map = map;
	}
	
	
}
