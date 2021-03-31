package StoneKopeloffProject.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import StoneKopeloffProject.dao.ReimbursementDao;
import StoneKopeloffProject.model.Reimbursement;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;


public class ReimbursementService {
	private ReimbursementDao rd = new ReimbursementDao();
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

	public void createReimbursement (float amount, String description, int author, int type_id) {
		Timestamp ts = new Timestamp(LocalDate.now().toEpochDay());
		//TODO add reimbursement to DB
	}
	
	public List<Reimbursement> fetchAllReimbursements() {
		return rd.getList();
	}
	
	public List<Reimbursement> getReimbursementsByUserID(int id) {
		return rd.getByUserId(id);
	}
	
/*	public void updateReimbursements(int[][] i, int r) {
		rd.updateList(i, r);
	}*/
	public void updateReimbursement (int resolverid,int newstatus) {
		//TODO call DAO
	}
}
