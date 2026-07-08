package adminchoices.read;

import database.DbConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import util.AgeConverter;

public class ViewArchivedPets {

    public static void viewArchivedPets() {

        Scanner input = new Scanner(System.in);

        try {

            Connection con = DbConnection.getConnection();

            System.out.println("\n===== 🗄 ARCHIVED PETS =====");

            String sql
                    = "SELECT * FROM pets "
                    + "WHERE archived = 1";

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);

            if (!rs.next()) {
                System.out.println("ℹ No archived pets found.");
                System.out.println("\nPress [Enter] to return to the Admin Menu...");
                input.nextLine();
                con.close();
                return;
            }

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

            con.close();

        } catch (SQLException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}
