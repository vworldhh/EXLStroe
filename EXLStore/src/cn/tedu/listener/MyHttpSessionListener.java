package cn.tedu.listener;

import java.util.HashMap;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import cn.tedu.bean.Product;
 
public class MyHttpSessionListener implements HttpSessionListener {

	@Override
	
	public void sessionCreated(HttpSessionEvent se) {
		
		se.getSession().setAttribute("cartmap", new HashMap<Product, Integer>());
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		
	}

	

}
