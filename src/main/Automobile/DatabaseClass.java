package Automobile;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;

public class DatabaseClass {
    int id;
    String name;

    public DatabaseClass(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public DatabaseClass() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    Statement connection() throws SQLException, ClassNotFoundException {
        String myDriver = "com.mysql.cj.jdbc.Driver";
        String myUrl = "jdbc:mysql://127.0.0.1:3306/car_company?autoReconnect=true&useSSL=false";
        Class.forName(myDriver);
        Connection conn = DriverManager.getConnection(myUrl, "root", "root");
        // our SQL SELECT query.
        // if you only need a few columns, specify them by name instead of using "*"
        // create the java statement
        return conn.createStatement();
    }

    void AddBrand(String name) {
        try {

            // create our mysql database connection
            Statement st = connection();
            // execute the query, and get a java resultset

            int rs = st.executeUpdate("INSERT INTO marka" + "(marka)" + "VALUES('" + name + "')");
            // iterate through the java resultset
            if (rs != 0) {

                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("Inserted into database");
                a.show();
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    void display(TableColumn f1, TableColumn f, TableView f2) throws SQLException, ClassNotFoundException {
        Statement st = connection();
        ObservableList<DatabaseClass> oblist = FXCollections.observableArrayList();
        oblist.clear();
        ResultSet rs1 = st.executeQuery("Select *from marka");
        while (rs1.next()) {
            oblist.add(new DatabaseClass(rs1.getInt("id_marka"), rs1.getString("marka")));
        }

        f1.setCellValueFactory(new PropertyValueFactory<>("id"));
        f.setCellValueFactory(new PropertyValueFactory<>("name"));
        f2.setItems(oblist);
        st.close();

    }

    void UpdateBrand(int id, String name) {
        try {

            // create our mysql database connection
            Statement st = connection();
            // execute the query, and get a java resultset

            int rs = st.executeUpdate("Update marka set marka='" + name + "' where id_marka='" + id + "'");
            // iterate through the java resultset
            if (rs != 0) {

                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("Update Successful");
                a.show();
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    void Select(String name,TableColumn f1, TableColumn f, TableView f2) throws SQLException, ClassNotFoundException {
        Statement st = connection();
        ObservableList<DatabaseClass> oblist = FXCollections.observableArrayList();
        oblist.clear();
        ResultSet rs1 = st.executeQuery("Select * from marka  where marka = '"+name+"'");
        while (rs1.next()) {
            oblist.add(new DatabaseClass(rs1.getInt("id_marka"), rs1.getString("marka")));
        }

        f1.setCellValueFactory(new PropertyValueFactory<>("id"));
        f.setCellValueFactory(new PropertyValueFactory<>("name"));
        f2.setItems(oblist);
        st.close();

    }
}
