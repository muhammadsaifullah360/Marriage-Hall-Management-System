package dashboard.screens;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class TeamController {
    
    @FXML
    private ListView<String> available_list;
    
    @FXML
    private ListView<String> selected_list;
    @FXML
    private JFXTextField search;
    
    public void initialize() {
    
        final ObservableList<String> candidates = FXCollections.observableArrayList("Z", "A", "B", "C", "D");
        available_list.setItems(candidates);
    
        final ObservableList<String> selected = FXCollections.observableArrayList();
        selected_list.setItems(selected);
    }
    
        public void onAdd (ActionEvent actionEvent){
            String potential = available_list.getSelectionModel().getSelectedItem();
            if (potential != null) {
                available_list.getSelectionModel().clearSelection();
//                candidates.remove(potential);
//                selected.add(potential);
            }
        }
    
        public void onRemove (ActionEvent actionEvent){
            String s = selected_list.getSelectionModel().getSelectedItem();
            if (s != null) {
                selected_list.getSelectionModel().clearSelection();
//                selected.remove(s);
//                candidates.add(s);
            }
        }
}
