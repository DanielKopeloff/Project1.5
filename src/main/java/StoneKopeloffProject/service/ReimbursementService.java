package StoneKopeloffProject.service;


import java.util.List;

import StoneKopeloffProject.dao.ReimbursementDao;
import StoneKopeloffProject.dao.UserDao;
import StoneKopeloffProject.model.Reimbursement;
import org.apache.log4j.Logger;

public class ReimbursementService {
	private ReimbursementDao rd = new ReimbursementDao();
	private UserDao ud = new UserDao();
	private static final Logger LOGGER = Logger.getLogger(ReimbursementService.class);

	private static ReimbursementService instance;

	private ReimbursementService() { }

	synchronized public static ReimbursementService getInstance() {
		if (instance == null) {
			instance = new ReimbursementService();
		} return instance;
	}


	public void createReimbursement (float amount, String description, int author, Reimbursement.expenseType type_id) {
		Reimbursement r = new Reimbursement(amount ,description,ud.getById(author) , type_id);
		rd.insert(r);
	}
	
	public List<Reimbursement> fetchAllReimbursements() {
		return rd.getList();
	}
	
	public List<Reimbursement> getReimbursementsByUserID(int id) {
		return rd.getByUserId(id);
	}

	public Reimbursement getbyReimbursementID(int id){return rd.getByReimbursementID(id);}

	public void updateReimbursement(int id, int userIDPK, int newstatus) {
		rd.update(id ,userIDPK ,newstatus);
	}

	public void updateReimbursement(Reimbursement r){
		rd.update(r);
	}

	public List<Reimbursement> getReimbursementsByStatus(int id, int uid){return rd.getReimbursementByStatus(id ,uid);}


}
