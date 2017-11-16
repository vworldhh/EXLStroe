package cn.tedu.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
 
public class MyServletContextListener implements ServletContextListener{
	@Override
	public void contextInitialized(ServletContextEvent sce) { 
		
		ServletContext context = sce.getServletContext(); 
	 
		context.setAttribute("app", context.getContextPath()); 
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}
}
