package controller;

import Util.Alert;
import database.DonHang; //import database

//import external library
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;

//import javafx library
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import Model.DonHangModel;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class ThemDonHangController implements Initializable{

    // item id
    @FXML
    private MFXTextField idTextField;

    @FXML
    private MFXTextField tenKhachHangTextField;

    @FXML
    private ChoiceBox<String> loaiSpBox;


    @FXML
    private MFXTextField soLuongTextField;


    @FXML
    private MFXButton closeButton;

    DonHang DonHangdtbs;

    boolean isEditing; //=false;

    @FXML
    private void add(ActionEvent event){
        int ID;
        try {
            ID = Integer.parseInt(idTextField.getText());
        } catch (NumberFormatException e){
            ID = 0;
        }
        int soLuong;
        try {
            soLuong = Integer.parseInt(soLuongTextField.getText());
        } catch (NumberFormatException e){
            soLuong = 0;
        }
        DonHangModel DonHangModel = new DonHangModel(ID,
                tenKhachHangTextField.getText().strip(), //Loại bỏ khoảng trắng ở 2 đầu
                getChoice(loaiSpBox),
                soLuong,
                Math.floor(tinhTienSp(loaiSpBox.getValue(), soLuong)));
        if (isEditing) {
            DonHangdtbs.update(DonHangModel);
        } else {
            DonHangdtbs.insert(DonHangModel);
        }
        Alert.showDialog(Alert.DialogType.SUCCESS,"Thành công", Arrays.asList(new MFXButton("Đóng")));
        closeWindow();
    }
    private double tinhTienSp(String loaiSp, int soLuong){
        if (loaiSp.equals("Cà phê")) return soLuong*20000;
        if (loaiSp.equals("Đồ uống")) return soLuong*30000;
        else return soLuong*50000;
    }

    private String getChoice(ChoiceBox<String> choiceBox){
        String loaiSp = choiceBox.getValue();
        return loaiSp;
    }

    //Close window
    private void closeWindow() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    @FXML //Close button
    private void close(ActionEvent event) {
        closeWindow();
    }

    //Chỉnh sửa
    public void change(DonHangModel DonHangModel){
        idTextField.setText(String.valueOf(DonHangModel.getID()));
        tenKhachHangTextField.setText(DonHangModel.getTenKhachHang());
        loaiSpBox.setValue(DonHangModel.getLoaiSp());
        soLuongTextField.setText(String.valueOf(DonHangModel.getSoLuong()));
        tinhTienSp(loaiSpBox.getValue(), DonHangModel.getSoLuong());
        isEditing = true;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        ObservableList<String> list = FXCollections.observableArrayList("Cà phê", "Đồ uống", "Bánh ngọt");
        loaiSpBox.setItems(list); //Chọn 1 trong các loại sản phẩm
        loaiSpBox.setValue("Cà phê");
        DonHangdtbs = new DonHang();//khởi tạo đối tượng mới
    }

}
