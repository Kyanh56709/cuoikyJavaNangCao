package Model;

import javafx.beans.property.*; //Import thư viện của javafx

public class DonHangModel{
    //Các thuộc tính của lớp DonHangModel
    private final SimpleIntegerProperty ID; //SimpleInteger là kiểu khai báo của javafx
    private final SimpleStringProperty tenKhachHang;
    private final SimpleStringProperty loaiSp;
    private final SimpleIntegerProperty soLuong;
    private final SimpleDoubleProperty thanhTien;
    
//Constructor DonHangModel được khởi tạo theo kiểu của javaFX
    public DonHangModel(int ID, String tenKhachHang, String loaiSp, int soLuong, double thanhTien) {
        this.ID = new SimpleIntegerProperty(ID);
        this.tenKhachHang = new SimpleStringProperty(tenKhachHang);
        this.loaiSp = new SimpleStringProperty(loaiSp);
        this.soLuong = new SimpleIntegerProperty(soLuong);
        this.thanhTien = new SimpleDoubleProperty(thanhTien);
    }

    @Override
    public String toString() {
        return "ID =" + ID + ", tenKhachHang =" + tenKhachHang + '}';
    }
    //Các phương thức getter và setter khởi tạo cho mỗi thuộc tính
    public int getID() {
        return ID.get();
    }
    public void setID(int ID){
        this.ID.set(ID);
    }

    public String getTenKhachHang() {
        return tenKhachHang.get();
    }
    public void setTenKhachHang(String tenKhachHang){
        this.tenKhachHang.set(tenKhachHang);
    }

    public String getLoaiSp() {
        return loaiSp.get();
    }
    public void setLoaiSp(String loaiSp){
        this.loaiSp.set(loaiSp);
    }
//    public String getTenSp() {
//        return tenSp.get();
//    }
//    public void setTenSp(String tenSp){
//        this.tenSp.set(tenSp);
//    }

    public int getSoLuong() {
        return soLuong.get();
    }
    public void setSoLuong(int soLuong){
        this.soLuong.set(soLuong);
    }

    public double getThanhTien() {
        return thanhTien.get();
    }
    public void setThanhTien(double thanhTien){
        this.thanhTien.set(thanhTien);
    }

    public SimpleIntegerProperty IDProperty() {
        return ID;
    }

    public SimpleStringProperty tenKhachHangProperty() {
        return tenKhachHang;
    }


    public SimpleIntegerProperty soLuongProperty() {
        return soLuong;
    }

    public SimpleDoubleProperty thanhTienProperty() {
        return thanhTien;
    }

}
