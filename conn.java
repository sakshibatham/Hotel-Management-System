import java.sql.*;

public class conn {
    Connection c;
    Statement s;
    conn() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/HotelManagementSystem" , "root" , "Mysql@7719");
            s = c.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
