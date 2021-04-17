package Automobile;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class AddModelController {

    @FXML
    private Label lb_register;

    @FXML
    private TextField model_name;

    @FXML
    private TableView<?> brandTable;

    @FXML
    private TableColumn<?, ?> ID;

    @FXML
    private TableColumn<?, ?> Name;

    @FXML
    private TableColumn<?, ?> Name1;

    @FXML
    private TableColumn<?, ?> Name2;

    @FXML
    private TableColumn<?, ?> Name3;

    @FXML
    private TextField model_price;

    @FXML
    private TextField model_marka;

    @FXML
    private TextField model_extra;

    @FXML
    void AddB(ActionEvent event) {

    }

    @FXML
    void UpdateB(ActionEvent event) {

    }

}
