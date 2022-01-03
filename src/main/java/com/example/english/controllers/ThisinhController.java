package com.example.english.controllers;

import com.example.english.dao.*;
import com.example.english.models.phongthi;
import com.example.english.models.thisinh;
import com.example.english.models.thisinh_phongthi;
import com.example.english.utils.HibernateUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ThisinhController implements Initializable {
    @FXML
    private TableView<thisinh> tablethisinh;

    @FXML
    private TableColumn<thisinh, String> id, hoten, gioitinh, ngaysinh, noisinh, cmnd, ngaycap, noicap, sdt, email;

    @FXML
    private ObservableList<thisinh> listthisinh;

    @FXML
    private Button insert_btn, delete_btn, edit_btn, refresh_btn;

    private static SessionFactory factory;

    private phongthi phongthi_send;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        factory = HibernateUtil.getSessionFactory();
        id.setCellValueFactory(data -> new SimpleStringProperty(Integer.toString(data.getValue().getId())));
        hoten.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getHoten()));
        gioitinh.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getGioitinh()));
        ngaysinh.setCellValueFactory(data -> new SimpleStringProperty(khoathiDAO.DateFormat(data.getValue().getNgaysinh())));
        noisinh.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNoisinh()));
        cmnd.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCmnd()));
        ngaycap.setCellValueFactory(data -> new SimpleStringProperty(khoathiDAO.DateFormat(data.getValue().getNgaycap())));
        noicap.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNoicap()));
        sdt.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getSdt()));
        email.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmail()));
        loadData();
    }

    private void loadData() {
        listthisinh = FXCollections.observableArrayList(thisinhDAO.listthisinh()); //listen change in DB
        tablethisinh.getItems().clear();
        tablethisinh.setItems(listthisinh);
    }

    public void handleRefresh(){
        loadData();
    }

    public void newThiSinh(ActionEvent e) throws IOException {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/english/thi_sinh_form.fxml"));
            Parent formThiSinh = loader.load();
            Scene scene = new Scene(formThiSinh);

            stage.setScene(scene);
            stage.show();
    }

    public void handleDeleteThiSinh() {
        try {
            thisinh selected = tablethisinh.getSelectionModel().getSelectedItem();
            thisinhDAO.deleteById(selected.getId());
            loadData();
        } catch (Exception e) {
            Notifications.create()
                    .title("Thông báo")
                    .text("Vui lòng chọn dữ liệu cần xóa")
                    .showWarning();
        }
    }

    public void setView(phongthi phongthi){
        loadData();
        phongthi_send = phongthi;
        insert_btn.setVisible(false);
        delete_btn.setVisible(false);
        edit_btn.setVisible(false) ;
        refresh_btn.setVisible(false);
    }

    public void handleEditThiSinh(ActionEvent e) throws IOException {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/english/thi_sinh_form.fxml"));
            Parent updateThiSinh = loader.load();
            Scene scene = new Scene(updateThiSinh);

            ThisinhFormController controller = loader.getController();
            thisinh selected = tablethisinh.getSelectionModel().getSelectedItem();
            controller.setView(selected);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e1) {
            Notifications.create()
                    .title("Thông báo")
                    .text("Vui lòng chọn dữ liệu cần chỉnh sửa")
                    .showWarning();
        }
    }

    public void insertToPhongthi(ActionEvent actionEvent){
        try {
            List<thisinh_phongthi> thisinh_phongthiList = phongthiDAO.getDetail(phongthi_send).getThisinh_phongthiList();
            int size = thisinh_phongthiList.size()+1;
            boolean flag = false;
            String size2 = size < 10 ? "0" + size : String.valueOf(size);
            thisinh selected = tablethisinh.getSelectionModel().getSelectedItem();
            for(thisinh_phongthi t : thisinh_phongthiList){
                if(selected.getId() == t.getId_thisinh()){
                    flag = true;
                    break;
                } else {
                    flag = false;
                }
            }

            if(flag){
                Notifications.create()
                        .title("Thông báo")
                        .text("Thí sinh đã tồn tại trong phòng thi")
                        .showWarning();
            } else {
                thisinh_phongthi thisinh_phongthi = new thisinh_phongthi();
                thisinh_phongthi.setId_thisinh(selected.getId());
                thisinh_phongthi.setId_phongthi(phongthi_send.getId());
                thisinh_phongthi.setSbd(phongthi_send.getTrinhdo() + size2);
                thisinh_phongthi.setDiemnghe(0);
                thisinh_phongthi.setDiemnoi(0);
                thisinh_phongthi.setDiemdoc(0);
                thisinh_phongthi.setDiemviet(0);
                thisinhphongthiDAO.insert(thisinh_phongthi);
                Notifications.create()
                        .title("Thông báo")
                        .text("Thêm thí sinh thành công")
                        .showWarning();
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
