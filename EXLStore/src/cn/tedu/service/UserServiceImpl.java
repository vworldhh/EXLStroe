package cn.tedu.service;

import java.util.List;

import cn.tedu.bean.User;
import cn.tedu.dao.UserDao;
import cn.tedu.exception.MsgException;
import cn.tedu.factory.BasicFactory;

public class UserServiceImpl implements UserService{
	private UserDao dao = BasicFactory.getFactory().getInstance(UserDao.class);
	
	 
	public void registUser(User user) throws MsgException { 
		//判断用户名是否可用
		boolean result = dao.findUserByUsername(user.getUsername());
		if(result){//判断用户是否存在
			throw new MsgException("用户名已存在!");
		} 
		//用户不存在
		dao.addUser(user);
	}
	 
	public User loginUser(String username, String password) {
		return dao.findUserByUsernameAndPassword(username, password);
	}
	
 
	public boolean hasUser(String username) {
		return dao.findUserByUsername(username);
	}

	
	
	@Override
	public List<User> findAllUsers() {
		// TODO Auto-generated method stub
		return dao.findAllUsers();
	}

	@Override
	public boolean deleteUserBid(String pid) {
		// TODO Auto-generated method stub
		return dao.deleteUserBid(pid);
	}
	
}
