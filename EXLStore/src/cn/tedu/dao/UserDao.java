package cn.tedu.dao;
import java.util.List;

import cn.tedu.bean.User;
public interface UserDao extends Dao {
	
 
	public boolean findUserByUsername(String username);
	 
	public void addUser(User user);
	
	 
	public User findUserByUsernameAndPassword(String username, String password);

	
	
	public List<User> findAllUsers();

	public boolean deleteUserBid(String pid);
}
