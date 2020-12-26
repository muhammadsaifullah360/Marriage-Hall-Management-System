package dashboard.screens;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class teamController {
    
    @FXML
    private ListView<String> availabelList;
    
    @FXML
    private ListView<String> selectList;
    
    public void initialize() {
    
        final ObservableList<String> candidates = FXCollections.observableArrayList("Z", "A", "B", "C", "D");
        availabelList.setItems(candidates);
    
        final ObservableList<String> selected = FXCollections.observableArrayList();
        selectList.setItems(selected);
    }
    
        public void onAdd (ActionEvent actionEvent){
            String potential = availabelList.getSelectionModel().getSelectedItem();
            if (potential != null) {
                availabelList.getSelectionModel().clearSelection();
//                candidates.remove(potential);
//                selected.add(potential);
            }
        }
    
        public void onRemove (ActionEvent actionEvent){
            String s = selectList.getSelectionModel().getSelectedItem();
            if (s != null) {
                selectList.getSelectionModel().clearSelection();
//                selected.remove(s);
//                candidates.add(s);
            }
        }
}
