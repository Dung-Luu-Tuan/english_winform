package com.example.english.controllers;

import com.example.english.dao.*;
import com.example.english.models.khoathi;
import com.example.english.models.thisinh;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class ThisinhFormController {
    @FXML
    private TextField idtf, hotentf, noisinhtf, cmndtf, noicaptf, sdttf, emailtf;

    @FXML
    private ComboBox gioitinhtf;

    @FXML
    private DatePicker ngaysinhtf, ngaycaptf;

    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");

    public void handleInsertThiSinh(ActionEvent event) {
        thisinh thisinh = new thisinh();
        try {
            thisinh.setHoten(hotentf.getText());
            thisinh.setGioitinh(gioitinhtf.getValue().toString());
            thisinh.setNgaysinh(formatter1.parse(ngaysinhtf.getValue().toString()));
            thisinh.setNoisinh(noisinhtf.getText());
            thisinh.setCmnd(cmndtf.getText());
            thisinh.setNgaycap(formatter1.parse(ngaycaptf.getValue().toString()));
            thisinh.setNoicap(noicaptf.getText());
            thisinh.setSdt(sdttf.getText());
            thisinh.setEmail(emailtf.getText());
            if ((idtf.getText()) == "") {
                thisinhDAO.insert(thisinh);
                ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
            } else {
                Notifications.create()
                        .title("Thông báo")
                        .text("Thi sinh đã tồn tại bạn không được phép lưu")
                        .showWarning();
            }
        } catch (Exception e) {
            Notifications.create()
                    .title("Thông báo")
                    .text("Vui lòng nhập đầy đủ dữ liệu cần thêm")
                    .showWarning();
        }
    }

    public void handleUpdateThiSinh(ActionEvent event) {
        try {
            thisinh thisinh = new thisinh(Integer.parseInt(idtf.getText()), hotentf.getText(), gioitinhtf.getValue().toString(),
                    formatter1.parse(ngaysinhtf.getValue().toString()), noisinhtf.getText(), cmndtf.getText(),
                    formatter1.parse(ngaycaptf.getValue().toString()), noicaptf.getText(), sdttf.getText(), emailtf.getText());
            thisinhDAO.update(thisinh);
            ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        } catch (Exception e) {
            Notifications.create()
                    .title("Thông báo")
                    .text("Vui lòng chọn dữ liệu cần update")
                    .showWarning();
        }
    }

    public void setView(thisinh selected) {
        idtf.setText(Integer.toString(selected.getId()));
        hotentf.setText(selected.getHoten());
        gioitinhtf.setValue(selected.getGioitinh());
        ngaysinhtf.setValue(khoathiDAO.convertToLocalDateViaSqlDate(selected.getNgaysinh()));
        noisinhtf.setText(selected.getNoisinh());
        cmndtf.setText(selected.getCmnd());
        ngaycaptf.setValue(khoathiDAO.convertToLocalDateViaSqlDate(selected.getNgaycap()));
        noicaptf.setText(selected.getNoicap());
        sdttf.setText(selected.getSdt());
        emailtf.setText(selected.getEmail());
    }


}
