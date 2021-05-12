package Automobile;

import DataClasses.DataClassModel;
import DataClasses.DatabaseClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;

public class AddTypeController {

    @FXML
    private Label lb_register;

    @FXML
    private TextField type_name;

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
    DatabaseClass p=new DatabaseClass();
    DataClassModel f=new DataClassModel();
    @FXML
    void AddB(ActionEvent event) throws SQLException, ClassNotFoundException {
        String name = type_name.getText();
        String query="Select vid from vid where vid='"+name+"'";
        try {
            if(type_name.getText().isEmpty()){
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("Name Cannot be empty");
                a.show();
            } else if (p.selectAll(name,query,"vid")) {

            }else{
                p.AddType(name);
               p.displayType(ID,Name,brandTable);
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
        p.displayType(ID, Name, brandTable);
    }
    public void onEdit() throws SQLException, ClassNotFoundException {
        // check the table's selected item and get selected item
        String name = type_name.getText();
        String query = "Select vid from vid where vid='" + name + "'";
        try {
            if (type_name.getText().isEmpty()) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("Name Cannot be empty");
                a.show();
            } else if (p.selectAll(name, query, "vid")) {

            } else {
                if (brandTable.getSelectionModel().getSelectedItem() != null) {
                    DatabaseClass data = brandTable.getSelectionModel().getSelectedItem();
                    name = type_name.getText();
                    int id = data.getId();
                    p.UpdateType(id, name);
                    p.displayType(ID, Name, brandTable);
                }
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        startUpdate();
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                String name = search.getText();
                p.SelectType(name, ID, Name, brandTable);
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

