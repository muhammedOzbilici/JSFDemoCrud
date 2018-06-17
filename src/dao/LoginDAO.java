package dao;

import util.DataConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

    public static boolean validate(String username , String password){
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DataConnect.getConnection();
            preparedStatement = connection.prepareStatement("Select username,password from user " +
                    "where username = ? and password = ?");

            preparedStatement.setString(1 , username);
            preparedStatement.setString(2,password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Login error " +e.getMessage());
            return false;
        }

        finally {
            DataConnect.close(connection);
        }

        return false;
    }
}
