package Automobile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
public class DataClassModel extends DatabaseClass{
    int id;
    String name;
    float price;
    String marka;
    String extra;
    public DataClassModel(){}

    public DataClassModel(int id, String name, float price, String marka, String extra) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.marka = marka;
        this.extra = extra;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    void AddModel(String name, String price, String marka, String extra) {
        try {
            // create our mysql database connection
            Statement st = connection();
            // execute the query, and get a java resultset
            int rs = st.executeUpdate("INSERT INTO model" + "(model,cena,id_marka,id_extra)" + "VALUES('" + name + "','"+price+"',(Select id_marka from marka where marka='"+marka+"'),(Select id_extra from extra where extra='"+extra+"'))");
            // iterate through the java resultset
            if (rs != 0) {

                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("Inserted into database");
                a.show();
            }
        } catch (Exception e) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Cannot be empty or NULL");
            a.show();

        }

    }
    void displayModel(TableColumn f, TableColumn f1,TableColumn f2,TableColumn f3,TableColumn f4,TableView f5) throws SQLException, ClassNotFoundException {
        Statement st = connection();
        ObservableList<DatabaseClass> oblist = FXCollections.observableArrayList();
        oblist.clear();
        ResultSet rs1 = st.executeQuery("Select m.id_model,m.model,m.cena,ma.marka,e.extra from model m join marka ma on ma.id_marka=m.id_marka join extra e on e.id_extra=m.id_extra;");
        while (rs1.next()) {
            oblist.add(new DataClassModel(rs1.getInt("m.id_model"), rs1.getString("m.model"),rs1.getFloat("m.cena"),rs1.getString("ma.marka"),rs1.getString("e.extra")));
        }

        f.setCellValueFactory(new PropertyValueFactory<>("id"));
        f1.setCellValueFactory(new PropertyValueFactory<>("name"));
        f2.setCellValueFactory(new PropertyValueFactory<>("price"));
        f3.setCellValueFactory(new PropertyValueFactory<>("marka"));
        f4.setCellValueFactory(new PropertyValueFactory<>("extra"));

        f5.setItems(oblist);
        st.close();

    }
    void UpdateModel(int id, String name, String price, String marka, String extra) {
        try {

            // create our mysql database connection
            Statement st = connection();
            // execute the query, and get a java resultset

            int rs = st.executeUpdate("Update model set model='" + name + "',cena='"+price+"',id_marka=(Select id_marka from marka where marka='"+marka+"'),id_extra=(Select id_extra from extra where extra='"+extra+"') where id_model='" + id + "'");
            // iterate through the java resultset
            if (rs != 0) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("Update Successful");
                a.show();
            }
        } catch (Exception e) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Cannot be empty or NULL");
            a.show();

        }
    }
    void SelectModel(String name,TableColumn f, TableColumn f1,TableColumn f2,TableColumn f3,TableColumn f4,TableView f5) throws SQLException, ClassNotFoundException {
        Statement st = connection();
        ObservableList<DatabaseClass> oblist = FXCollections.observableArrayList();
        oblist.clear();
        ResultSet rs1 = st.executeQuery("Select * from model m join marka ma on ma.id_marka=m.id_marka join  extra e on e.id_extra=m.id_extra  where model= '"+name+"'");
        while (rs1.next()) {
            oblist.add(new DataClassModel(rs1.getInt("m.id_model"), rs1.getString("m.model"),rs1.getFloat("m.cena"),rs1.getString("ma.marka"),rs1.getString("e.extra")));
        }

        f.setCellValueFactory(new PropertyValueFactory<>("id"));
        f1.setCellValueFactory(new PropertyValueFactory<>("name"));
        f2.setCellValueFactory(new PropertyValueFactory<>("price"));
        f3.setCellValueFactory(new PropertyValueFactory<>("marka"));
        f4.setCellValueFactory(new PropertyValueFactory<>("extra"));

        f5.setItems(oblist);
        st.close();

    }
    boolean selectAll(String name, String query,String f) throws SQLException, ClassNotFoundException {
        boolean p;
        Statement st = connection();
        ResultSet rs1 = st.executeQuery(query);
        String namecheck = null;
        while (rs1.next())
            namecheck=rs1.getString(f);
        if(name.equals(namecheck)){
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("That name is used");
            a.show();
            p=true;
        }else{
            p =false;
        }
        return p;
    }
}
