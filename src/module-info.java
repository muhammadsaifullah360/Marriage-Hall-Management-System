module MHM {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.jfoenix;
    requires com.h2database;
    opens dashboard;
    opens dashboard.screens;
    opens dashboard.employee;
    opens main;
    opens login;
}
