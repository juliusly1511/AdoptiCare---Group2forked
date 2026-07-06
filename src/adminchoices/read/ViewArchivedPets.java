package adminchoices.read;

import database.DbConnection;
import java.sql.*;
import java.util.Scanner;

public class ViewArchivedPets {

    public static void viewArchivedPets() {

        Scanner input = new Scanner(System.in);

        try {

            Connection con = DbConnection.getConnection();

            String sql
                    = "SELECT * FROM pets "
                    + "WHERE archived = 1";

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);

            System.out.printf(
                    "%-8s %-15s %-8s %-5s %-12s %-15s %-15s%n",
                    "ID", "Pet Name", "Gender", "Age", "Species", "Breed", "Status"
            );

            System.out.println("--------------------------------------------------------------------------------");

            if (!rs.isBeforeFirst()) {
                System.out.println("No archived pets found.");
                return;
            }

            while (rs.next()) {

                System.out.printf(
                        "%-8d %-15s %-8s %-5d %-12s %-15s %-15s%n",
                        rs.getInt("pet_id"),
                        rs.getString("pet_name"),
                        rs.getString("gender"),
                        rs.getInt("age"),
                        rs.getString("species"),
                        rs.getString("breed"),
                        rs.getString("adoption_status")
                );

                System.out.println("--------------------------------------------------------------------------------");
            }

            con.close();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
