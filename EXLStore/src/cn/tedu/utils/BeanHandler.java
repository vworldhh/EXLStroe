package cn.tedu.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BeanHandler<T> implements ResultSetHandler<T>{

	private Class clazz;
	public BeanHandler(Class clazz) {
		/* 接收T类型的Class对象，是为了便于下面剖析这个类，
		 * 从而得出该类型中有哪些属性和方法*/
		this.clazz = clazz;//保存在类的内部，方便下面的方法使用。
	}
	
	/* 将查询结果集中的第一行记录封装成一个Bean(T)对象并返回 */
	@Override
	public T handle(ResultSet rs) throws Exception {
		//rs-->sql执行后得出的结果
		//如何剖析Class对象，获知该类型上的set方法和属性
		if(rs.next()){
			//1.通过该类型的Class对象创建该类型的实例
			//相当于User user = new User();
			T t = (T) clazz.newInstance();
			
			//2.剖析T得出该类型中包含哪些公共的方法和属性
			BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
			//2.1根据beanInfo获取T类型中包含的属性描述器数组
			PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
			//2.2遍历属性描述器数组，获取每一个属性描述器，再获取该属性描述器中包含的属性及相关的set和get方法。
			for(PropertyDescriptor pd : pds){
				//2.3.获取当前描述器中的属性名
				String name = pd.getName();
				//2.4.获取当前描述器中属性相关的set方法
				Method method = pd.getWriteMethod();
				//2.5.获取结果集中第一行记录的每一列的值
				//根据属性名name获取rs中指定列的值
				
				try {
					method.invoke(t, rs.getObject(name));
				} catch (Exception e) {
					//如果根据class属性去表中找对应的列，找不到就会抛出异常此时我们应该跳过该属性，继续执行后面的循环。
					continue;
				}
				//2.6.将结果集中的数据封装到Bean对象(t)中
				
				
			}
			return t;
		}
		
		return null;
	}

	
}
