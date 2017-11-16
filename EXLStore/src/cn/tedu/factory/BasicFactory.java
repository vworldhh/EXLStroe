package cn.tedu.factory;

import java.io.FileInputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Properties;

import cn.tedu.anno.Tran;
import cn.tedu.dao.Dao;
import cn.tedu.service.Service;
import cn.tedu.utils.TranManager;
 
public class BasicFactory {
	private static BasicFactory factory = new BasicFactory();
	private static Properties prop = new Properties();
	public static BasicFactory getFactory() {
		return factory;
	}

	private BasicFactory() {
	}

	static {
	 
		try {
			String confPath = BasicFactory.class.getClassLoader().getResource("conf.properties").getPath();
			prop.load(new FileInputStream(confPath));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	/*
	 * ����conf.properties�����ļ��е�������Ϣ ����UserDao��ʵ�� UserDao=cn.tedu.dao.UserDaoImpl
	 */
	public <T> T getInstance(Class<T> clazz) {
		try {
			// 1.��ȡ�����ļ������õ���Ϣ(UserDao��ʵ����)
			// cn.tedu.dao.UserDaoImpl
			String className = prop.getProperty(clazz.getSimpleName());

			// 2.�������ȫ�޶����ƻ�ø����Class����
			Class clz = Class.forName(className);

			//����t��Service�������Dao�����
			if(Service.class.isAssignableFrom(clz)){
				// 3.���÷��似�����ݸ���Class���󴴽������ʵ��
				final T t = (T) clz.newInstance();
				//service��
				//�����������
				@SuppressWarnings("unchecked")
				T proxy = (T) Proxy.newProxyInstance(clz.getClassLoader(), clz.getInterfaces(), new InvocationHandler() {
					
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						Object result = null;
						//�ж�method�����Ƿ�ʹ���������ע��@Tran
						if(method.isAnnotationPresent(Tran.class)){
							//ʹ��������
							try {//��������
								TranManager.startTran();
								//ִ��ί���������ʵ���󣩵ķ���
								result = method.invoke(t, args);
								//�ύ����
								TranManager.commitTran();
							}catch (InvocationTargetException e) {
								e.printStackTrace();
								TranManager.rollbackTran();
								throw e.getTargetException();
							} finally {
								TranManager.releseTran();
							}
						}else{
							//δʹ������
							try {
								result = method.invoke(t, args);
							} catch (InvocationTargetException e) {
								e.printStackTrace();
								throw e.getTargetException();
							} finally {
								TranManager.releseTran();
							}
						}
						return result;
					}
				});
				return proxy;
			}else if(Dao.class.isAssignableFrom(clz)){
				//Dao��
				return (T)clz.newInstance();
			}else{
				//�Ȳ���Service��Ҳ����Dao��
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}
}
