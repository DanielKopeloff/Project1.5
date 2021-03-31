package StoneKopeloffProject.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import StoneKopeloffProject.model.User;
import StoneKopeloffProject.service.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


/*
 * Purpose of this Dao is to send/retrieve info about a reimbursement
 * to/from the database. It then returns the composed Reimbursement Object.
 */
public class UserDao implements GenericDao<User> {
	private static final Logger LOGGER = Logger.getLogger(UserDao.class);

	private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	private User objectConstructor(ResultSet rs) throws SQLException {
		return new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getInt(7));
	}

	@Override
	public List<User> getList() {

		Session session = sessionFactory.openSession();
		session.getTransaction().begin();


		List<User> hqlResult = session.createQuery(
				"from users",
				User.class
		).list();

		session.getTransaction().commit();
		return hqlResult;
	}
	/*public List<User> getList() {
		List<User> l = new ArrayList<User>();
		
		try (Connection c = ConnectionUtil.getConn()) {
			String qSql = "SELECT * FROM ers_users";
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(qSql);
			
			while(rs.next()) {
				l.add(objectConstructor(rs));
			}
			LOGGER.debug("A list of users was retrieved from the database.");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			LOGGER.error("An attempt to get all users from the database failed.");
		}
		return l;
	}*/

	@Override
	public User getById(int id) {
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();

		String hql = "from users where id = " + Integer.toString(id);

		List<User> hqlResult = session.createQuery(
				hql,
				User.class
		).list();

		session.getTransaction().commit();
		return hqlResult.get(0);
		/*User u = null;
		
		try(Connection c = ConnectionUtil.getConn()) {
			String qSql = "SELECT * FROM ers_users WHERE ers_users_id = ?";
			PreparedStatement ps = c.prepareStatement(qSql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
				u = objectConstructor(rs);
			
			LOGGER.debug("Information about user ID " + id + " was retrieved from the database.");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			LOGGER.error("An attempt to get info about user ID " + id + " from the database failed.");
		}
		return u;*/
	}
	
	@Override
	public List<User> getByUserId(int id) {
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();

		String hql = "from users where id = " + id;

		List<User> hqlResult = session.createQuery(
				hql,
				User.class
		).list();

		session.getTransaction().commit();
		return hqlResult;
	}
	
	@Override
	public User getByUsername(String username) {
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();

		String hql = "from users where username = " + username;

		List<User> hqlResult = session.createQuery(
				hql,
				User.class
		).list();

		session.getTransaction().commit();
		return hqlResult.get(0);
		/*User u = null;
		
		try(Connection c = ConnectionUtil.getConn()) {
			String qSql = "SELECT * FROM ers_users WHERE ers_username = ?";
			PreparedStatement ps = c.prepareStatement(qSql);
			ps.setString(1, username.toLowerCase());
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				//System.out.println("User object was created!");
				u = objectConstructor(rs);
			}
			
			LOGGER.debug("Information about username " + username + " was retrieved from the database.");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			LOGGER.error("An attempt to get info about username " + username + " from the database failed.");
		}
		return u;*/
	}

	@Override
	public void insert(User t) {

		Session session = sessionFactory.openSession();
		session.getTransaction().begin();

		session.persist(t);

		session.getTransaction().commit();
		
	}

	@Override
	public void delete(User t) {
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();

		session.delete(t);

		session.getTransaction().commit();
		
	}
}
