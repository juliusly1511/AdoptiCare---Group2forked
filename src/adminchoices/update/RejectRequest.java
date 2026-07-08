package adminchoices.update;

import database.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RejectRequest {

    public static void rejectRequest(int reviewId) {
        
        try {
            Connection con = DbConnection.getConnection();

            String sql
                    = "UPDATE adoption_requests "
                    + "SET "
                    + "review_date = CURRENT_TIMESTAMP, "
                    + "status = 'Rejected', "
                    + "remarks = 'Not qualified', "
                    + "archived = 1 "
                    + "WHERE request_id = ?";

            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setInt(1, reviewId);
            
            int rows = pst.executeUpdate();

            if (rows > 0) {
                System.out.println("\n✅ Adoption request rejected.");
            }

        } catch (SQLException e) {
            System.out.println("\n❌ Error: " + e.getMessage());
        }
    }
}
