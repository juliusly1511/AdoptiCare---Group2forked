package vetchoices.create;

import database.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Scanner;

public class AddPetMedicalRecords {

    public static void addPetMedicalRecords() {

        Scanner input = new Scanner(System.in);

        try {

            System.out.print("\nPet ID: ");
            int petId = input.nextInt();

            input.nextLine();
            
            Connection con = DbConnection.getConnection();

            String sql = "INSERT INTO vaccinations "
                    + "(pet_id, vaccine_name, last_vaccination_date, next_vaccination_schedule, vaccination_status, diet, vitamins) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pst = con.prepareStatement(sql);
            
            while (true) {

                System.out.print("\nVaccine Name (type 'done' to finish): ");
                String vaccineName = input.nextLine();

                if (vaccineName.equalsIgnoreCase("done")) {
                    break;
                }

                System.out.print("Health Condition: ");
                String healthCondition = input.nextLine();

                System.out.print("Last Vaccination Date (yyyy-mm-dd hh:mm:ss): ");
                Timestamp lastDate = Timestamp.valueOf(input.nextLine());

                System.out.print("Next Vaccination Schedule (yyyy-mm-dd hh:mm:ss): ");
                Timestamp nextDate = Timestamp.valueOf(input.nextLine());

                System.out.print("Vaccination Status: ");
                String status = input.nextLine();

                System.out.print("Diet: ");
                String diet = input.nextLine();

                System.out.print("Vitamins: ");
                String vitamins = input.nextLine();

                pst.setInt(1, petId);
                pst.setString(2, vaccineName);
                pst.setString(3, healthCondition);
                pst.setTimestamp(4, lastDate);
                pst.setTimestamp(5, nextDate);
                pst.setString(6, status);
                pst.setString(7, diet);
                pst.setString(8, vitamins);

                pst.executeUpdate();

                System.out.println("✓ Vaccine added successfully.");
            }

            System.out.println("\nAll vaccination records saved.");

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
