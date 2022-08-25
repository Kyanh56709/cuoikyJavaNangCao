package migrations;
/** Code to create table in the databases
 * Made by Kỳ Anh */

import database.Connect; //Import package to connect to the database

//Import SQL library
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {
    // Tạo bảng với công cụ SQL SERVER
    public void create_table(){
        String sql = "CREATE TABLE \"DonHang\" (\n" +
                "\t\"ID\"\tINTEGER,\n" +
                "\t\"tenKhachHang\"\tTEXT NOT NULL,\n" +
                "\t\"loaiSp\"\tTEXT NOT NULL,\n" +
                "\t\"soLuong\"\tTEXT NOT NULL,\n" +
                "\t\"thanhTien\"\tTEXT NOT NULL,\n" +
                "\tPRIMARY KEY(\"ID\")\n" +
                ")";
        try (Connection conn = Connect.connect();
             Statement stmt = conn.createStatement()) {
            // tạo bảng mới
            stmt.execute(sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        CreateTable tb = new CreateTable();
        tb.create_table();
    }
}
