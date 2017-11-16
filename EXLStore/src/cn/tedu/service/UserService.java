package cn.tedu.service;

import java.util.List;

import cn.tedu.bean.User;
import cn.tedu.exception.MsgException;

public interface UserService extends Service {
 
	public void registUser(User user) throws MsgException;
	
	 
	
	public User loginUser(String username, String password);
	
	 
	public boolean hasUser(String username);
	

	public List<User> findAllUsers();



	public boolean deleteUserBid(String pid);
}
