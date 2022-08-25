package migrations;
//Made by Lê Kỳ Anh
import database.Connect; // Kết nối

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

public class CreateDB {
    // Tạo cơ sở dữ liệu
    public void createNewDatabase() {
        try (Connection conn = Connect.connect()) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        CreateDB db = new CreateDB();
        db.createNewDatabase();
    }
}
