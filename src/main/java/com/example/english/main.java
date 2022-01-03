package com.example.english;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class main extends Application{
    @Override
    public void start(Stage stage) throws Exception {
            Parent root = FXMLLoader.load(getClass().getResource("khoa_thi_list.fxml"));
            stage.setTitle("khóa thi");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);
    }

    public static void main (String[] args) {
        launch(args);
    }
}
