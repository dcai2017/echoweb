package echoweb.dao;

import java.util.List;

import echoweb.model.User;


public interface UserDao {

	public User findByName(String name);
	
	public List<User> findAll();
	public List<User> login(String username, String password);

}