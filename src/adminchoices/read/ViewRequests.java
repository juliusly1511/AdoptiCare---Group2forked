package adminchoices.read;

import adminchoices.update.ApproveRequest;
import adminchoices.update.RejectRequest;
import database.DbConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ViewRequests {

    public static void viewRequests() {

        Scanner input = new Scanner(System.in);

        try {

            Connection con = DbConnection.getConnection();

            String selectReq
                    = "SELECT * "
                    + "FROM adoption_requests "
                    + "WHERE archived = 0";

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(selectReq);

            System.out.println("--------------------------------------------------------------------------------------------------------------------------------");

            System.out.printf(
                    "| %-8s | %-8s | %-8s | %-25s | %-12s | %-25s | %-15s |%n",
                    "Req ID",
                    "User ID",
                    "Pet ID",
                    "Request Date",
                    "Status",
                    "Review Date",
                    "Remarks"
            );

            System.out.println("--------------------------------------------------------------------------------------------------------------------------------");

            if (rs.next()) {

                do {

                    System.out.printf(
                            "| %-8d | %-8d | %-8d | %-25s | %-12s | %-25s | %-15s |%n",
                            rs.getInt("request_id"),
                            rs.getInt("user_id"),
                            rs.getInt("pet_id"),
                            String.valueOf(rs.getTimestamp("request_date")),
                            rs.getString("status"),
                            String.valueOf(rs.getTimestamp("review_date")),
                            rs.getString("remarks")
                    );

                    System.out.println("--------------------------------------------------------------------------------------------------------------------------------");

                } while (rs.next());

                System.out.print("\n👉 Enter Request ID to review: ");
                int reviewId = input.nextInt();

                //CHOOSE APPROVE OR REJECT
                System.out.println("\n⚙ What would you like to do?");

                System.out.println("[1]. ✅ Approve");
                System.out.println("[2]. ❌ Reject");

                System.out.print("\n👉 Choose an option [1/2]: ");
                int choice = input.nextInt();

                input.nextLine();

                switch (choice) {
                    case 1:
                        ApproveRequest.approveRequest(reviewId);
                        break;

                    case 2:
                        RejectRequest.rejectRequest(reviewId);
                        break;

                    default:
                        System.out.println("\n❌ Invalid choice, try again.\n");
                }

            } else {
                System.out.println("\n❌ No adoption request found.\n");
            }

        } catch (SQLException e) {
            System.out.println("\n❌ Error: " + e.getMessage());
        }
    }
}
