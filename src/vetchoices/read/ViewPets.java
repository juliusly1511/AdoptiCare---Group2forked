package vetchoices.read;

import database.DbConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ViewPets {

    public static void viewPets() {
        try {
            Connection con = DbConnection.getConnection();

            String sql = "SELECT * FROM pets WHERE archived = 0";

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);

            System.out.println("\n===== PET LIST =====");

            System.out.printf("%-5s %-15s %-12s %-20s %-5s %-30s %-15s%n,",
                    "ID", "Pet Name", "Species", "Breed", "Age", "Description", "Adoption Status");

            while (rs.next()) {

                System.out.printf("%-5d %-15s %-12s %-20s %-5d %-15s%n",
                        rs.getInt("pet_id"),
                        rs.getString("pet_name"),
                        rs.getString("species"),
                        rs.getString("breed"),
                        rs.getInt("age"),
                        rs.getString("description"),
                        rs.getString("adoption_status"));

                System.out.println("-------------------------");
            }

            con.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
