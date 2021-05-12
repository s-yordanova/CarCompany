package Automobile;

import DataClasses.DataClassModel;
import DataClasses.DatabaseClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;

public class AddExtraController {
    DatabaseClass p=new DatabaseClass();
    DataClassModel f=new DataClassModel();
    @FXML
    private Label lb_register;

    @FXML
    private TextField extra_name;

    @FXML
    private TableView<DatabaseClass> brandTable;

    @FXML
    private TableColumn<DatabaseClass, Integer> ID;

    @FXML
    private TableColumn<DatabaseClass, String> Name;

    @FXML
    private Label lb_register1;

    @FXML
    private TextField search;

    @FXML
    void AddB(ActionEvent event) {
        try {
            String name = extra_name.getText();
            String query = "Select extra from extra where extra='" + name + "'";
            if(extra_name.getText().isEmpty()){
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("Name Cannot be empty");
                a.show();
            } else if (p.selectAll(name, query, "extra")) {

            }else{
                p.AddExtra(name);
                p.displayExtra(ID,Name,brandTable);
            }


        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void startUpdate() throws SQLException, ClassNotFoundException {
        brandTable.setOnMouseClicked((MouseEvent e) -> {
            if (e.getClickCount() > 1) {
                try {
                    onEdit();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
            }
        });
        p.displayExtra(ID, Name, brandTable);
    }
    public void onEdit() throws SQLException, ClassNotFoundException {
        // check the table's selected item and get selected item
        String name = extra_name.getText();
        String query = "Select extra from extra where extra='" + name + "'";

            if (extra_name.getText().isEmpty()) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("Name Cannot be empty");
                a.show();
            } else if (p.selectAll(name, query, "extra")) {

            } else {
                if (brandTable.getSelectionModel().getSelectedItem() != null) {
                    DatabaseClass data = brandTable.getSelectionModel().getSelectedItem();
                    name = extra_name.getText();
                    int id = data.getId();
                    p.UpdateExtra(id, name);
                    p.displayExtra(ID, Name, brandTable);
                }

            }
    }

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        startUpdate();
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                String name = search.getText();
                p.SelectExtra(name, ID, Name, brandTable);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (search.getText().isEmpty()) {
                try {
                    startUpdate();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
