package com.example.english.controllers;

import com.example.english.dao.*;
import com.example.english.models.*;
import com.example.english.utils.HibernateUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class KhoathiController implements Initializable {
    @FXML
    private TableView<khoathi> tablekhoathi;

    @FXML
    private TableColumn<khoathi, String> id, name, date;

    @FXML
    private TextField idtf, nametf;

    @FXML
    private DatePicker datetf;

    @FXML
    private ObservableList<khoathi> listkhoathi;

    @FXML
    private TextField searchField;

    private static SessionFactory factory;

    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        factory = HibernateUtil.getSessionFactory();
        id.setCellValueFactory(data -> new SimpleStringProperty(Integer.toString(data.getValue().getId())));
        name.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTenkhoathi()));
        date.setCellValueFactory(data -> new SimpleStringProperty(khoathiDAO.DateFormat(data.getValue().getNgaythi())));
        loadData();

        tablekhoathi.setOnMouseClicked((MouseEvent e) -> {
            khoathi selected = tablekhoathi.getSelectionModel().getSelectedItem();
            if (selected != null) {
                idtf.setText(Integer.toString(selected.getId()));
                nametf.setText(selected.getTenkhoathi());
                datetf.setValue(khoathiDAO.convertToLocalDateViaSqlDate(selected.getNgaythi()));
            }
        });
    }

    public void gotoDetails(ActionEvent e) throws IOException {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/english/phong_thi_list.fxml"));
            Parent phongthi = loader.load();
            Scene scene = new Scene(phongthi);

            PhongthiController controller = loader.getController();
            khoathi selected = tablekhoathi.getSelectionModel().getSelectedItem();
            controller.setView(selected);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e1) {
            e1.printStackTrace();
            Notifications.create()
                    .title("Thông báo")
                    .text("Vui lòng chọn dữ liệu cần xem chi tiết")
                    .showWarning();
        }

    }

    private void loadData() {
        listkhoathi = FXCollections.observableArrayList(khoathiDAO.listkhoathi());

        FilteredList<khoathi> filteredList = new FilteredList<>(listkhoathi, b -> true);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(khoathi -> {
                if( newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if(khoathi.getTenkhoathi().toLowerCase().indexOf(lowerCaseFilter) != -1 ){
                    return true;
                } else return false;
            });
        });

        SortedList<khoathi> sortedData = new SortedList<>(filteredList);

        sortedData.comparatorProperty().bind(tablekhoathi.comparatorProperty());
        tablekhoathi.setItems(sortedData);
        clear();
    }

    private void clear(){
        idtf.clear();
        nametf.clear();
        datetf.getEditor().clear();
    }

    public void handleInsertKhoaThi() {
        khoathi khoathi = new khoathi();
        try {
            khoathi.setTenkhoathi(nametf.getText());
            khoathi.setNgaythi(formatter1.parse(datetf.getValue().toString()));
            if ((idtf.getText()) == "") {
                khoathiDAO.insert(khoathi);
                loadData();
            } else {
                Notifications.create()
                        .title("Thông báo")
                        .text("Khóa thi đã tồn tại")
                        .showWarning();
            }
        } catch (Exception e) {
            Notifications.create()
                    .title("Thông báo")
                    .text("Vui lòng nhập dữ liệu cần thêm")
                    .showWarning();
        }
    }

    public void handleUpdateKhoaThi() {
        try {
            khoathi khoathi = new khoathi(Integer.parseInt(idtf.getText()), nametf.getText(), formatter1.parse(datetf.getValue().toString()));
            khoathiDAO.update(khoathi);
            loadData();
        } catch (Exception e) {
            Notifications.create()
                    .title("Thông báo")
                    .text("Vui lòng chọn dữ liệu cần update")
                    .showWarning();
        }
    }

    public void handleRefresh() {
        try {
            loadData();
        } catch (Exception e) {
            Notifications.create()
                    .title("Thông báo")
                    .text("Không có dữ liệu nào được làm mới")
                    .showWarning();
        }
    }

    public void handleDeleteKhoaThi() {
        try {
            khoathi khoathi = new khoathi(Integer.parseInt(idtf.getText()), nametf.getText(), formatter1.parse(datetf.getValue().toString()));
            khoathiDAO.delete(khoathi);
            loadData();
        } catch (Exception e) {
            Notifications.create()
                    .title("Thông báo")
                    .text("Vui lòng chọn dữ liệu cần xóa")
                    .showWarning();
        }
    }
}
