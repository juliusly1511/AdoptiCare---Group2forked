package customerchoices;

import database.DbConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ViewPetDetails {

    public static void viewPetDetails() {
     
        Scanner input = new Scanner(System.in);
        
        try{
            
            Connection con = DbConnection.getConnection();
            
            String sql = "SELECT * FROM pets WHERE archived = 0";
            
            Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()) {
                
                System.out.println("\nPet ID: " + rs.getInt("pet_id"));
                
                System.out.println("Pet Name: " + rs.getString("pet_name"));
                
                System.out.println("Species: " + rs.getString("species"));
                
                System.out.println("Age: " + rs.getInt("age"));
                
                System.out.println("Gender: " + rs.getString("gender"));
                
                System.out.println("Breed: " + rs.getString("breed"));
                
                System.out.println("Health Condition: " + rs.getString("health_condition"));
                
                System.out.println("Vaccination Status: " + rs.getString("vaccination_status"));
                
                System.out.println("Description: " + rs.getString("description"));
            }
            
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
