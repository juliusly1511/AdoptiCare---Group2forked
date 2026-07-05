package customerchoices;

import auth.Login;
import database.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class SubmitAdoptionRequest {

    public static void submitAdoptionRequest() {
        
        Scanner input = new Scanner(System.in);
        
        try {
            
            System.out.print("Enter Pet ID: ");
            int petId = input.nextInt();

            input.nextLine();
            
            Connection con = DbConnection.getConnection();
            
            String sql = 
                    "INSERT INTO adoption_requests " 
                    + "(pet_id, user_id, request_date, status, review_date, remarks)" 
                    + "VALUES (?, ?, CURRENT_TIMESTAMP, 'Pending', CURRENT_TIMESTAMP, 'Waiting for review')";
            
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setInt(1, petId);
            pst.setInt(2, Login.loggedInUserId);
            
            int rows = pst.executeUpdate();
            
            if (rows > 0) {
                System.out.println("Adoption request submitted.");
            }
            
            con.close();
            
        } catch (SQLException e) {
            System.out.println(e);
        }
        
    }
}
