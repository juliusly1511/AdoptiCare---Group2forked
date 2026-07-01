package adminchoices.read;

import database.DbConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ViewRequests {

    public static void viewRequests(){
        
        try {
            
            Connection con = DbConnection.getConnection();

            String sql = 
                    "SELECT * "
                    + "FROM adoption_request "
                    + "WHERE archived = 0";
            
            Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery(sql);
            
            if(rs.next()) {
                do {
                
                System.out.println("Request ID: " + rs.getInt("request_id"));
                
                System.out.println("User ID: " + rs.getInt("user_id"));
                
                System.out.println("Pet_ID: " + rs.getInt("pet_id"));
                
                System.out.println("Request Date: " + rs.getTimestamp("request_date"));
                
                System.out.println("Status: " + rs.getString("status"));
                
                System.out.println("Review Date: " + rs.getTimestamp("review_date"));
                
                System.out.println("Remarks: " + rs.getString("remarks"));
                
                System.out.println("====================================");
            }
            
            while (rs.next());
            } else {
                System.out.println("No adoption request found.");
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
