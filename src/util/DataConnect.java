package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataConnect {

    public static Connection getConnection(){

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo",
            "root" , "root");

            return connection;
        }
        catch (Exception e){
            System.out.println("Database connection error " + e.getMessage());
            return null;
        }
    }

    public static void close(Connection connection){
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
