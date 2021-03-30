package StoneKopeloffProject.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionUtil {


    static String jdbcUrl = "jdbc:h2:tcp://localhost/~/test";
    //    static String jdbcUrl = "jdbc:h2:~/test-user=sa" jdbc:h2:mem:tcp://localhost/`/test;
    static Connection conn = null;
    public static Statement getInstance() throws SQLException {





            if (conn == null) {
                try {
                    Class.forName("org.h2.Driver");
                } catch (ClassNotFoundException e) {
                    System.out.println("Could not register driver!");
                    e.printStackTrace();
                }
//            conn = DriverManager.getConnection("jdbc:h2:"+"./Database/my","sa", "");
                conn = DriverManager.getConnection(jdbcUrl,"sa" ,"");
            }


            //If connection was closed then retrieve a new connection
            if (conn.isClosed()) {
                System.out.println("Opening new connection...");
                conn = DriverManager.getConnection(jdbcUrl,"sa" ,"");
            }
            return (Statement) conn;
        }


}
