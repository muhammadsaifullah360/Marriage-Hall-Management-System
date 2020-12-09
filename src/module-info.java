module MHM {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.jfoenix;
    requires ojdbc6;
    opens dashboard;
    opens dashboard.screens;
    opens dashboard.screens.employeeOperations;
    opens main;
    opens login;
}
