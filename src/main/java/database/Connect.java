package database;
//Made by Lê Kỳ Anh

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Connect {
    private static Connection conn;
    // Kết nối cơ sở dữ liệu
    public static Connection connect() {
        String url = "jdbc:sqlite:management.db";
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}