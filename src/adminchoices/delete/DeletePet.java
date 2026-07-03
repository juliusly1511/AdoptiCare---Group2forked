package adminchoices.delete;

import database.DbConnection;
import java.sql.*;
import java.util.Scanner;

public class DeletePet {

    public static void deletePet() {
        
        Scanner input = new Scanner(System.in);
        
        try {
            
            System.out.println("Enter Pet ID: ");
            int petId = input.nextInt();
            
            input.nextLine();
            
            Connection con = DbConnection.getConnection();
            
            String sql =
                    "DELETE " 
                    + "FROM pets " 
                    + "WHERE pet_id = ?";
            
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setInt(1, petId);
            
            int rows = pst.executeUpdate();
            
            if (rows > 0) {
                System.out.println("Pet deleted successfully!");
            } else {
                System.out.println("Pet not found.");
            }
            
            con.close();
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
