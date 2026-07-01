package customerchoices;

import static customerchoices.ViewPetDetails.viewPetDetails;
import database.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class SearchPet {

    public static void searchPet() {
        
        try {
            
            Scanner input = new Scanner(System.in);
            
            System.out.println("Pet Name: ");
            String petName = input.nextLine();
            
            Connection con = DbConnection.getConnection();
            
            String sql = 
                    "SELECT * FROM pets WHERE pet_name = ?";
            
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setString(1, petName);
            
            ResultSet rs = pst.executeQuery(sql);
            
            if (rs.next()){
 
                do {
                    System.out.println("Pet ID: " + rs.getInt("pet_id"));
                    
                    System.out.println("Pet Name: " + rs.getString("pet_name"));
                    
                    System.out.println("Species: " + rs.getString("species"));

                    System.out.println("Age: " + rs.getInt("age")); 
                    
                    System.out.println("Gender: " + rs.getString("gender"));
                    
                    System.out.println("Breed: " + rs.getString("breed"));
                    
                    System.out.println("Health Condition: " + rs.getString("health_condition"));
                    
                    System.out.println("Vaccination Status: " + rs.getString("vaccination_status"));
                    
                    System.out.println("Description: " + rs.getString("description"));

                } while (rs.next());
            } else {
                System.out.println("Pet not found.");
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
