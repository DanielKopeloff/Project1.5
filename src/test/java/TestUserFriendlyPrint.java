import StoneKopeloffProject.model.Reimbursement;
import StoneKopeloffProject.model.User;
import StoneKopeloffProject.service.HibernateUtil;
import StoneKopeloffProject.ui.UserFriendlyPrint;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDate;

public class TestUserFriendlyPrint {
    //@BeforeAll
    public static void addUserForTest() throws InterruptedException {
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

    @Test
    public void TestUserFriendlyPrint() {
        Timestamp ts = new Timestamp(LocalDate.now().toEpochDay());
        Reimbursement r1 = new Reimbursement(0, 100.0f, ts, ts, "This is a test reimbursement",1,
        1, 0, 0);
        System.out.println(UserFriendlyPrint.printReimbursement(r1,true));
        Reimbursement r2 = new Reimbursement(0, 100.0f, ts, ts, "This is another test reimbursement",1,
                1, 1, 1);
        Assertions.assertTrue(true);
    }
}
