package Automobile;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import javax.annotation.Resources;
import java.net.URL;
import java.sql.SQLException;

public class AddBrandController extends Automobile.DatabaseClass {

    @FXML
    private Label lb_register;

    @FXML
    private TextField brand_name;

    @FXML
    private TableView<DatabaseClass> brandTable;

    @FXML
    private TableColumn<DatabaseClass, Integer> ID;

    @FXML
    private TableColumn<DatabaseClass, String> Name;
    @FXML
    private TextField search;
    @FXML
    void AddB(ActionEvent event) {
        try {
            String name = brand_name.getText();
            System.out.println(name);
            AddBrand(name);
            display(ID,Name,brandTable);

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
        display(ID, Name, brandTable);
    }
    public void onEdit() throws SQLException, ClassNotFoundException {
        // check the table's selected item and get selected item
        if (brandTable.getSelectionModel().getSelectedItem() != null) {
            DatabaseClass data = brandTable.getSelectionModel().getSelectedItem();
            String name = brand_name.getText();
            int id =data.getId();
            UpdateBrand(id,name);
            display(ID,Name,brandTable);
        }
    }

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
       startUpdate();
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                String name = search.getText();
                Select(name,ID, Name, brandTable);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

    }
}

