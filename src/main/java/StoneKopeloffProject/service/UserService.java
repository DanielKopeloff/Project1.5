package StoneKopeloffProject.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import StoneKopeloffProject.dao.ReimbursementDao;
import StoneKopeloffProject.dao.UserDao;
import StoneKopeloffProject.model.User;
import org.apache.log4j.Logger;

public class UserService {
	private UserDao ud = new UserDao();
	private static UserService instance;
	private static final Logger LOGGER = Logger.getLogger(UserService.class);

	private UserService() {
	}

	synchronized public static UserService getInstance() {
		if (instance==null) {
			instance = new UserService();
		}
		return instance;
	}

	public List<User> fetchAllUsers() {
		return ud.getList();
	}

	public User getUserById(int id) {
		return ud.getById(id);
	}

	public User getUserByUsername(String username) {
		return ud.getByUsername(username);
	}

	public User getUserByLogin(String user, String pass) {
		User u = ud.getByUsername(user);

		if(u != null && u.getPassword().equals(pass)) {
			return u;
		}
		return null;
	}
	public boolean addUser (String userName, String password,String firstName, String lastName,String email) {
		ud.insert(new User(userName, password ,firstName ,lastName, email,0));
		return true;
	}

	public void addUser(User u){
		ud.update(u);
	}

	public boolean addManager (String userName, String password,String firstName, String lastName,String email) {
		ud.insert(new User(userName, password ,firstName ,lastName, email,1));
		return true;
	}

	public void updateUser(User u){
		ud.update(u);
	}
}


