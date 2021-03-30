import StoneKopeloffProject.model.Reimbursement;
import StoneKopeloffProject.model.User;
import StoneKopeloffProject.service.ConnectionUtil;
import StoneKopeloffProject.service.HibernateUtil;
import StoneKopeloffProject.service.ReimbursementService;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

public class Driver {


    public static void main(String[] args) {

//        Connection c ;
//
//        try {
//             c=  ConnectionUtil.getConn();
//            PreparedStatement pr = c.prepareStatement("Create Table Computer (id int)");
//             pr.executeUpdate();
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }


//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//
//        Transaction tx = session.getTransaction();
//
//        tx.begin();


//        session.persist(new Reimbursement(2300f, Timestamp.from(Instant.now()),Timestamp.from(Instant.now()),"Just a basic thing " ,10 ,10 ,5,3));
//        session.persist(new Reimbursement(2345300f, Timestamp.from(Instant.now()),Timestamp.from(Instant.now()),"Testing " ,10 ,10 ,5,3));
//        session.getTransaction().commit();

        ReimbursementService reimbursementService = new ReimbursementService();
        List<Reimbursement> reimbursementList = reimbursementService.fetchAllReimbursements();
        for(Reimbursement reimbursement : reimbursementList){
            System.out.println(reimbursement);
        }

        System.out.println(reimbursementService.getReimbursementsByUserID(10));


    }
}
