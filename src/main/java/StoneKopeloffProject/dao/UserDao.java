package StoneKopeloffProject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import StoneKopeloffProject.model.Reimbursement;
import StoneKopeloffProject.model.User;
import StoneKopeloffProject.service.ConnectionUtil;
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
                "from user",
                User.class
        ).list();

        session.getTransaction().commit();
        return hqlResult;

    }

    @Override
    public User getById(int id) {
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();

		String hql = "from User where Id = " + Integer.toString(id);

		return session.createQuery(
				hql,
				User.class
		).getSingleResult();
	}

    @Override
    public List<User> getByUserId(int id) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        String hql = "from User where userId = " + id;

        List<User> hqlResult = session.createQuery(
                hql,
                User.class
        ).list();

        return hqlResult;
    }

    @Override
    public User getByUsername(String username) {

        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        String hql = "from User where username = " + username;

        return session.createQuery(
                hql,
                User.class
        ).getSingleResult();

    }

    @Override
    public void insert(User t) {

        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        session.saveOrUpdate(t);

        session.getTransaction().commit();

    }

    @Override
    public void delete(User t) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        session.remove(t);
        session.flush();

        session.getTransaction().commit();


    }
}
