package adminchoices.update;

import database.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class ApproveRequest {

    public static void approveRequest() {

        Scanner input = new Scanner(System.in);

        try {
            System.out.print("Request ID: ");
            int requestId = input.nextInt();

            input.nextLine();

            Connection con = DbConnection.getConnection();

            String sql
                    = "UPDATE adoption_request "
                    + "SET "
                    + "status = 'Approved', "
                    + "remarks = 'Qualified' "
                    + "WHERE request_id = ?";

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setInt(1, requestId);

            int rows = pst.executeUpdate();

            if (rows > 0) {
                System.out.println("Adoption request approved.");
            } else {
                System.out.println("Adoption request failed.");
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

    }
}
