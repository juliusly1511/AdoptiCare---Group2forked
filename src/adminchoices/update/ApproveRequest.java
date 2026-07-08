package adminchoices.update;

import database.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ApproveRequest {

    public static void approveRequest(int reviewId) {

        try {
            Connection con = DbConnection.getConnection();

            String approveQuery
                    = "UPDATE adoption_requests "
                    + "SET "
                    + "review_date = CURRENT_TIMESTAMP, "
                    + "status = 'Approved', "
                    + "remarks = 'Qualified', "
                    + "archived = 1 "
                    + "WHERE request_id = ?";

            PreparedStatement pst = con.prepareStatement(approveQuery);

            pst.setInt(1, reviewId);

            int rows = pst.executeUpdate();

            if (rows > 0) {
                
                String selectQuery = 
                        "SELECT pet_id, user_id "
                        + "FROM adoption_requests "
                        + "WHERE request_id = ?";
                
                PreparedStatement selectPst = con.prepareStatement(selectQuery);
                
                selectPst.setInt(1, reviewId);
                
                ResultSet rs = selectPst.executeQuery();
                
                if (rs.next()) {
                    int petId = rs.getInt("pet_id");
                    int adopterId = rs.getInt("user_id");
                    
                    String updatePet = 
                            "UPDATE pets "
                            + "SET adoption_status = 'Adopted', "
                            + "archived = 1, "
                            + "user_id = ? "
                            + "WHERE pet_id = ?";
                    
                    PreparedStatement petPst = con.prepareStatement(updatePet);
                    
                    petPst.setInt(1, adopterId);
                    petPst.setInt(2, petId);
                    
                    int petRows = petPst.executeUpdate();
                    
                    if (petRows > 0) {
                        
                        String historyQuery = 
                                "INSERT INTO adoption_history "
                                + "(request_id, user_id, pet_id, adoption_date) "
                                + "VALUES (?, ?, ?, CURRENT_TIMESTAMP)";
                        
                        PreparedStatement historyPst = con.prepareStatement(historyQuery);
                        
                        historyPst.setInt(1, reviewId);
                        historyPst.setInt(2, adopterId);
                        historyPst.setInt(3, petId);
                        
                        int historyRows = historyPst.executeUpdate();
                        
                        if (historyRows > 0) {
                            System.out.println("\n===== ✅ Adoption approved successfully. =====\n");
                            System.out.println("===== ✅ Pet updated successfully. =====\n");
                            System.out.println("===== ✅ Adoption history recorded. =====");
                        } else {
                            System.out.println("\n❌ Failed to save adoption history.");
                        }
                        
                    } else {
                        System.out.println("\n❌ Failed to update pet.");
                    }
                    
                } else {
                    System.out.println("\n❌ Request not found.");
                }
                
            } else {
                System.out.println("\n❌ Failed to update approve request.");
            } 

        } catch (SQLException e) {
            System.out.println("\n❌ Error: " + e.getMessage());
        }
    }
}
