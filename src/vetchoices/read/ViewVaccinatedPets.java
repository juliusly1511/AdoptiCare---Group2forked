package vetchoices.read;

import database.DbConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ViewVaccinatedPets {

    public static void viewVaccinatedPets() {

        Scanner input = new Scanner(System.in);

        try {

            Connection con = DbConnection.getConnection();

            String sql = "SELECT * "
                    + "FROM pet_medical_records "
                    + "WHERE vaccination_status = 'Fully Vaccinated' OR vaccination_status = 'Partially Vaccinated'";

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);

            System.out.printf(
                    "%-15s %-10s %-15s %-20s %-25s %-25s %-15s%n",
                    "Vaccination ID",
                    "Pet ID",
                    "Vaccine Name",
                    "Health Condition",
                    "Last Shot",
                    "Next Shot",
                    "Status"
            );
            System.out.println("--------------------------------------------------------------------------------");

            if (rs.next()) {

                while (rs.next()) {
                    System.out.printf(
                            "%-5d %-10d %-15s %-20s %-25s %-25s %-15s%n",
                            rs.getInt("vaccination_id"),
                            rs.getInt("pet_id"),
                            rs.getString("vaccine_name"),
                            rs.getString("health_condition"),
                            rs.getTimestamp("last_vaccination_date"),
                            rs.getTimestamp("next_vaccination_schedule"),
                            rs.getString("vaccination_status")
                    );

                    System.out.println("--------------------------------------------------------------------------------");
                }
            } else {
                System.out.println("Vaccination status not found.");
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
