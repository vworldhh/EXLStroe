package cn.tedu.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BeanListHandler<T> implements ResultSetHandler<List<T>>{

	private Class clazz;
	public BeanListHandler(Class clazz) {
		/* ����T���͵�Class������Ϊ�˱���������������࣬
		 * �Ӷ��ó�������������Щ���Ժͷ���*/
		this.clazz = clazz;//����������ڲ�����������ķ���ʹ�á�
	}
	
	/* ����ѯ������е�ÿһ�м�¼��װ��һ��Bean(T)�������һ��List���ϲ����� */
	@Override
	public List<T> handle(ResultSet rs) throws Exception {
		//rs-->sqlִ�к�ó��Ľ��
		//�������Class���󣬻�֪�������ϵ�set����������
		List<T> list = new ArrayList<T>();
		while(rs.next()){
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
					Object value = null;
					if(pd.getPropertyType()==int.class){
						value = rs.getInt(name);
					}else{
						value = rs.getObject(name);
					}
					method.invoke(t, value);
				} catch (Exception e) {
					//�������class����ȥ�����Ҷ�Ӧ���У��Ҳ����ͻ��׳��쳣��ʱ����Ӧ�����������ԣ�����ִ�к����ѭ����
					continue;
				}
				//2.6.��������е����ݷ�װ��Bean����(t)��
				
				
			}
			list.add(t);
		}
		
		return list.size() == 0 ? null : list;
	}

	
}
