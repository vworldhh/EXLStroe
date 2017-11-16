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
		/* ����T���͵�Class������Ϊ�˱���������������࣬
		 * �Ӷ��ó�������������Щ���Ժͷ���*/
		this.clazz = clazz;//����������ڲ�����������ķ���ʹ�á�
	}
	
	/* ����ѯ������еĵ�һ�м�¼��װ��һ��Bean(T)���󲢷��� */
	@Override
	public T handle(ResultSet rs) throws Exception {
		//rs-->sqlִ�к�ó��Ľ��
		//�������Class���󣬻�֪�������ϵ�set����������
		if(rs.next()){
			//1.ͨ�������͵�Class���󴴽������͵�ʵ��
			//�൱��User user = new User();
			T t = (T) clazz.newInstance();
			
			//2.����T�ó��������а�����Щ�����ķ���������
			BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
			//2.1����beanInfo��ȡT�����а�������������������
			PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
			//2.2�����������������飬��ȡÿһ���������������ٻ�ȡ�������������а��������Լ���ص�set��get������
			for(PropertyDescriptor pd : pds){
				//2.3.��ȡ��ǰ�������е�������
				String name = pd.getName();
				//2.4.��ȡ��ǰ��������������ص�set����
				Method method = pd.getWriteMethod();
				//2.5.��ȡ������е�һ�м�¼��ÿһ�е�ֵ
				//����������name��ȡrs��ָ���е�ֵ
				
				try {
					method.invoke(t, rs.getObject(name));
				} catch (Exception e) {
					//�������class����ȥ�����Ҷ�Ӧ���У��Ҳ����ͻ��׳��쳣��ʱ����Ӧ�����������ԣ�����ִ�к����ѭ����
					continue;
				}
				//2.6.��������е����ݷ�װ��Bean����(t)��
				
				
			}
			return t;
		}
		
		return null;
	}

	
}
