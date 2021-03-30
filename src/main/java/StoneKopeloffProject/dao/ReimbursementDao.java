package StoneKopeloffProject.dao;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import StoneKopeloffProject.model.Reimbursement;
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

	private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	private Reimbursement objectConstructor(ResultSet rs) throws SQLException {
		return new Reimbursement(rs.getInt(1), rs.getFloat(2), rs.getTimestamp(3), rs.getTimestamp(4),
							rs.getString(5), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10));
	}

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
//		List<Reimbursement> l = new ArrayList<Reimbursement>();
//
//		try (Connection c = ConnectionUtil.getConn()) {
//			String qSql = "SELECT * FROM ers_reimbursement";
//			Statement s = c.createStatement();
//			ResultSet rs = s.executeQuery(qSql);
//
//			while(rs.next()) {
//				l.add(objectConstructor(rs));
//			}
//
//			rs.close();
//			s.closeOnCompletion();
//			LOGGER.debug("All reimbursements were retrieved from the database.");
//		} catch (SQLException | ClassNotFoundException e) {
//			e.printStackTrace();
//			LOGGER.error("An attempt to get all reimbursements failed.");
//		}
//		return l;


	@Override
	public Reimbursement getById(int id) {

		Session session = sessionFactory.openSession();
		session.getTransaction().begin();

		Reimbursement temp = session.find(Reimbursement.class, id);
		session.getTransaction().commit();

		return temp;

	}


//		Reimbursement r = null;
//
//		try(Connection c = ConnectionUtil.getConn()) {
//			String qSql = "SELECT * FROM ers_reimbursement WHERE reimb_id = ?";
//			PreparedStatement ps = c.prepareStatement(qSql);
//			ps.setInt(1, id);
//			ResultSet rs = ps.executeQuery();
//
//			if(rs.next())
//				r = objectConstructor(rs);
//
//			rs.close();
//			ps.closeOnCompletion();
//			LOGGER.debug("A reimbursement by ID " + id + " was retrieved from the database.");
//		} catch (SQLException | ClassNotFoundException e) {
//			e.printStackTrace();
//			LOGGER.error("An attempt to get a reimbursement by ID" + id + " from the database failed.");
//		}
//		return r;

	/**
	 *Method that goes through the table looking up all the records that have the authorID
	 * @param id
	 * @return
	 */

	@Override
	public List<Reimbursement> getByUserId(int id) {



		Session session = sessionFactory.openSession();
		session.getTransaction().begin();

		String hql = "from Reimbursement where author = " + Integer.toString(id);

		List<Reimbursement> hqlResult = session.createQuery(
				hql,
				Reimbursement.class
		).list();

		session.getTransaction().commit();
		return hqlResult;


//
//		List<Reimbursement> l = new ArrayList<Reimbursement>();
//
//		try(Connection c = ConnectionUtil.getConn()) {
//			String qSql = "SELECT * FROM ers_reimbursement WHERE reimb_author = ?";
//			PreparedStatement ps = c.prepareStatement(qSql);
//			ps.setInt(1, id);
//			ResultSet rs = ps.executeQuery();
//
//			while(rs.next()) {
//				l.add(objectConstructor(rs));
//			}
//			rs.close();
//			ps.closeOnCompletion();
//			LOGGER.debug("A list of reimbursements made by user ID " + id + " was retrieved from the database.");
//		} catch (SQLException | ClassNotFoundException e) {
//			e.printStackTrace();
//			LOGGER.error("An attempt to get all reimbursements made by user ID " + id + " fron the database failed.");
//		}
//		System.out.println(l.toString());
//		return l;
	}
	
	public Reimbursement getByUsername(String username) {
		//Empty. Reason - No use.
		return null;
	}

	@Override
	public void insert(Reimbursement r) {

		//Inserting a reimbursement into the DB

		Session session = sessionFactory.openSession();
		session.getTransaction().begin();

		session.persist(r);

		session.getTransaction().commit();


//		try(Connection c = ConnectionUtil.getConn()) {
//			String sql = "INSERT INTO ers_reimbursement(reimb_amount, reimb_submitted, reimb_description, "
//					   + "reimb_author, reimb_status_id, reimb_type_id) VALUES(?, ?, ?, ?, ?, ?)";
//			PreparedStatement ps = c.prepareStatement(sql);
//			ps.setFloat(1, r.getAmount());
//			Calendar cal = Calendar.getInstance();
//			ps.setTimestamp(2, new Timestamp(cal.getTime().getTime()));
//			ps.setString(3, r.getDescription());
//			ps.setInt(4, r.getAuthor());
//			ps.setInt(5, r.getStatus_id());
//			ps.setInt(6, r.getType_id());
//
//			ps.executeUpdate();
//			ps.closeOnCompletion();
//			LOGGER.debug("A new reimbursement was successfully added to the database.");
//		} catch (SQLException | ClassNotFoundException e) {
//			e.printStackTrace();
//			LOGGER.error("An attempt to insert a reimbursement to the database failed.");
//		}
	}
	
	public void updateList(int[][] i, int resolver) {
		try(Connection c = ConnectionUtil.getConn()) {
			String aSql = "SELECT acceptarray(?, ?)";
			String dSql = "SELECT denyarray(?, ?)";
			
			//Convert both of our int arrays to an Integer object
			Integer[] a = Arrays.stream(i[0]).boxed().toArray(Integer[]::new);
			Integer[] d = Arrays.stream(i[1]).boxed().toArray(Integer[]::new);
			
			//Convert both of our Integer arrays into something useful for SQL.
			Array aArray = c.createArrayOf("INTEGER", a);
			Array dArray = c.createArrayOf("INTEGER", d);
			
			//Perform our SQL calls
			CallableStatement cs = c.prepareCall(aSql);
			cs.setArray(1, aArray);
			cs.setInt(2, resolver);
			cs.execute();
			cs.closeOnCompletion();
			
			cs = c.prepareCall(dSql);
			cs.setArray(1, dArray);
			cs.setInt(2, resolver);
			cs.execute();
			cs.closeOnCompletion();
			
			//This section is just for the sake of logging.
			int totalCount = 0;
			for(int co = 0; co < a.length; co++) {
				if (a[co] != -1) {
					totalCount++;
				}
				if (d[co] != -1) {
					totalCount++;
				}
			}
			LOGGER.debug(totalCount + " reimbursement" + ((totalCount != 1) ? "s" : "") + " modified by user ID " + resolver + ".");
		} catch (SQLException | ClassNotFoundException e) {
			LOGGER.error("An attempt to accept/deny reimbursements by user ID " + resolver + " from the database failed.");
			e.printStackTrace();
		}
	}
	
	@Override
	public void delete(Reimbursement r) {

		Session session = sessionFactory.openSession();
		session.getTransaction().begin();

		session.delete(r);

		session.getTransaction().commit();


	}
	
}
