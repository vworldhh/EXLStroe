package cn.tedu.utils;

import java.sql.Connection;
import java.sql.SQLException;

public class TranManager {

	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>(){
		protected Connection initialValue(){
			return JDBCUtils.getConn();
		}
	};
	private TranManager(){}
	
	public static Connection getConn(){
		return tl.get();
	}
	
	//添加事务
	public static void startTran(){
		try {
			tl.get().setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//提交事务
	public static void commitTran(){
		try {
			tl.get().commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//事务的回滚
	public static void rollbackTran(){
		try {
			tl.get().rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//去除事务
	public static void releseTran(){
		try {
			 
			tl.get().close();
		 
			tl.remove();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
