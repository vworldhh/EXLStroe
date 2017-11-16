package cn.tedu.utils;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import cn.tedu.bean.Product;

public class JDBCUtils {

	public static ComboPooledDataSource pool = new ComboPooledDataSource();
	
	private JDBCUtils() {}
 
	public static DataSource getPool(){
		return pool;
	}
	
	public static Connection getConn(){
		try {
			return pool.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	 
	public static <T> T query(String sql, ResultSetHandler<T> rsh, Object... params) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = TranManager.getConn();
			ps = conn.prepareStatement(sql);
			if(params != null){
				for(int i=0;i<params.length;i++){
					ps.setObject(i+1, params[i]);
				}
			}
			rs = ps.executeQuery();
			return rsh.handle(rs);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			close(rs, ps, null);
		}
		
	}
	
	 
	public static int update(String sql, Object... params) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = TranManager.getConn();
			ps = conn.prepareStatement(sql);
			if(params != null){
				for(int i=0;i<params.length;i++){
					ps.setObject(i+1, params[i]);
				}
			}
			return ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			close(rs, ps, null);
		}
	}
	
	public static void close(ResultSet rs, Statement stat, Connection conn) {
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				rs = null;
			}
		}
		if(stat != null){
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				stat = null;
			}
		}
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				conn = null;
			}
		}
	}
	
  public static int AllCountBook(){
		int count = 0;
		String sql = "select count(*) from products";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = TranManager.getConn();
			ps = conn.prepareStatement(sql); 
			rs = ps.executeQuery();
			while(rs!=null&&rs.next()){ 
				count=rs.getInt(1); 
			}
		 
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			close(rs, ps, null);
		}
		return count; 
	}
     
	
}
