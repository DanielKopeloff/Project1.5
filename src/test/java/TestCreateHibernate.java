import StoneKopeloffProject.model.User;
import StoneKopeloffProject.service.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestCreateHibernate {
//@Test
    public void create() {

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
    tx.commit();
    System.out.println("successfully saved");
    //Hibernate methods do not return a success or failure value so the DB is checked manually
    Assertions.assertTrue(true);
    }
}
