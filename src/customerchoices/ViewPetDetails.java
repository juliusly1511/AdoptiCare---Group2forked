package customerchoices;

import database.DbConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ViewPetDetails {

    public static void viewPetDetails(int petId) {

        Scanner input = new Scanner(System.in);

        try {

            System.out.println("\n===== PET DETAILS =====");

            Connection con = DbConnection.getConnection();

            String sql
                    = "SELECT p.*, pm.vaccine_name, pm.health_condition, "
                    + "pm.last_vaccination_date, pm.next_vaccination_schedule, "
                    + "pm.vaccination_status "
                    + "FROM pets p "
                    + "LEFT JOIN pet_medical_records pm "
                    + "ON p.pet_id = pm.pet_id "
                    + "WHERE p.archived = 0";

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);

            System.out.printf(
                    "%-8s %-15s %-12s %-5s %-8s %-15s %-25s%n",
                    "Pet ID", "Pet Name", "Species", "Age", "Gender", "Breed", "Description"
            );

            System.out.println("--------------------------------------------------------------------------------");

            if (rs.next()) {

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

                System.out.println("\n===== " + rs.getString("pet_name") + " MEDICAL RECORD =====\n");

                System.out.printf("%-30s: %s%n", "Vaccine Name", rs.getString("vaccine_name"));
                System.out.printf("%-30s: %s%n", "Health Condition", rs.getString("health_condition"));
                System.out.printf("%-30s: %s%n", "Last Vaccination Date", rs.getTimestamp("last_vaccination_schedule"));
                System.out.printf("%-30s: %s%n", "Next Vaccination Schedule", rs.getTimestamp("next_vaccination_schedule"));
                System.out.printf("%-30s: %s%n", "Vaccination Status", rs.getString("vaccination_status"));

            } else {
                System.out.println("Pet not found.");
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
