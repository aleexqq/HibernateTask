package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {

    private final static String URL = "jdbc:mysql://localhost:3306/user_schema";
    private final static String USER = "root";
    private final static String PASSWORD = "ud5y3y";

    private static SessionFactory sessionFactory;

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration config = new Configuration();

                Properties props = new Properties();
                props.put(Environment.URL, URL);
                props.put(Environment.USER, USER);
                props.put(Environment.PASS, PASSWORD);

                config.setProperties(props);
                config.addAnnotatedClass(User.class);

                sessionFactory = config.buildSessionFactory();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}