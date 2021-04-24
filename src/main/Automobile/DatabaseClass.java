package Automobile;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;

public class DatabaseClass {
    int id;
    String name;
    String model;
    float price;
    String brand;
    String extra;

    public DatabaseClass(int id, String name, String model, float price, String brand, String extra) {
        this.id = id;
        this.name = name;
        this.model = model;
        this.price = price;
        this.brand = brand;
        this.extra = extra;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public DatabaseClass(int id, String name, String model, float price, String brand) {
        this.id = id;
        this.name = name;
        this.model = model;
        this.price = price;
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public DatabaseClass(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public DatabaseClass(String name) {
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

    void displayBrand(TableColumn f1, TableColumn f, TableView f2) throws SQLException, ClassNotFoundException {
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

    void SelectBrand(String name, TableColumn f1, TableColumn f, TableView f2) throws SQLException, ClassNotFoundException {
        Statement st = connection();
        ObservableList<DatabaseClass> oblist = FXCollections.observableArrayList();
        oblist.clear();
        ResultSet rs1 = st.executeQuery("Select * from marka  where marka = '" + name + "'");
        while (rs1.next()) {
            oblist.add(new DatabaseClass(rs1.getInt("id_marka"), rs1.getString("marka")));
        }

        f1.setCellValueFactory(new PropertyValueFactory<>("id"));
        f.setCellValueFactory(new PropertyValueFactory<>("name"));
        f2.setItems(oblist);
        st.close();

    }

    void displayExtra(TableColumn f1, TableColumn f, TableView f2) throws SQLException, ClassNotFoundException {
        Statement st = connection();
        ObservableList<DatabaseClass> oblist = FXCollections.observableArrayList();
        oblist.clear();
        ResultSet rs1 = st.executeQuery("Select *from extra");
        while (rs1.next()) {
            oblist.add(new DatabaseClass(rs1.getInt("id_extra"), rs1.getString("extra")));
        }

        f1.setCellValueFactory(new PropertyValueFactory<>("id"));
        f.setCellValueFactory(new PropertyValueFactory<>("name"));
        f2.setItems(oblist);
        st.close();
    }

    void AddExtra(String name) {
        try {

            // create our mysql database connection
            Statement st = connection();
            // execute the query, and get a java resultset

            int rs = st.executeUpdate("INSERT INTO extra" + "(extra)" + "VALUES('" + name + "')");
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

    void UpdateExtra(int id, String name) {
        try {

            // create our mysql database connection
            Statement st = connection();
            // execute the query, and get a java resultset

            int rs = st.executeUpdate("Update extra set extra='" + name + "' where id_extra='" + id + "'");
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

    void SelectExtra(String name, TableColumn f1, TableColumn f, TableView f2) throws SQLException, ClassNotFoundException {
        Statement st = connection();
        ObservableList<DatabaseClass> oblist = FXCollections.observableArrayList();
        oblist.clear();
        ResultSet rs1 = st.executeQuery("Select * from extra  where extra= '" + name + "'");
        while (rs1.next()) {
            oblist.add(new DatabaseClass(rs1.getInt("id_extra"), rs1.getString("extra")));
        }

        f1.setCellValueFactory(new PropertyValueFactory<>("id"));
        f.setCellValueFactory(new PropertyValueFactory<>("name"));
        f2.setItems(oblist);
        st.close();

    }

    void displayType(TableColumn f1, TableColumn f, TableView f2) throws SQLException, ClassNotFoundException {
        Statement st = connection();
        ObservableList<DatabaseClass> oblist = FXCollections.observableArrayList();
        oblist.clear();
        ResultSet rs1 = st.executeQuery("Select *from vid");
        while (rs1.next()) {
            oblist.add(new DatabaseClass(rs1.getInt("id_vid"), rs1.getString("vid")));
        }

        f1.setCellValueFactory(new PropertyValueFactory<>("id"));
        f.setCellValueFactory(new PropertyValueFactory<>("name"));
        f2.setItems(oblist);
        st.close();
    }

    void AddType(String name) {
        try {

            // create our mysql database connection
            Statement st = connection();
            // execute the query, and get a java resultset

            int rs = st.executeUpdate("INSERT INTO vid" + "(vid)" + "VALUES('" + name + "')");
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

    void UpdateType(int id, String name) {
        try {

            // create our mysql database connection
            Statement st = connection();
            // execute the query, and get a java resultset

            int rs = st.executeUpdate("Update vid set vid='" + name + "' where id_vid='" + id + "'");
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

    void SelectType(String name, TableColumn f1, TableColumn f, TableView f2) throws SQLException, ClassNotFoundException {
        Statement st = connection();
        ObservableList<DatabaseClass> oblist = FXCollections.observableArrayList();
        oblist.clear();
        ResultSet rs1 = st.executeQuery("Select * from vid  where vid= '" + name + "'");
        while (rs1.next()) {
            oblist.add(new DatabaseClass(rs1.getInt("id_vid"), rs1.getString("vid")));
        }

        f1.setCellValueFactory(new PropertyValueFactory<>("id"));
        f.setCellValueFactory(new PropertyValueFactory<>("name"));
        f2.setItems(oblist);
        st.close();

    }

    void displayTypeCombo(ComboBox p) throws SQLException, ClassNotFoundException {
        Statement st = connection();
        ObservableList<String> oblist = FXCollections.observableArrayList();
        oblist.clear();
        ResultSet rs1 = st.executeQuery("Select vid from vid");
        while (rs1.next()) {
            oblist.add(rs1.getString("vid"));
        }
        p.setItems(oblist);
        st.close();
    }

    void displayModelCombo(ComboBox p) throws SQLException, ClassNotFoundException {
        Statement st = connection();
        ObservableList<String> oblist = FXCollections.observableArrayList();
        oblist.clear();
        ResultSet rs1 = st.executeQuery("Select model from model");
        while (rs1.next()) {
            oblist.add(rs1.getString("model"));
        }
        p.setItems(oblist);
        st.close();
    }

    int SelectTypeCombo(ComboBox p) throws SQLException, ClassNotFoundException {
        Statement st = connection();
        String f;
        int i = 0;
        f = (String) p.getValue();

        ResultSet f1 = st.executeQuery("Select * from vid  where vid= '" + f + "'");
        while (f1.next()) {
            i = f1.getInt("id_vid");
        }
        return i;
    }

    int SelectModelCombo(ComboBox p) throws SQLException, ClassNotFoundException {
        Statement st = connection();
        String f;
        int i = 0;
        f = (String) p.getValue();

        ResultSet f1 = st.executeQuery("Select * from model  where model= '" + f + "'");
        while (f1.next()) {
            i = f1.getInt("id_model");
        }
        return i;
    }

    void AddAutomobile(int type, int model, String price) {
        try {
            float price2 = 0;

            // create our mysql database connection
            Statement st = connection();
            // execute the query, and get a java resultset

            ResultSet pricesql = st.executeQuery("Select cena from model where id_model='" + model + "'");
            while (pricesql.next()) {
                price2 = Float.parseFloat(price) + pricesql.getFloat("cena");
            }
            int rs = st.executeUpdate("INSERT INTO automobile" + "(id_vid,id_model,cena)" + "VALUES('" + type + "','" + model + "','" + price2 + "')");
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

    boolean selectAll(String name, String query, String f) throws SQLException, ClassNotFoundException {
        boolean p;
        Statement st = connection();
        ResultSet rs1 = st.executeQuery(query);
        String namecheck = null;
        while (rs1.next())
            namecheck = rs1.getString(f);
        if (name.equals(namecheck)) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("That name is used");
            a.show();
            p = true;
        } else {
            p = false;
        }
        return p;
    }

    void displayAutomobile(TableColumn f, TableColumn f1, TableColumn f2, TableColumn f3, TableColumn f4, TableView f5) throws SQLException, ClassNotFoundException {
        Statement st = connection();
        ObservableList<DatabaseClass> oblist = FXCollections.observableArrayList();
        oblist.clear();
        ResultSet rs1 = st.executeQuery("Select a.id_automobile,v.vid,m.model,a.cena,ma.marka from automobile a join model m on a.id_model=m.id_model join marka ma on m.id_marka=ma.id_marka join vid v on a.id_vid=v.id_vid");
        while (rs1.next()) {
            oblist.add(new DatabaseClass(rs1.getInt("a.id_automobile"), rs1.getString("v.vid"), rs1.getString("m.model"), rs1.getFloat("a.cena"), rs1.getString("ma.marka")));
        }

        f.setCellValueFactory(new PropertyValueFactory<>("id"));
        f1.setCellValueFactory(new PropertyValueFactory<>("name"));
        f2.setCellValueFactory(new PropertyValueFactory<>("model"));
        f3.setCellValueFactory(new PropertyValueFactory<>("price"));
        f4.setCellValueFactory(new PropertyValueFactory<>("brand"));
        f5.setItems(oblist);
        st.close();
    }

    void UpdateAutomobile(int id, int type, int model, String price) {
        try {
            float price2 = 0;
            // create our mysql database connection
            Statement st = connection();
            // execute the query, and get a java resultset
            ResultSet pricesql = st.executeQuery("Select cena from model where id_model='" + model + "'");
            while (pricesql.next()) {
                price2 = Float.parseFloat(price) + pricesql.getFloat("cena");
            }
            int rs = st.executeUpdate("Update automobile set id_vid='" + type + "',id_model='" + model + "',cena='" + price2 + "'where id_automobile='" + id + "'");
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

    void displayCatalog(TableColumn f, TableColumn f1, TableColumn f2, TableColumn f3, TableColumn f4, TableColumn f5, TableView f6) throws SQLException, ClassNotFoundException {
        Statement st = connection();
        ObservableList<DatabaseClass> oblist = FXCollections.observableArrayList();
        oblist.clear();
        ResultSet rs1 = st.executeQuery("Select a.id_automobile,v.vid,m.model,a.cena,ma.marka ,e.extra from automobile a join model m on a.id_model=m.id_model join marka ma on m.id_marka=ma.id_marka join vid v on a.id_vid=v.id_vid join extra e on m.id_extra=e.id_extra");
        while (rs1.next()) {
            oblist.add(new DatabaseClass(rs1.getInt("a.id_automobile"), rs1.getString("v.vid"), rs1.getString("m.model"), rs1.getFloat("a.cena"), rs1.getString("ma.marka"), rs1.getString("e.extra")));
        }

        f.setCellValueFactory(new PropertyValueFactory<>("id"));
        f1.setCellValueFactory(new PropertyValueFactory<>("name"));
        f4.setCellValueFactory(new PropertyValueFactory<>("model"));
        f2.setCellValueFactory(new PropertyValueFactory<>("price"));
        f3.setCellValueFactory(new PropertyValueFactory<>("brand"));
        f5.setCellValueFactory(new PropertyValueFactory<>("extra"));
        f6.setItems(oblist);

        st.close();
    }

    void selectCatalog(String name, TableColumn f, TableColumn f1, TableColumn f2, TableColumn f3, TableColumn f4, TableColumn f5, TableView f6) throws SQLException, ClassNotFoundException {

        Statement st = connection();
        ObservableList<DatabaseClass> oblist = FXCollections.observableArrayList();
        oblist.clear();
        ResultSet rs1 = st.executeQuery("Select a.id_automobile,v.vid,m.model,a.cena,ma.marka ,e.extra from automobile a join model m on a.id_model=m.id_model join marka ma on m.id_marka=ma.id_marka join vid v on a.id_vid=v.id_vid join extra e on m.id_extra=e.id_extra where m.model='" + name + "'");
        while (rs1.next()) {
            oblist.add(new DatabaseClass(rs1.getInt("a.id_automobile"), rs1.getString("v.vid"), rs1.getString("m.model"), rs1.getFloat("a.cena"), rs1.getString("ma.marka"), rs1.getString("e.extra")));
        }

        f.setCellValueFactory(new PropertyValueFactory<>("id"));
        f1.setCellValueFactory(new PropertyValueFactory<>("name"));
        f4.setCellValueFactory(new PropertyValueFactory<>("model"));
        f2.setCellValueFactory(new PropertyValueFactory<>("price"));
        f3.setCellValueFactory(new PropertyValueFactory<>("brand"));
        f5.setCellValueFactory(new PropertyValueFactory<>("extra"));
        f6.setItems(oblist);
        st.close();
    }
}

