package database;
//Made by Lê Kỳ Anh
import Model.DonHangModel; //truy cập gói Model để lấy các thuộc tính từ donHangModel

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DonHang {
    //Phương thức thêm dữ liệu trong bảng
    public void insert(DonHangModel donHangModel) {
        String sql = "INSERT INTO DonHang (tenKhachHang,loaiSp,soLuong,thanhTien) VALUES(?,?,?,?)";
        try (Connection conn = Connect.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, donHangModel.getTenKhachHang());
            ps.setString(2, donHangModel.getLoaiSp());
            ps.setString(3, String.valueOf(donHangModel.getSoLuong()));
            ps.setString(4, String.valueOf(donHangModel.getThanhTien()));
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //Phương thức xóa dữ liệu khỏi bảng
    public void delete(int id) {
        String sql = "DELETE FROM DonHang WHERE id = ?";

        try (Connection conn = Connect.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    //Phương thức sửa dữ liệu
    public void update(DonHangModel donHangModel) {
        String sql = "UPDATE DonHang SET tenKhachHang = ?, loaiSp = ?, soLuong = ?, thanhTien = ? WHERE id = ?";

        try (Connection conn = Connect.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // set the corresponding param
            ps.setString(1, donHangModel.getTenKhachHang());
            ps.setString(2, donHangModel.getLoaiSp());
            ps.setString(3, String.valueOf(donHangModel.getSoLuong()));
            ps.setString(4, String.valueOf(donHangModel.getThanhTien()));
            ps.setInt(5, donHangModel.getID());
            // update
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    //Phương thức in ra từ cơ sở dữ liệu
    public List<DonHangModel> getAll(){
        String sql = "SELECT * FROM DonHang";
        try (Connection conn = Connect.connect();
             Statement st = conn.createStatement();
             ResultSet result = st.executeQuery(sql)) {
            ArrayList<DonHangModel> res = new ArrayList<>();
            while (result.next()){
                res.add(new DonHangModel(result.getInt("id"),
                        result.getString("tenKhachHang"),
                        result.getString("loaiSp"),
                        result.getInt("soLuong"),
                        result.getDouble("thanhTien")));
                //System.out.println(result.getString("tenKhachHang"));
            }
            return res;
        } catch (Exception ex) {
            System.out.println("ERROR" + ex.getMessage());
        }
        return null;
    }
    public List<DonHangModel> search(String key, String searchValue){
        String sql;
        switch (searchValue)
        {
            case "Loai san pham":
                sql = "SELECT * FROM DonHang WHERE loaiSp LIKE '" + key +"%' ";
                break;
            default:
                sql = "SELECT * FROM DonHang WHERE tenKhachHang LIKE '" + key +"%' ";
        }
        //String sql = "SELECT * FROM donHang WHERE tenKhachHang LIKE '" + key +"%' ";
        try (Connection conn = Connect.connect();
             Statement stmt = conn.createStatement();
             ResultSet result = stmt.executeQuery(sql)) {
            ArrayList<DonHangModel> res = new ArrayList<>();
            while (result.next()){
                res.add(new DonHangModel(result.getInt("id"),
                        result.getString("tenKhachHang"),
                        result.getString("loaiSp"),
                        result.getInt("soLuong"),
                        result.getDouble("thanhTien")));
                //System.out.println(result.getString("tenKhachHang"));
                //System.out.println(result.getInt("id")+" "+result.getString("tenKhachHang")+" "+result.getString("loaiSp")+" "+result.getString("tenSp")+" "+result.getString("soLuong")+" "+result.getInt("thoiGianDeXe")+" "+result.getInt("thanhTien")+" "+result.getInt("phiTrongXe"));
            }
            return res;
        } catch (Exception e) {
            System.out.println("ERROR" + e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        DonHang dh = new DonHang();
        DonHangModel donHangModel = new DonHangModel(6, "ABCD", "Cà phê", 4 , 35000);
        dh.delete(7);
        dh.getAll();
        //dh.search();
        //hd.getAll();
        //hd.search("Ky");
    }
}
