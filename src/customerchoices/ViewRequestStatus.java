package customerchoices;

import database.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewRequestStatus {

    public static void viewRequestStatus(int userId) {

        try {

            Connection con = DbConnection.getConnection();

            String queryRequest
                    = "SELECT ar.request_id, "
                    + "p.pet_name, "
                    + "ar.request_date, "
                    + "ar.status, "
                    + "ar.review_date,"
                    + "ar.remarks "
                    + "FROM adoption_requests ar "
                    + "LEFT JOIN pets p "
                    + "ON ar.pet_id = p.pet_id "
                    + "WHERE ar.user_id = ?";

            PreparedStatement pst = con.prepareStatement(queryRequest);

            pst.setInt(1, userId);

            ResultSet rs = pst.executeQuery();

            System.out.printf("\n===== REQUEST STATUS =====\n\n");

            System.out.printf("%-12s %-15s %-25s %-10s %-25s %-15s%n",
                    "Request ID",
                    "Pet Name",
                    "Request Date",
                    "Status",
                    "Review Date",
                    "Remarks"
            );

            System.out.println("--------------------------------------------------------------------------------");

            if (rs.next()) {

                do {
                    System.out.printf("%-12d %-15s %-25s %-10s %-25s %-15s%n",
                            rs.getInt("request_id"),
                            rs.getString("pet_name"),
                            rs.getTimestamp("request_date"),
                            rs.getString("status"),
                            rs.getTimestamp("review_date"),
                            rs.getString("remarks")
                    );

                    System.out.println("--------------------------------------------------------------------------------");

                } while (rs.next());
            } else {
                System.out.println("\nYou have no adoption requests.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
