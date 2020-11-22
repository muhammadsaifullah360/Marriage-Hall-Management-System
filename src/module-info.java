module MHM {
    
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.jfoenix;
    requires ojdbc6;
    opens loginActivities;
    opens ForgetPassword;
    opens dashboard;
    opens dashboard.Screens;
//    opens styleSheet;
    opens dashboard.Screens.EmployeeOperations;
}
