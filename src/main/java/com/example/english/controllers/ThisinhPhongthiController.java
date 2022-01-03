package com.example.english.controllers;

import com.example.english.dao.khoathiDAO;
import com.example.english.dao.phongthiDAO;
import com.example.english.dao.thisinhDAO;
import com.example.english.dao.thisinhphongthiDAO;
import com.example.english.models.khoathi;
import com.example.english.models.phongthi;
import com.example.english.models.thisinh;
import com.example.english.models.thisinh_phongthi;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

import java.util.ArrayList;
import java.util.List;

public class ThisinhPhongthiController {
    @FXML
    private TableView<thisinh_phongthi> thisinhphongthitable;

    @FXML
    private TableColumn<thisinh_phongthi, String> sobaodanh, tenthisinh, sdt, email;

    @FXML
    private ObservableList<thisinh_phongthi> listthisinhphongthi;

    @FXML
    private TextField nghe, noi, doc, viet;

    @FXML
    private Label tenphongthi;

    public phongthi phongthi_send;

    public void setView(phongthi phongthi) {
        phongthi_send = phongthi;
        tenphongthi.setText(phongthi.getName());
        sobaodanh.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getSbd()));
        tenthisinh.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getThisinh().getHoten()));
        sdt.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getThisinh().getSdt()));
        email.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getThisinh().getSdt()));
        loadData();

        thisinhphongthitable.setOnMouseClicked((MouseEvent e) -> {
            thisinh_phongthi selected = thisinhphongthitable.getSelectionModel().getSelectedItem();
            if (selected != null) {
                nghe.setText(Float.toString(selected.getDiemnghe()));
                noi.setText(Float.toString(selected.getDiemnoi()));
                doc.setText(Float.toString(selected.getDiemdoc()));
                viet.setText(Float.toString(selected.getDiemviet()));
            }
        });
    }

    private void loadData(){
        List<thisinh_phongthi> thisinh_phongthisList = phongthiDAO.getDetail(phongthi_send).getThisinh_phongthiList();
        listthisinhphongthi = FXCollections.observableArrayList(thisinh_phongthisList);
        thisinhphongthitable.setItems(listthisinhphongthi);
    }

    private void clear(){
        nghe.clear();
        noi.clear();
        doc.clear();
        viet.clear();
    }
    public void handleRefresh(){
        loadData();
        clear();
    }


    @FXML
    public void addThiSinh(){
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/english/thi_sinh_list.fxml"));
            Parent thisinh = loader.load();
            Scene scene = new Scene(thisinh);

            ThisinhController controller = loader.getController();
            controller.setView(phongthi_send);

            stage.setScene(scene);
            stage.show();
        } catch (Exception e1) {
            e1.printStackTrace();
//            Notifications.create()
//                    .title("Thông báo")
//                    .text("Vui lòng chọn phòng thi cần xem")
//                    .showWarning();
        }
    }

    @FXML
    public void deleteThiSinh(){
        try {
            thisinh_phongthi selected = thisinhphongthitable.getSelectionModel().getSelectedItem();
            thisinhphongthiDAO.deleteById(selected.getId());
            loadData();
        } catch (Exception e) {
            Notifications.create()
                    .title("Thông báo")
                    .text("Vui lòng chọn dữ liệu cần xóa")
                    .showWarning();
        }
    }

    @FXML
    public void updateThiSinh(){
        try {
            thisinh_phongthi selected = thisinhphongthitable.getSelectionModel().getSelectedItem();
            thisinh_phongthi thisinh_phongthi = new thisinh_phongthi();
            thisinh_phongthi.setId(selected.getId());
            thisinh_phongthi.setId_thisinh(selected.getId_thisinh());
            thisinh_phongthi.setId_phongthi(selected.getId_phongthi());
            thisinh_phongthi.setSbd(selected.getSbd());
            thisinh_phongthi.setDiemnghe(Float.parseFloat(nghe.getText()));
            thisinh_phongthi.setDiemnoi(Float.parseFloat(noi.getText()));
            thisinh_phongthi.setDiemdoc(Float.parseFloat(doc.getText()));
            thisinh_phongthi.setDiemviet(Float.parseFloat(viet.getText()));
            thisinhphongthiDAO.update(thisinh_phongthi);
            handleRefresh();
        } catch (Exception e) {
            e.printStackTrace();
            Notifications.create()
                    .title("Thông báo")
                    .text("Vui lòng chọn thí sinh cần nhập điểm")
                    .showWarning();
        }
    }
}
