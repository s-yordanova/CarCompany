import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Test {

   public static void main(String[] args){
       try
       {
           // create our mysql database connection
           String myDriver = "com.mysql.cj.jdbc.Driver";
           String myUrl = "jdbc:mysql://127.0.0.1:3306/car_company?autoReconnect=true&useSSL=false";
           Class.forName(myDriver);
           Connection conn = DriverManager.getConnection(myUrl, "root", "root");

           // our SQL SELECT query.
           // if you only need a few columns, specify them by name instead of using "*"
           String query = "INSERT INTO automobile"+"(id_avtomobil,cena,id_marka,id_vid)"+"VALUES(6,6,6,6)";

           // create the java statement
           Statement st = conn.createStatement();
st.executeUpdate(query);
           // execute the query, and get a java resultset
       /*    ResultSet rs = st.executeQuery(query);

           // iterate through the java resultset
           while (rs.next())
           {
               int id_avtomobil = rs.getInt("id_avtomobil");
          float cena=rs.getFloat("cena");
          int id_marka=rs.getInt("id_marka");
          int id_vid=rs.getInt("id_vid");
               // print the results
               System.out.format("%s, %s, %s, %s\n", id_avtomobil,cena,id_marka,id_vid);
           }

        */
           st.close();
       }
       catch (Exception e)
       {
          e.printStackTrace();

       }
   }
}
