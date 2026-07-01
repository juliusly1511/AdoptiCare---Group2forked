package adminchoices.read;

import database.DbConnection;
import java.sql.*;

public class ViewPets {

        public static void viewPets() {
        try {
            Connection con = DbConnection.getConnection();

            Statement st = con.createStatement();

            ResultSet rs
                    = st.executeQuery(
                            "SELECT * FROM pets WHERE archived = FALSE");

            System.out.println("\n===== PET LIST =====");

            while (rs.next()) {

                System.out.println("ID: " + rs.getInt("pet_id"));

                System.out.println("Name: " + rs.getString("pet_name"));

                System.out.println("Species: " + rs.getString("species"));

                System.out.println("Breed: " + rs.getString("breed"));

                System.out.println("Age: " + rs.getInt("age"));

                System.out.println("Health Condition: " + rs.getString("health_condition"));

                System.out.println("Vaccination Status: " + rs.getString("vaccination_status"));

                System.out.println("Adoption Status: " + rs.getString("adoption_status"));

                System.out.println("-------------------------");

                con.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
