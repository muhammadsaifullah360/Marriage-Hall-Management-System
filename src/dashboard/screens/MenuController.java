package dashboard.screens;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import database.DBService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    ObservableList<String> MenuList = FXCollections.observableArrayList();
    ObservableList<String> MenuSelection = FXCollections.observableArrayList();
    ObservableList<String> facility = FXCollections.observableArrayList();
    @FXML
    private CheckBox qorma;
    @FXML
    private CheckBox rice;
    @FXML
    private CheckBox salads;
    @FXML
    private CheckBox colddrinks;
    @FXML
    private CheckBox sweetDish;
    @FXML
    private CheckBox others;
    @FXML
    private JFXTextArea CommentArea;
    @FXML
    private CheckBox Qorma1;
    @FXML
    private CheckBox rice1;
    @FXML
    private CheckBox sweetDish1;
    @FXML
    private CheckBox other1;
    @FXML
    private RadioButton onClickSelfMenu;
    @FXML
    private RadioButton selectMenuFromHall;
    @FXML
    private AnchorPane MenuSelect;
    @FXML
    private RadioButton clickOnsingledish;
    @FXML
    private RadioButton onClickMultidish;
    @FXML
    private AnchorPane DishesPane;
    @FXML
    private AnchorPane DishesPane1;
    @FXML
    private CheckBox ACcheck;
    @FXML
    private CheckBox heaterCheck;
    @FXML
    private Label MenuSaveLabel;
    @FXML
    private JFXComboBox<String> decorationBox;
    
    public void selectMenuFromHall(ActionEvent event) {
        MenuSelect.setDisable(false);
        if (selectMenuFromHall.isSelected()) {
            MenuSelection.addAll(selectMenuFromHall.getText());
        }
    }
    
    public void onClickSelfMenu(ActionEvent event) {
        MenuSelect.setDisable(true);
        DishesPane1.setVisible(false);
        DishesPane.setVisible(false);
        if (onClickSelfMenu.isSelected()) {
            MenuSelection.addAll(onClickSelfMenu.getText());
        }
    }
    
    public void onClickMultidish(ActionEvent event) {
        DishesPane.setVisible(true);
        DishesPane1.setVisible(false);
    }
    
    public void clickOnsingledish(ActionEvent event) {
        DishesPane1.setVisible(true);
        DishesPane.setVisible(false);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> list3 = FXCollections.observableArrayList("Simple", "Fancy", "Customize");
        decorationBox.setItems(list3);
    }
    
    public void MenuDetailSave(ActionEvent event) {
        String commntArea = CommentArea.getText();
        String Decoration = decorationBox.getValue();
    
        String query = "INSERT  INTO Menu (Dishes,MenuService,Decoration,comments,facility)VALUES('" + MenuList + "','" + MenuSelection + "','" + Decoration + "','" + commntArea + "','" + facility + "')";
    
        DBService.executeUpdate(query);
        MenuSaveLabel.setText("Saved");
        clearFields();
    }
    
    public void clearFields() {
        decorationBox.setValue(null);
    }
    
    public void qormaCheck(ActionEvent event) {
        if (qorma.isSelected()) {
            MenuList.addAll(qorma.getText());
        }
    }
    
    public void riceCheck(ActionEvent event) {
        if (rice.isSelected()) {
            MenuList.addAll(rice.getText());
        }
    }
    
    public void Qorma1Check(ActionEvent event) {
        if (Qorma1.isSelected()) {
            MenuList.addAll(Qorma1.getText());
        }
    }
    
    public void rice1Check(ActionEvent event) {
        if (rice1.isSelected()) {
            MenuList.addAll(rice1.getText());
        }
    }
    
    public void sweetDish1Check(ActionEvent event) {
        if (sweetDish1.isSelected()) {
            MenuList.addAll(sweetDish1.getText());
        }
    }
    
    public void other1Check(ActionEvent event) {
        if (other1.isSelected()) {
            MenuList.addAll(other1.getText());
        }
    }
    
    public void OtherCheck(ActionEvent event) {
        if (others.isSelected()) {
            MenuList.addAll(others.getText());
        }
    }
    
    public void sweetDishCheck(ActionEvent event) {
        if (sweetDish.isSelected()) {
            MenuList.addAll(sweetDish.getText());
        }
    }
    
    public void colddrinksCheck(ActionEvent event) {
        if (colddrinks.isSelected()) {
            MenuList.addAll(colddrinks.getText());
        }
    }
    
    public void saladsCheck(ActionEvent event) {
        if (salads.isSelected()) {
            MenuList.addAll(salads.getText());
        }
    }
    
    public void heatercheck(ActionEvent event) {
        if (heaterCheck.isSelected()) {
            facility.addAll(heaterCheck.getText());
        }
    }
    
    public void AcCheck(ActionEvent event) {
        if (ACcheck.isSelected()) {
            facility.addAll(ACcheck.getText());
        }
    }
}
