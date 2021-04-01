package StoneKopeloffProject.dao;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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
public class ReimbursementDao implements GenericDao<Reimbursement> {
	private static final Logger LOGGER = Logger.getLogger(ReimbursementDao.class);

	private UserDao ud = new UserDao();
	private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	//private Reimbursement objectConstructor(ResultSet rs) throws SQLException {
//		return new Reimbursement(rs.getInt(1), rs.getFloat(2), rs.getTimestamp(3), rs.getTimestamp(4),
//				rs.getString(5), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10));
//	}

	/**
	 * Get a list of all reimbursements
	 * @return List
	 */
	@Override
	public List<Reimbursement> getList() {
		// start the transaction


		Session session = sessionFactory.openSession();
		session.getTransaction().begin();


		List<Reimbursement> hqlResult = session.createQuery(
				"from Reimbursement",
				Reimbursement.class
		).list();

		session.getTransaction().commit();
		return hqlResult;
	}


	/**
	 * Get a Reimbursement by its ID
	 * @param id
	 * @return Reimbursement
	 */

	@Override
	public Reimbursement getById(int id) {

		Session session = sessionFactory.openSession();
		session.getTransaction().begin();

		Reimbursement temp = session.find(Reimbursement.class, id);
		session.getTransaction().commit();

		return temp;

	}


	/**
	 *Method that goes through the table looking up all the records that have the authorID
	 * @param id
	 * @return List<Reimbursement>
	 */

	@Override
	public List<Reimbursement> getByUserId(int id) {



		Session session = sessionFactory.openSession();
		session.getTransaction().begin();

		String hql = "from Reimbursement where author = " + id;

		List<Reimbursement> hqlResult = session.createQuery(
				hql,
				Reimbursement.class
		).list();

		session.getTransaction().commit();
		return hqlResult;

	}

	public Reimbursement getByUsername(String username) {
		//Empty. Reason - No use.
		return null;
	}

	/**
	 * Insert a new Reimbursement
	 * @param r
	 */
	@Override
	public void insert(Reimbursement r) {

		//Inserting a reimbursement into the DB

		Session session = sessionFactory.openSession();
		session.getTransaction().begin();

		session.saveOrUpdate(r);
		session.flush();
		session.getTransaction().commit();


	}




	/**
	 * My thinking with this method is that the user will use find by Id or some search
	 * Then will edit the object and then can call this method to update
	 * @param r
	 * @return
	 */
	public void update(int id, int userIDPK, int newstatus){

		Session session = sessionFactory.openSession();
		session.getTransaction().begin();

//		session.close();
//
//		session = sessionFactory.openSession();
//		session.getTransaction().begin();
		Reimbursement r1 = session.get(Reimbursement.class ,id);
		r1.setResolver(session.get(User.class ,userIDPK));
		r1.setStatus_id(newstatus);
		r1.setResolved(Timestamp.from(Instant.now()));
		session.saveOrUpdate(r1);

//		String hql = "from Reimbursement where author = " + Integer.toString(id);
//
//		List<Reimbursement> hqlResult = session.createQuery(
//				hql,
//				Reimbursement.class
//		).list();

		session.flush();
		session.getTransaction().commit();

	}

	public void createReimbursement(Reimbursement r){

	}

	/**
	 * Delete a Reimbursement
	 * @param r
	 */
	@Override
	public void delete(Reimbursement r) {

		Session session = sessionFactory.openSession();
		session.getTransaction().begin();

		session.delete(r);

		session.getTransaction().commit();


	}

}