package Automobile;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class AddCatalogController {

    @FXML
    private Label lb_register;

    @FXML
    private TextField type_name;

    @FXML
    private TableView<?> brandTable;

    @FXML
    private TableColumn<?, ?> ID;

    @FXML
    private TableColumn<?, ?> Vid;

    @FXML
    private TableColumn<?, ?> Cena;

    @FXML
    private TableColumn<?, ?> Marka;

    @FXML
    private TableColumn<?, ?> Model;

    @FXML
    private TableColumn<?, ?> Extra;

}
