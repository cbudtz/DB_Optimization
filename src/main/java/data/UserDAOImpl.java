package data;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//TODO Rename class
public class UserDAOImpl implements IUserDAO {
    //TODO Make a connection to the database
    private Connection createConnection() throws SQLException {
        return  DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/db02327?"
                + "user=db02327&password=db02327");
    }

    @Override
    public IUserDTO getUser(int userId) throws DALException {
        try (Connection connection = createConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE userid = " + userId);
            if (resultSet.next()) {
                return makeUserFromResultSet(resultSet);
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DALException(e.getMessage());
        }

    }

    private IUserDTO makeUserFromResultSet(ResultSet resultSet) throws SQLException {
        IUserDTO user = new UserDTO();
        user.setUserName(resultSet.getString("userName"));
        user.setIni(resultSet.getString("ini"));
        user.setUserId(resultSet.getInt("userId"));
        List<String> roles = Arrays.asList(resultSet.getString("roles").split(";"));
        user.setRoles(roles);
        return user;
    }

    @Override
    public IUserDTO getUserByIni(String initials) throws DALException {
        try (Connection connection = createConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE ini LIKE '" + initials + "'");
            UserDTO user = new UserDTO();
            if(resultSet.next()) {
                user.setUserName(resultSet.getString("userName"));
                user.setIni(resultSet.getString("ini"));
                List<String> roles = Arrays.asList(resultSet.getString("Roles").split(";"));
                user.setRoles(roles);
            }
            return user;
        } catch (SQLException e) {
            throw new DALException(e.getMessage());
        }
    }


    @Override
    public List<IUserDTO> getUserList() throws DALException {
        try (Connection connection = createConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            List<IUserDTO> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(makeUserFromResultSet(resultSet));
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DALException(e.getMessage());
        }
    }

    @Override
    public void createUser(IUserDTO user) throws DALException {
        // Implement this if you like - Should insert a user into the db using data from UserDTO object.
    }

    @Override
    public void updateUser(IUserDTO user) throws DALException {
        //Implement this if you like - Should update a user in the db using data from UserDTO object.
    }

    @Override
    public void deleteUser(int userId) throws DALException {
        // Implement this if you like - Should insert a user into the db using data from UserDTO object.
        //Note db02327 user can't delete users from db02327 db
    }
}
