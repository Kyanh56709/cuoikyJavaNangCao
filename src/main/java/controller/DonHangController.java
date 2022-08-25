
package controller;

import Util.Alert;
import database.DonHang; //Import từ database

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXDialog;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyTableView;/** Thư viện ngoài */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import Model.DonHangModel; //Lấy thuộc tính

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;


public class DonHangController implements Initializable{
    // các trường ứng với mỗi cột trong bảng
    @FXML
    private MFXLegacyTableView<DonHangModel> table;

    @FXML
    private TableColumn<DonHangModel , Integer> idCol;

    @FXML
    private TableColumn<DonHangModel , String> tenKhachHangCol;

    @FXML
    private TableColumn<DonHangModel , String> loaiSpCol;

    @FXML
    private TableColumn<DonHangModel , Integer> soLuongCol;

    @FXML
    private TableColumn<DonHangModel , Double> thanhTienCol;

    @FXML
    private ChoiceBox<String> timKiemBox;

    @FXML
    private AnchorPane root;

    @FXML
    private TextField searchTextField;


    ObservableList<DonHangModel> DonHangList; //ObservableList để
    DonHang database = new DonHang();

    //Hàm mở một cửa sổ mới
    public static void loadWindow(URL location, String title) {
        try {
            Parent parent = FXMLLoader.load(location);
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Thêm dữ liệu vào bảng, mở cửa sổ Thêm đơn hàng
    @FXML
    private void add(ActionEvent event){
        loadWindow(getClass().getResource("/themDonHang.fxml"), "Thêm đơn hàng");
        refreshTable();
    }
    //Xóa khỏi bảng
    @FXML
    private void delete(ActionEvent event){
        DonHangModel chon = table.getSelectionModel().getSelectedItem();
        if (chon != null){
            database.delete(chon.getID());
            refreshTable();
            Alert.showDialog(Alert.DialogType.SUCCESS,"Thành công", Arrays.asList(new MFXButton("Đóng")));
        }
    }
    //Chỉnh sửa
    @FXML
    private void edit(ActionEvent event){
        DonHangModel chon = table.getSelectionModel().getSelectedItem();
        if (chon != null) { // Nếu 1 hàng được chọn
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/themDonHang.fxml"));// Mở cửa sổ thêm đơn hàng
            try {
                Parent parent = loader.load();
                Stage stage = new Stage();
                stage.setTitle("Chỉnh sửa");
                stage.setScene(new Scene(parent));
                ThemDonHangController controller = loader.getController(); // Sử dụng controller ThemDonHang
                controller.change(chon);
                stage.showAndWait();
                System.out.println("out");
                refreshTable();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void refreshTable() { //Làm mới lại bảng sau khi thêm sửa hoặc xóa
        DonHangList.removeAll(DonHangList);
        DonHangList.addAll(database.getAll());
    }
    //Tìm kiếm
    @FXML
    private void search() {
        FilteredList<DonHangModel> DonHang = new FilteredList(DonHangList, p -> true);
        table.setItems(DonHang);// sử dụng bộ lọc danh sách Filterlist  lọc dữ liệu từ bảng để tìm kiếm theo timKiemBox
        searchTextField.setPromptText("Hộp tìm kiếm...");
        searchTextField.textProperty().addListener((obs, oldValue, newValue) -> {
            switch (timKiemBox.getValue())//Loc bang theo timKiemBox
            {
                case "Họ tên khách hàng":
                    DonHang.setPredicate(p -> p.getTenKhachHang().toLowerCase().contains(newValue.toLowerCase().trim()));
                    break;
                case "Loại sản phẩm":
                    DonHang.setPredicate(p -> p.getLoaiSp().toLowerCase().contains(newValue.toLowerCase().trim()));
                    break;
            }
        });
    }

    // Hiển thị dữ liệu ứng với mỗi cột ra màn hình
    private void populateTable(){
        DonHangList = FXCollections.observableList(database.getAll());
        idCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
        tenKhachHangCol.setCellValueFactory(new PropertyValueFactory<>("tenKhachHang"));
        loaiSpCol.setCellValueFactory(new PropertyValueFactory<>("loaiSp"));
        soLuongCol.setCellValueFactory(new PropertyValueFactory<>("soLuong"));
        thanhTienCol.setCellValueFactory(new PropertyValueFactory<>("thanhTien"));
        table.setItems(DonHangList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MFXDialog dialog = Alert.getInstance();
        root.getChildren().add(dialog);
        database = new DonHang();
        populateTable();
        search();//khoi chay ham search

        //timKiemBox
        ObservableList<String> list = FXCollections.observableArrayList("Họ tên khách hàng", "Loại sản phẩm");
        timKiemBox.setItems(list);
        timKiemBox.setValue("Họ tên khách hàng");

    }
}
