package cn.tedu.dao;

import java.sql.SQLException;
import java.util.List;

import cn.tedu.bean.Product;
import cn.tedu.bean.User;
import cn.tedu.utils.BeanHandler;
import cn.tedu.utils.BeanListHandler;
import cn.tedu.utils.JDBCUtils;
public class UserDaoImpl implements UserDao {
	
	public boolean findUserByUsername(String username) {
		try {
			User user = JDBCUtils.query("select * from user where username=?", new BeanHandler<User>(User.class), username);
			return user != null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public void addUser(User user) {
		 
		
		try {
			JDBCUtils.update("insert into user values(null, ?,?,?,?,?)", 
					user.getUsername(),
					user.getPassword(),
					user.getNickname(),
					user.getEmail(),
					"user");
			        
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	
	public User findUserByUsernameAndPassword(String username, String password) {
		try {
			User user = JDBCUtils.query("select * from user where username=? and password=?", new BeanHandler<User>(User.class), username, password);
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	
	@Override
	public List<User> findAllUsers() {
		// TODO Auto-generated method stub
		try { 
			return (List<User>) JDBCUtils.query("select * from user", new BeanListHandler<User>(User.class));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		 
	}
	@Override
	public boolean deleteUserBid(String pid) {
		// TODO Auto-generated method stub
		String sql = "delete from user where id=?";
		try {
			 return JDBCUtils.update(sql, pid) > 0;
			  
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
