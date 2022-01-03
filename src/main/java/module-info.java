module com.example.english {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;
    requires java.persistence;
    requires org.hibernate.orm.core;

    exports com.example.english;
    opens com.example.english to javafx.fxml, org.hibernate.orm.core;


    exports com.example.english.utils;
    opens com.example.english.utils to javafx.fxml, org.hibernate.orm.core;
    exports com.example.english.models;
    opens com.example.english.models to javafx.fxml, org.hibernate.orm.core;
    exports com.example.english.controllers;
    opens com.example.english.controllers to javafx.fxml, org.hibernate.orm.core;

    requires java.naming;
    requires lombok;
}