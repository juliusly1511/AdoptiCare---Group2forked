package vetchoices.update;

import database.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Scanner;

public class UpdatePetMedicalRecords {

    static Scanner input = new Scanner(System.in);

    public static void updatePetMedicalRecordsMenu() {

        int menu;

        do {

            System.out.println("\n===== Update Pet Medical Records Menu =====");

            System.out.println("\nWhich would you like to update?");

            System.out.println("1. Update All Pet Medical Records");
            System.out.println("2. Update Vaccine Name");
            System.out.println("3. Update Health Condition");
            System.out.println("4. Update Last Vaccination Date");
            System.out.println("5. Update Next Vaccination Schedule");
            System.out.println("6. Update Vaccination Status");
            System.out.println("7. Back");

            System.out.print("\nChoose: ");
            menu = input.nextInt();

            input.nextLine();

            switch (menu) {

                case 1:
                    updatePetMedicalRecords();
                    break;

                case 2:
                    updateVaccinationName();
                    break;

                case 3:
                    updateHealthCondition();
                    break;

                case 4:
                    updateLastVaccinationDate();
                    break;

                case 5:
                    updateNextVaccinationSchedule();
                    break;

                case 6:
                    updateVaccinationStatus();
                    break;

                case 7:
                    System.out.println("Going back...");
                    break;

                default:
                    System.out.println("Invalid option.");
            }

        } while (menu != 7);
    }

    public static void updatePetMedicalRecords() {

        try {
            System.out.println("Vaccination ID: ");
            int vaccinationId = input.nextInt();

            input.nextLine();

            System.out.println("Vaccination Name: ");
            String vaccinationName = input.nextLine();

            System.out.println("Health Condition: ");
            String healthCondition = input.nextLine();

            System.out.println("Last Vaccination Date (yyyy-MM-dd HH:mm:ss): ");
            String lastVaccination = input.nextLine();

            System.out.println("Next Vaccination Schedule (yyyy-MM-dd HH:mm:ss): ");
            String nextVaccination = input.nextLine();

            System.out.println("Vaccination Status: ");
            String vaccinationStatus = input.nextLine();

            Timestamp lastVaccinationDate = null;

            Timestamp nextVaccinationSchedule = null;

            if (!lastVaccination.trim().isEmpty()) {
                lastVaccinationDate = Timestamp.valueOf(lastVaccination);
            }

            if (!nextVaccination.trim().isEmpty()) {
                nextVaccinationSchedule = Timestamp.valueOf(nextVaccination);
            }

            Connection con = DbConnection.getConnection();

            String sql = "UPDATE pet_medical_records "
                    + "SET vaccine_name = ?, "
                    + "health_condition = ?, "
                    + "last_vaccination_date = ?, "
                    + "next_vaccination_schedule = ?, "
                    + "vaccination_status = ? "
                    + "WHERE vaccination_id = ?";

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, vaccinationName);
            pst.setString(2, healthCondition);
            if (lastVaccinationDate != null) {
                pst.setTimestamp(3, lastVaccinationDate);
            } else {
                pst.setNull(3, java.sql.Types.TIMESTAMP);
            }

            if (nextVaccinationSchedule != null) {
                pst.setTimestamp(4, nextVaccinationSchedule);
            } else {
                pst.setNull(4, java.sql.Types.TIMESTAMP);
            }
            pst.setString(5, vaccinationStatus);

            pst.setInt(6, vaccinationId);

            int rows = pst.executeUpdate();

            if (rows > 0) {
                System.out.println("Updated Successfully!");
            } else {
                System.out.println("Update failed.");
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public static void updateVaccinationName() {
        try {
            System.out.println("Vaccination ID: ");
            int vaccinationId = input.nextInt();

            input.nextLine();

            System.out.println("Vaccination Name: ");
            String vaccinationName = input.nextLine();

            Connection con = DbConnection.getConnection();

            String sql = "UPDATE pet_medical_records "
                    + "SET vaccine_name = ? "
                    + "WHERE vaccination_id = ?";

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, vaccinationName);
            pst.setInt(2, vaccinationId);

            int rows = pst.executeUpdate();

            if (rows > 0) {
                System.out.println("Updated Successfully!");
            } else {
                System.out.println("Update failed.");
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void updateHealthCondition() {

        try {
            System.out.println("Vaccination ID: ");
            int vaccinationId = input.nextInt();

            input.nextLine();

            System.out.println("Health Condition: ");
            String healthCondition = input.nextLine();

            Connection con = DbConnection.getConnection();

            String sql = "UPDATE pet_medical_records "
                    + "SET "
                    + "health_condition = ? "
                    + "WHERE vaccination_id = ?";

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, healthCondition);
            pst.setInt(2, vaccinationId);

            int rows = pst.executeUpdate();

            if (rows > 0) {
                System.out.println("Updated Successfully!");
            } else {
                System.out.println("Update failed.");
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void updateLastVaccinationDate() {

        try {
            System.out.println("Vaccination ID: ");
            int vaccinationId = input.nextInt();

            input.nextLine();

            System.out.println("Last Vaccination Date (yyyy-MM-dd HH:mm:ss): ");
            String lastVaccination = input.nextLine();

            Timestamp lastVaccinationDate = null;

            if (!lastVaccination.trim().isEmpty()) {
                lastVaccinationDate = Timestamp.valueOf(lastVaccination);
            }

            Connection con = DbConnection.getConnection();

            String sql = "UPDATE pet_medical_records "
                    + "SET "
                    + "last_vaccination_date = ? "
                    + "WHERE vaccination_id = ?";

            PreparedStatement pst = con.prepareStatement(sql);

            if (lastVaccinationDate != null) {
                pst.setTimestamp(1, lastVaccinationDate);
            } else {
                pst.setNull(1, java.sql.Types.TIMESTAMP);
            }

            pst.setInt(2, vaccinationId);

            int rows = pst.executeUpdate();

            if (rows > 0) {
                System.out.println("Updated Successfully!");
            } else {
                System.out.println("Update failed.");
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void updateNextVaccinationSchedule() {

        try {
            System.out.println("Vaccination ID: ");
            int vaccinationId = input.nextInt();

            input.nextLine();

            System.out.println("Next Vaccination Schedule (yyyy-MM-dd HH:mm:ss): ");
            String nextVaccination = input.nextLine();

            Timestamp nextVaccinationSchedule = null;

            if (!nextVaccination.trim().isEmpty()) {
                nextVaccinationSchedule = Timestamp.valueOf(nextVaccination);
            }

            Connection con = DbConnection.getConnection();

            String sql = "UPDATE pet_medical_records "
                    + "SET "
                    + "next_vaccination_schedule = ? "
                    + "WHERE vaccination_id = ?";

            PreparedStatement pst = con.prepareStatement(sql);

            if (nextVaccinationSchedule != null) {
                pst.setTimestamp(1, nextVaccinationSchedule);
            } else {
                pst.setNull(1, java.sql.Types.TIMESTAMP);
            }

            pst.setInt(2, vaccinationId);

            int rows = pst.executeUpdate();

            if (rows > 0) {
                System.out.println("Updated Successfully!");
            } else {
                System.out.println("Update failed.");
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public static void updateVaccinationStatus() {
        
        try {
            System.out.println("Vaccination ID: ");
            int vaccinationId = input.nextInt();

            input.nextLine();

            System.out.println("Vaccination Status: ");
            String vaccinationStatus = input.nextLine();

            Connection con = DbConnection.getConnection();

            String sql = "UPDATE pet_medical_records "
                    + "SET vaccine_name = ?, "
                    + "health_condition = ?, "
                    + "last_vaccination_date = ?, "
                    + "next_vaccination_schedule = ?, "
                    + "vaccination_status = ? "
                    + "WHERE vaccination_id = ?";

            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setString(1, vaccinationStatus);

            pst.setInt(2, vaccinationId);

            int rows = pst.executeUpdate();

            if (rows > 0) {
                System.out.println("Updated Successfully!");
            } else {
                System.out.println("Update failed.");
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
