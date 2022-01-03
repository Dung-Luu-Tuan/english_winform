package com.example.english.controllers;

import com.example.english.models.*;
import com.example.english.dao.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

import java.io.IOException;

public class PhongthiController {
    @FXML
    private TableView<phongthi> tablephongthi;

    @FXML
    private TableColumn<phongthi, String> id, name, trinhdo, cathi;

    @FXML
    private TextField idtf;

    @FXML
    private Label tenkhoathi;

    @FXML
    private ComboBox trinhdotf, cathitf;

    @FXML
    private ObservableList<phongthi> listphongthi;

    khoathi khoathi_send;

    int last_id;

    public void setView(khoathi khoathi) {
        khoathi_send = khoathi;
        tenkhoathi.setText(khoathi_send.getTenkhoathi());
        id.setCellValueFactory(data -> new SimpleStringProperty(Integer.toString(data.getValue().getId())));
        name.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));
        trinhdo.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTrinhdo()));
        cathi.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCathi()));
        loadData();

        tablephongthi.setOnMouseClicked((MouseEvent e) -> {
            phongthi selected = tablephongthi.getSelectionModel().getSelectedItem();
            if (selected != null) {
                idtf.setText(Integer.toString(selected.getId()));
                trinhdotf.setValue(selected.getTrinhdo());
                cathitf.setValue(selected.getCathi());
            }
        });
    }

    public void loadData(){
        khoathi khoathi = khoathiDAO.getDetail(khoathi_send.getId());
        listphongthi = FXCollections.observableArrayList(khoathi.getPhongthiList());
        last_id = khoathi.getPhongthiList().size() + 1;
        tablephongthi.setItems(listphongthi);
        clear();
    }

    private void clear(){
        idtf.clear();
        trinhdotf.valueProperty().setValue(null);
        cathitf.valueProperty().setValue(null);
    }

    public void handleRefresh(){
        loadData();
    }

    public void handleInsertPhongThi() {
        phongthi phongthi = new phongthi();
        try {
            phongthi.setId_khoathi(khoathi_send.getId());
            if(last_id <= 9) {
                phongthi.setName(trinhdotf.getValue().toString() + "P" + "0" + last_id);
            } else {
                phongthi.setName(trinhdotf.getValue().toString() + "P" + last_id);
            }
            phongthi.setCathi(cathitf.getValue().toString());
            phongthi.setTrinhdo(trinhdotf.getValue().toString());
            if ((idtf.getText()) == "") {
                phongthiDAO.insert(phongthi);
                loadData();
            } else {
                Notifications.create()
                        .title("Thông báo")
                        .text("Phòng thi đã tồn tại")
                        .showWarning();
            }
        } catch (Exception e) {
            Notifications.create()
                    .title("Thông báo")
                    .text("Vui lòng nhập dữ liệu cần thêm")
                    .showWarning();
        }
    }

    public void handleDeletePhongThi() {
        try {
            phongthi selected = tablephongthi.getSelectionModel().getSelectedItem();
            phongthiDAO.deleteById(selected.getId());
            loadData();
        } catch (Exception e) {
            Notifications.create()
                    .title("Thông báo")
                    .text("Vui lòng chọn dữ liệu cần xóa")
                    .showWarning();
        }
    }

    @FXML
    private void gotoThisinh(ActionEvent e) throws IOException {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/english/thi_sinh_phong_thi.fxml"));
            Parent thisinh = loader.load();
            Scene scene = new Scene(thisinh);

            ThisinhPhongthiController controller = loader.getController();
            phongthi selected = tablephongthi.getSelectionModel().getSelectedItem();
            controller.setView(selected);

            stage.setScene(scene);
            stage.show();
        } catch (Exception e1) {
            e1.printStackTrace();
            Notifications.create()
                    .title("Thông báo")
                    .text("Vui lòng chọn phòng thi cần xem")
                    .showWarning();
        }
    }
}
