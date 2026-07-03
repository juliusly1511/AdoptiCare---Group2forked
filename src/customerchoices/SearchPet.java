package customerchoices;

import static customerchoices.ViewPetDetails.viewPetDetails;
import database.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SearchPet {

    public static void searchPet() {
        
        try {
            
            Scanner input = new Scanner(System.in);
            
            System.out.print("\nEnter Pet Name: ");
            String petName = input.nextLine();
            
            Connection con = DbConnection.getConnection();
            
            String sql = 
                    "SELECT * FROM pets WHERE pet_name LIKE ? "
                    + "AND archived = 0";
            
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setString(1, "%" + petName + "%");
            
            ResultSet rs = pst.executeQuery(sql);
            
            if (rs.next()){
 
                do {
                    
                    System.out.println("====================");
                    System.out.println("Pet ID: " + rs.getInt("pet_id"));
                    
                    System.out.println("Pet Name: " + rs.getString("pet_name"));
                    
                    System.out.println("Species: " + rs.getString("species"));

                    System.out.println("Age: " + rs.getInt("age")); 
                    
                    System.out.println("Gender: " + rs.getString("gender"));
                    
                    System.out.println("Breed: " + rs.getString("breed"));
                    
                    System.out.println("Description: " + rs.getString("description"));

                } while (rs.next());
                
                System.out.println("\nEnter Pet ID to view details: ");
                int petId = input.nextInt();
                
                ViewPetDetails.viewPetDetails(petId);
                
            } else {
                System.out.println("Pet not found.");
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
