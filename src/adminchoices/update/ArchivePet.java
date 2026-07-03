package adminchoices.update;

import java.util.Scanner;
import java.sql.*;
import database.DbConnection;

public class ArchivePet {

    public static void archivePet() {
        
        Scanner input = new Scanner(System.in);
        
        try {
            
            System.out.print("Enter Pet ID: ");
            int petId = input.nextInt();
            
            input.nextLine();
            
            Connection con = DbConnection.getConnection();
            
            String sql = 
                    "UPDATE pets" 
                    + "SET archived = 1 " 
                    + "WHERE pet_id = ?;";
            
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setInt(1, petId);
            
            int rows = pst.executeUpdate();
            
            if (rows > 0) {
                System.out.println("Pet archived.");
            } else {
                System.out.println("Archive failed.");
            }
            
            con.close();
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
