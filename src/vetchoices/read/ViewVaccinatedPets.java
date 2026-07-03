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
                    + "WHERE vaccination_status = 'Fully Vaccinated'";

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {

                while (rs.next()) {
                    System.out.println("Vaccination ID: " + rs.getInt("vaccination_id"));

                    System.out.println("Pet_ID: " + rs.getInt("pet_id"));

                    System.out.println("Vaccine Name: " + rs.getString("vaccine_name"));

                    System.out.println("Health Condition: " + rs.getString("health_condition"));

                    System.out.println("Last Vaccination Schedule: " + rs.getTimestamp("last_vaccination_date"));

                    System.out.println("Next Vaccination Schedule: " + rs.getTimestamp("next_vaccination_schedule"));

                    System.out.println("Vaccination Status: " + rs.getString("vaccination_status"));
                }
            } else {
                System.out.println("Vaccination status not found.");
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
