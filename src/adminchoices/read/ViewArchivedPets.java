package adminchoices.read;

import database.DbConnection;
import java.sql.*;
import java.util.Scanner;

public class ViewArchivedPets {

    public static void viewArchivedPets() {
        
        Scanner input = new Scanner(System.in);
        
        try {
            
            Connection con = DbConnection.getConnection();
            
            String sql = 
                    "SELECT * FROM pets " 
                    + "WHERE archived = 1";
            
            Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()) {
                
                System.out.println(rs.getInt("pet_Id")
                        + " - " 
                        + rs.getString("pet_name"));
            }
            
            con.close();
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
