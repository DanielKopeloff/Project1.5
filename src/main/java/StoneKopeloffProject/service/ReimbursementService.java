package StoneKopeloffProject.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import StoneKopeloffProject.dao.ReimbursementDao;
import StoneKopeloffProject.model.Reimbursement;
import StoneKopeloffProject.model.User;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;


public class ReimbursementService {
	private ReimbursementDao rd;
	private static final Logger LOGGER = Logger.getLogger(ReimbursementService.class);
	
	public ReimbursementService() {
		rd = new ReimbursementDao();
	}
	
	public void createReimbursement(String json) {
		try {
			Reimbursement r = new ObjectMapper().readValue(json, Reimbursement.class);
			LOGGER.debug("JSON from the client was successfully parsed.");
			rd.insert(r);
		} catch (Exception e) {
			LOGGER.error("Something occurred during JSON parsing for a new reimbursement. Is the JSON malformed?");
			e.printStackTrace();
		}
	}
	
	public List<Reimbursement> fetchAllReimbursements() {
		return rd.getList();
	}
	
	public List<Reimbursement> getReimbursementsByUserID(int id) {
		return rd.getByUserId(id);
	}

	public void  addReimbursement(Reimbursement r){rd.insert(r);return;}


	/**
	 * This wont work becuase the r we get doesnt have the id field yet
	 *
	 * @param r
	 * @param resolver
	 * @param decision
	 */
	@Deprecated
	public void updateReimbursement(Reimbursement r , User resolver , Reimbursement.Status decision){
		r.setResolver(resolver);
		r.setType_id(decision.ordinal());
		r.setResolved(Timestamp.from(Instant.now()));


		rd.update(rd.getById(r.getId()));
	}

	public void updateReimbursement(int id, User resolver , Reimbursement.Status decision){

		Reimbursement temp = rd.getById(id);
		temp.setResolver(resolver);
		temp.setType_id(decision.ordinal());
		temp.setResolved(Timestamp.from(Instant.now()));

		rd.update(temp);
	}
	
//	public void updateReimbursements(int[][] i, int r) {
//		rd.updateList(i, r);
//	}
}
