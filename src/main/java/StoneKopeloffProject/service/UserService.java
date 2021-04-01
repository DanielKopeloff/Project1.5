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
	//credit http://emailregex.com/ for the regex used below
	public boolean addUser (String userName, String password,String firstName, String lastName,String email) {
		if (!email.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")) {
			return false;
		}
		ud.insert(new User(userName, password ,firstName ,lastName, email,0));
		return true;
	}
}


