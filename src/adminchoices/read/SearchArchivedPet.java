package adminchoices.read;

import database.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import util.AgeConverter;

public class SearchArchivedPet {

    public static void searchArchivedPet() {

        Scanner input = new Scanner(System.in);

        try {

            Connection con = DbConnection.getConnection();

            while (true) {
                System.out.println("\n===== 🔍 SEARCH ARCHIVED PET =====");

                System.out.print("🆔 Enter Species (type [Cancel] to cancel): ");

                String species = input.nextLine();

                if (species.equalsIgnoreCase("Cancel")) {
                    System.out.println("\n↩ Returning to Archive Menu...");
                    return;
                }

                String sql
                        = "SELECT * "
                        + "FROM pets "
                        + "WHERE species = ? AND archived = 1";

                PreparedStatement pst = con.prepareStatement(sql);

                pst.setString(1, species);

                ResultSet rs = pst.executeQuery();

                System.out.println("\n===== 🗄 Archived Pet Details =====");

                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

                System.out.printf(
                        "| %-6s | %-15s | %-8s | %-14s | %-10s | %-15s | %-30s | %-12s |%n",
                        "Pet ID",
                        "Pet Name",
                        "Gender",
                        "Age",
                        "Species",
                        "Breed",
                        "Description",
                        "Status"
                );

                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

                if (rs.next()) {

                    do {
                        
                        System.out.printf(
                                "| %-6d | %-15s | %-8s | %-14s | %-10s | %-15s | %-30s | %-12s |%n",
                                rs.getInt("pet_id"),
                                rs.getString("pet_name"),
                                rs.getString("gender"),
                                AgeConverter.convertAge(rs.getDouble("age")),
                                rs.getString("species"),
                                rs.getString("breed"),
                                rs.getString("description"),
                                rs.getString("adoption_status")
                        );

                        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                        
                    } while (rs.next());

                    break;

                } else {
                    System.out.println("\n❌ Archived pet not found. Try again.");
                }
            }

        } catch (SQLException e) {
            System.out.println("\n❌ Error: " + e.getMessage());
        }

    }
}
