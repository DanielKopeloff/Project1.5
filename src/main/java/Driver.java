import StoneKopeloffProject.model.Reimbursement;
import StoneKopeloffProject.model.User;
import StoneKopeloffProject.service.ConnectionUtil;
import StoneKopeloffProject.service.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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


        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        Transaction tx = session.getTransaction();

        tx.begin();


        session.persist(new Reimbursement());
        session.getTransaction().commit();


    }
}
