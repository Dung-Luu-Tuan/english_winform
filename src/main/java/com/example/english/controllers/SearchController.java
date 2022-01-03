package com.example.english.controllers;

import com.example.english.dao.*;
import com.example.english.models.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class SearchController implements Initializable {
    @FXML
    private TableView<thisinh_phongthi> searchTable;

    @FXML
    private TableColumn<thisinh_phongthi, String> sbd, phongthi, nghe, noi, doc, viet, hoten;

    @FXML
    private ObservableList<thisinh_phongthi> listsearch;

    @FXML
    private TextField searchField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sbd.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getSbd()));
        hoten.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getThisinh().getHoten()));
        phongthi.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPhongthi().getName()));
        nghe.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getDiemnghe())));
        noi.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getDiemnoi())));
        doc.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getDiemdoc())));
        viet.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getDiemviet())));

        listsearch = FXCollections.observableArrayList(thisinhphongthiDAO.list());

        FilteredList<thisinh_phongthi> filteredList = new FilteredList<>(listsearch, b -> true);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(thisinh_phongthi -> {
                if( newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if(thisinh_phongthi.getThisinh().getHoten().toLowerCase().indexOf(lowerCaseFilter) != -1 ){
                    return true;
                } else if(thisinh_phongthi.getThisinh().getSdt().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                } else return false;
            });
        });

        SortedList<thisinh_phongthi> sortedData = new SortedList<>(filteredList);

        sortedData.comparatorProperty().bind(searchTable.comparatorProperty());

        searchTable.setItems(sortedData);
    }
}
