package StoneKopeloffProject.dao;

import StoneKopeloffProject.model.User;
import StoneKopeloffProject.service.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


/**
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
    public List<User> getByUserId(int id) {
        return null;
    }

    @Override
    public User getByUsername(String username) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        String hql = "from User where username = :username";
        List<User> hqlResult = session.createQuery(
                hql,
                User.class
        ).setParameter("username", username).list();
        session.getTransaction().commit();
        if (hqlResult.isEmpty()) {
            return null;
        } else {
            return hqlResult.get(0);
        }

    }

    @Override
    public void insert(User user) {

        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        session.persist(user);

        session.getTransaction().commit();

    }

    @Override
    public void delete(User user) {

    }

    @Override
    public List<User> getList() {

        Session session = sessionFactory.openSession();
        session.getTransaction().begin();


        List<User> hqlResult = session.createQuery(
                "from User",
                User.class
        ).list();

        session.getTransaction().commit();
        return hqlResult;
    }


    @Override
    public User getById(int id) {
        Session session = sessionFactory.openSession();
//		session.getTransaction().begin();

        String hql = "from User where id = " + (id);


        return session.createQuery(
                hql,
                User.class
        ).getSingleResult();


    }

    public void update(User u) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        session.saveOrUpdate(u);

        session.flush();
        session.getTransaction().commit();


    }

}
