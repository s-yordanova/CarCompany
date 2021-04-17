package Automobile;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class AddAutomobileController {

    @FXML
    private Label lb_register;

    @FXML
    private TextField automobile_type;

    @FXML
    private TableView<?> brandTable;

    @FXML
    private TableColumn<?, ?> ID;

    @FXML
    private TableColumn<?, ?> Type;

    @FXML
    private TableColumn<?, ?> Name1;

    @FXML
    private TableColumn<?, ?> Name2;

    @FXML
    private TextField automobile_model;

    @FXML
    private TextField automobile_price;

    @FXML
    void AddB(ActionEvent event) {

    }

    @FXML
    void UpdateB(ActionEvent event) {

    }

}
