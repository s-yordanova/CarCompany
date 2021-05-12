package Automobile;

import DataClasses.DataClassModel;
import DataClasses.DatabaseClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddAutomobileController implements Initializable {
    DatabaseClass p=new DatabaseClass();
    DataClassModel f=new DataClassModel();
    @FXML
    private Label lb_register;

    @FXML
    private TableView<DatabaseClass> brandTable;

    @FXML
    private TableColumn<DatabaseClass, Integer> ID;

    @FXML
    private TableColumn<DatabaseClass, String> Type;

    @FXML
    private TableColumn<DatabaseClass, String> Name1;

    @FXML
    private TableColumn<DatabaseClass, String> Name2;

    @FXML
    private TableColumn<DatabaseClass, String> Brand;

    @FXML
    private ComboBox comboType;

    @FXML
    private ComboBox comboModel;
    @FXML
    private TextField price;
    private int model;
    private int type;

    @FXML
    void selectModel(ActionEvent event) throws SQLException, ClassNotFoundException {
        model= p.SelectModelCombo(comboModel);
    }

    @FXML
    void selectType(ActionEvent event) throws SQLException, ClassNotFoundException {
        type= p.SelectTypeCombo(comboType);
    }

    @FXML
    void AddB(ActionEvent event) throws SQLException, ClassNotFoundException {
        String price1=price.getText().toString();
p.AddAutomobile(type,model,price1);
p.displayAutomobile(ID,Type,Name1,Name2,Brand,brandTable);
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
        p.displayAutomobile(ID,Type,Name1,Name2,Brand,brandTable);
    }
    public void onEdit() throws SQLException, ClassNotFoundException {
        // check the table's selected item and get selected item

            if (brandTable.getSelectionModel().getSelectedItem() != null) {
                DatabaseClass data = brandTable.getSelectionModel().getSelectedItem();
                int id = data.getId();
                String p= price.getText();
               f.UpdateAutomobile(id, model,type,p);
                f.displayAutomobile(ID,Type,Name1,Name2,Brand,brandTable);
            }
        }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            p.displayModelCombo(comboModel);
            p.displayTypeCombo(comboType);
            startUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
