package customerchoices;

import database.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SearchPet {

    public static void searchPet() {

        try {

            System.out.println("\n===== SEARCH PET =====");

            Scanner input = new Scanner(System.in);

            System.out.print("\nBreed: ");
            String breed = input.nextLine();

            Connection con = DbConnection.getConnection();

            String sql
                    = "SELECT * FROM pets WHERE breed LIKE ? "
                    + "AND archived = 0";

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, "%" + breed + "%");

            ResultSet rs = pst.executeQuery();

            System.out.printf(
                    "%-8s %-15s %-12s %-5s %-8s %-15s %-25s%n",
                    "Pet ID",
                    "Pet Name",
                    "Species",
                    "Age",
                    "Gender",
                    "Breed",
                    "Description"
            );

            System.out.println("--------------------------------------------------------------------------------");

            if (rs.next()) {

                do {

                    System.out.printf(
                            "%-8d %-15s %-12s %-5d %-8s %-15s %-25s%n",
                            rs.getInt("pet_id"),
                            rs.getString("pet_name"),
                            rs.getString("species"),
                            rs.getInt("age"),
                            rs.getString("gender"),
                            rs.getString("breed"),
                            rs.getString("description")
                    );

                    System.out.println("--------------------------------------------------------------------------------");

                } while (rs.next());

                System.out.println("\nEnter Pet ID to view details: ");
                int petId = input.nextInt();

                ViewPetDetails.viewPetDetails(petId);

            } else {
                System.out.println("\nPet not found.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
