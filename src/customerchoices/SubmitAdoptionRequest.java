package customerchoices;

import auth.Login;
import database.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import static util.ShowPetList.showPetList;

public class SubmitAdoptionRequest {

    public static void submitAdoptionRequest() {

        Scanner input = new Scanner(System.in);

        try {
            Connection con = DbConnection.getConnection();

            showPetList(con);

            System.out.print("🆔 Enter Pet ID (press [0] to cancel): ");
            int petId = input.nextInt();

            input.nextLine();

            if (petId == 0) {
                System.out.println("↩ Returning to Customer Menu...");
                return;
            }

            String sql
                    = "INSERT INTO adoption_requests "
                    + "(pet_id, user_id, request_date, status, review_date, remarks)"
                    + "VALUES (?, ?, CURRENT_TIMESTAMP, 'Pending', NULL, 'Waiting for review')";

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setInt(1, petId);
            pst.setInt(2, Login.loggedInUserId);

            int rows = pst.executeUpdate();

            if (rows > 0) {
                System.out.println("\n✅ Adoption request submitted.");
            }

            con.close();

        } catch (SQLException e) {
            System.out.println(e);
        }

    }
}
