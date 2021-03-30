package StoneKopeloffProject.service;

import org.hibernate.Session;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ConnectionUtil {

    //    jdbc:driver://hostname:port/dbName?user=userName&password=password
    static String jdbcUrl = "jdbc:postgresql://project1-5.cbo6usfmqg0y.us-east-2.rds.amazonaws.com:5432/Project1-5?user=postgres&password=Project1.5";
    //    static String jdbcUrl = "jdbc:h2:~/test-user=sa" jdbc:h2:mem:tcp://localhost/`/test;
    static Connection conn = null;

    public static Session getInstance() throws SQLException {
        return HibernateUtil.getSessionFactory().getCurrentSession();

    }

//
public static Connection getConn() throws SQLException, ClassNotFoundException {



return createConnection();
}


    public static Connection createConnection() throws SQLException, ClassNotFoundException {
        String url = "jdbc:postgresql://localhost:5432/CarDealerShip";
        String user = "postgres";
        String password ="root";
        String db = "org.postgresql.Driver";
        Class.forName(db);
        return DriverManager.getConnection(url, user, password);

    }
}