package com.example.english.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    Button btnKhoathi;

    @FXML
    Button btnSearch;

    @FXML
    Label lbTittle;

    @FXML
    private StackPane contentArena;

    @FXML
    public void gotoKhoathi(javafx.event.ActionEvent actionEvent) throws IOException {
        lbTittle.setText("Danh sách khóa thi");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/english/khoa_thi_list.fxml"));
        Parent root = loader.load();
        contentArena.getChildren().removeAll();
        contentArena.getChildren().setAll(root);
        btnKhoathi.getStyleClass().add("active");
    }

    public void gotoSearch(ActionEvent actionEvent) throws IOException {
        lbTittle.setText("Tìm kiếm thí sinh");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/english/search.fxml"));
        Parent root = loader.load();
        contentArena.getChildren().removeAll();
        contentArena.getChildren().setAll(root);
        btnKhoathi.getStyleClass().add("active");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


}
