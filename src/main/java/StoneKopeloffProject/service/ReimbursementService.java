package StoneKopeloffProject.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

import StoneKopeloffProject.dao.ReimbursementDao;
import StoneKopeloffProject.dao.UserDao;
import StoneKopeloffProject.model.Reimbursement;
import StoneKopeloffProject.model.User;
import org.apache.log4j.Logger;

public class ReimbursementService {
	private ReimbursementDao rd = new ReimbursementDao();
	private UserDao ud = new UserDao();
	private static final Logger LOGGER = Logger.getLogger(ReimbursementService.class);

	private static ReimbursementService instance;

	private ReimbursementService() {

	}

	synchronized public static ReimbursementService getInstance() {
		if (instance == null) {
			instance = new ReimbursementService();
		} return instance;
	}

/*	public void createReimbursement(String json) {
		try {
			Reimbursement r = new ObjectMapper().readValue(json, Reimbursement.class);
			LOGGER.debug("JSON from the client was successfully parsed.");
			rd.insert(r);
		} catch (Exception e) {
			LOGGER.error("Something occurred during JSON parsing for a new reimbursement. Is the JSON malformed?");
			e.printStackTrace();
		}
	}*/

	public void createReimbursement (float amount, String description, int author, Reimbursement.expenseType type_id) {
		Timestamp ts = new Timestamp(LocalDate.now().toEpochDay());
		Reimbursement r = new Reimbursement(amount ,description,ud.getById(author) , type_id);
		rd.insert(r);
	}
	
	public List<Reimbursement> fetchAllReimbursements() {
		return rd.getList();
	}
	
	public List<Reimbursement> getReimbursementsByUserID(int id) {
		return rd.getByUserId(id);
	}


//	public void updateReimbursement(int id, User resolver , Reimbursement.Status decision){
//
//		Reimbursement temp = rd.getById(id);
//		temp.setResolver(resolver);
//		temp.setType_id(decision.ordinal());
//		temp.setResolved(Timestamp.from(Instant.now()));
//
//		rd.update(temp);
//	}

	public void updateReimbursement(int id, int userIDPK, int newstatus) {
		rd.update(id ,userIDPK ,newstatus);
	}

//	public void updateReimbursements(int[][] i, int r) {
//		rd.updateList(i, r);
//	}
}
