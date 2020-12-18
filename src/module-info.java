module MHM {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.jfoenix;
    requires ojdbc6;
    requires com.h2database;
    opens dashboard;
    opens dashboard.screens;
    opens dashboard.screens.employeeOperations;
    opens main;
    opens login;
}
