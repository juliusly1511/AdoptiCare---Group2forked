package customerchoices;

import database.DbConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ViewAvailablePets {

    public static void viewAvailablePets() {

        try {
            Scanner input = new Scanner(System.in);

            System.out.println("\n===== AVAILABLE PETS =====");

            Connection con = DbConnection.getConnection();

            String sql = "SELECT * "
                    + "FROM pets "
                    + "WHERE archived = 0 AND adoption_status = 'Available'";

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);

            System.out.printf(
                    "%-8s %-15s %-8s %-5s %-12s %-15s %-25s %-15s%n",
                    "Pet ID",
                    "Pet Name",
                    "Gender",
                    "Age",
                    "Species",
                    "Breed",
                    "Description",
                    "Status"
            );

            System.out.println("--------------------------------------------------------------------------------");

            if (rs.next()) {

                do {

                    System.out.printf(
                            "%-8d %-15s %-8s %-5d %-12s %-15s %-25s %-15s%n",
                            rs.getInt("pet_id"),
                            rs.getString("pet_name"),
                            rs.getString("gender"),
                            rs.getInt("age"),
                            rs.getString("species"),
                            rs.getString("breed"),
                            rs.getString("description"),
                            rs.getString("adoption_status")
                    );

                    System.out.println("--------------------------------------------------------------------------------");
                } while (rs.next());

                System.out.print("\nEnter Pet ID to view details: ");
                int petId = input.nextInt();

                ViewPetDetails.viewPetDetails(petId);
            } else {
                System.out.println("No pets available.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
