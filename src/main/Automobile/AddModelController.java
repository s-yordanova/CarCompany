package Automobile;



import DataClasses.DataClassModel;
import DataClasses.DatabaseClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;

public class AddModelController  {


    @FXML
    private Label lb_register;

    @FXML
    private TextField model_name;

    @FXML
    private TableView<DataClassModel> brandTable;

    @FXML
    private TableColumn<DataClassModel, Integer> ID;

    @FXML
    private TableColumn<DataClassModel, String> Name;

    @FXML
    private TableColumn<DataClassModel, Float> Price;

    @FXML
    private TableColumn<DataClassModel, String> Brand;

    @FXML
    private TableColumn<DataClassModel, String> Extra;

    @FXML
    private TextField model_price;

    @FXML
    private TextField model_marka;

    @FXML
    private TextField model_extra;

    @FXML
    private Label lb_register1;

    @FXML
    private TextField search;
    DatabaseClass p=new DatabaseClass();
    DataClassModel f=new DataClassModel();
    @FXML
    void AddB(ActionEvent event) {
        String name = model_name.getText();
        String query = "Select model from model where model='" + name + "'";
        try {
            String price=model_price.getText();
            String marka=model_marka.getText();
            String extra=model_extra.getText();
            if(name.isEmpty() || name.length()== 0 ||price.isEmpty()||price==null||marka.isEmpty()||marka.length()==0 ||extra.isEmpty()|| extra==null){
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("Cannot have empty fields");
                a.show();
            }else if (p.selectAll(name, query, "model")) {

            }else if(name.length()>20 || marka.length()>20){
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("Name Cannot be more than 20 chars");
                a.show();
            }else{
                f.AddModel(name,price,marka,extra);
                f.displayModel(ID,Name,Price,Brand,Extra,brandTable);
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
        f.displayModel(ID,Name,Price,Brand,Extra,brandTable);
    }
    public void onEdit() throws SQLException, ClassNotFoundException {
        // check the table's selected item and get selected item
        String name = model_name.getText();
        String price = model_price.getText();
        String marka = model_marka.getText();
        String extra = model_extra.getText();
        String query = "Select model from model where model='" + name + "'";
              if(name.isEmpty() || name==null||price.isEmpty()||price==null||marka.isEmpty()||marka==null ||extra.isEmpty()|| extra==null){
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("Name Cannot be empty");
                a.show();

            } else if (p.selectAll(name, query, "model")) {

            } else {
                if (brandTable.getSelectionModel().getSelectedItem() != null) {
                    DatabaseClass data = brandTable.getSelectionModel().getSelectedItem();
                    int id = data.getId();
                    f.UpdateModel(id, name, price, marka, extra);
                    f.displayModel(ID, Name, Price, Brand, Extra, brandTable);
                }
            }
    }
        @FXML
        void initialize () throws SQLException, ClassNotFoundException {
            startUpdate();
            search.textProperty().addListener((observable, oldValue, newValue) -> {
                try {
                    String name = search.getText();
                    f.SelectModel(name,ID, Name, Price, Brand, Extra, brandTable);;
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


