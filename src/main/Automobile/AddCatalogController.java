package Automobile;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddCatalogController extends DatabaseClass implements Initializable {

    @FXML
    private Label lb_register;

    @FXML
    private TextField type_name;

    @FXML
    private TableView<DatabaseClass> brandTable;

    @FXML
    private TableColumn<DatabaseClass, Integer> ID;

    @FXML
    private TableColumn<DatabaseClass, String> Vid;

    @FXML
    private TableColumn<DatabaseClass, Float> Cena;

    @FXML
    private TableColumn<DatabaseClass, String> Marka;

    @FXML
    private TableColumn<DatabaseClass, String> Model;

    @FXML
    private TableColumn<DatabaseClass, String> Extra;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        type_name.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                String name = type_name.getText();
                selectCatalog(name,ID,Vid,Cena,Marka,Model,Extra,brandTable);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if(type_name.getText().isEmpty()){
                try {
                    displayCatalog(ID,Vid,Cena,Marka,Model,Extra,brandTable);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        try {
            displayCatalog(ID,Vid,Cena,Marka,Model,Extra,brandTable);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
