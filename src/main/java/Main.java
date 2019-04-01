import data.IUserDAO;
import data.UserDAOImpl;

import java.sql.SQLException;

public class Main {
    static IUserDAO userDAO;

    static {
        try {
            userDAO = new UserDAOImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IUserDAO.DALException {
        long start = System.currentTimeMillis();
        userDAO.getUser(1);
        userDAO.getUser(1);
        userDAO.getUser(1);
        long end = System.currentTimeMillis();

        System.out.println("Time Elapsed: " + (double)(end-start)/1000 + "seconds");


    }
}
