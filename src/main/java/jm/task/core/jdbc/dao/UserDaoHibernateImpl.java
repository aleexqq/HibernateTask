package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private static final SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS user_schema.users (" +
                "id bigint NOT NULL AUTO_INCREMENT," +
                "name varchar(50)," +
                "lastName varchar(50)," +
                "age TINYINT," +
                "PRIMARY KEY (id))";

        Transaction transaction = null;

        try (Session sess = sessionFactory.openSession()) {
            transaction = sess.beginTransaction();
            sess.createSQLQuery(sql).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS user_schema.users";

        Transaction transaction = null;

        try (Session sess = sessionFactory.openSession()) {
            transaction = sess.beginTransaction();
            sess.createSQLQuery(sql).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO user_schema.users (name, lastName, age) VALUES (?, ?, ?)";

        Transaction transaction = null;

        try (Session sess = sessionFactory.openSession()) {
            transaction = sess.beginTransaction();
            sess.createSQLQuery(sql)
                    .setParameter(1, name)
                    .setParameter(2, lastName)
                    .setParameter(3, age)
                    .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        String sql = "DELETE FROM user_schema.users WHERE id = ?";

        Transaction transaction = null;

        try (Session sess = sessionFactory.openSession()) {
            transaction = sess.beginTransaction();
            sess.createSQLQuery(sql)
                    .setParameter(1, id)
                    .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM user_schema.users";

        List<User> list;
        try (Session sess = sessionFactory.openSession()) {
            list = (List<User>) sess.createSQLQuery(sql)
                    .addEntity(User.class)
                    .list();
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE user_schema.users";

        Transaction transaction = null;

        try (Session sess = sessionFactory.openSession()) {
            transaction = sess.beginTransaction();
            sess.createSQLQuery(sql).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
