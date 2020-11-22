module MHM {
    
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.jfoenix;
    requires ojdbc6;
    opens LoginActivities;
    opens ForgetPassword;
    opens Dashboard;
    opens Dashboard.Screens;
//    opens styleSheet;
    opens Dashboard.Screens.EmployeeOperations;
}
