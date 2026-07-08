package adminchoices.read;

import database.DbConnection;
import java.sql.*;
import util.AgeConverter;

public class ViewPets {

    public static void viewPets() {
        try {
            Connection con = DbConnection.getConnection();

            String sql = "SELECT * FROM pets WHERE archived = 0";

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);

            System.out.println("\n===== 🐾 PET LIST =====");

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

            while (rs.next()) {

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
                
            }

            con.close();
            rs.close();
            
        } catch (SQLException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}
