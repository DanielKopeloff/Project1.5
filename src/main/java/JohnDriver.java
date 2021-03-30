import StoneKopeloffProject.model.Reimbursement;
import StoneKopeloffProject.model.User;
import StoneKopeloffProject.service.ConnectionUtil;
import StoneKopeloffProject.service.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JohnDriver {


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

        User u1=new User();
        //u1.setUser_id(101);
        u1.setUsername("user");
        u1.setPassword("pass");
        u1.setFirstname("Bob");
        u1.setLastname("Smith");
        u1.setEmail("bob_smith@mail.com");
        u1.setRole_id(0);


        session.persist(u1);
        session.getTransaction().commit();


    }
}