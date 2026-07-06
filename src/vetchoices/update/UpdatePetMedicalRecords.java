package vetchoices.update;

import database.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.Scanner;

public class UpdatePetMedicalRecords {

    static Scanner input = new Scanner(System.in);

    // ================= MENU =================
    public static void updatePetMedicalRecordsMenu() {

        int menu;

        do {

            System.out.println("\n===== UPDATE PET MEDICAL RECORDS =====");
            System.out.println("1. Update Full Vaccination Record");
            System.out.println("2. Update Vaccine Name");
            System.out.println("3. Update Health Condition");
            System.out.println("4. Update Last Vaccination Date");
            System.out.println("5. Update Next Vaccination Schedule");
            System.out.println("6. Update Vaccination Status");
            System.out.println("7. Update Diet");
            System.out.println("8. Update Vitamins");
            System.out.println("9. Back");

            System.out.print("\nChoose: ");
            menu = input.nextInt();
            input.nextLine();

            switch (menu) {
                case 1:
                    updateFullRecord();
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
                    updateDiet();
                    break;

                case 8:
                    updateVitamins();
                    break;

                case 9:
                    System.out.println("Going back...");
                    break;

                default:
                    System.out.println("Invalid option.");
            }

        } while (menu != 9);
    }

    // ================= SHOW RECORDS =================
    private static void showVaccinations(Connection con, int petId) throws SQLException {

        String sql = "SELECT * FROM pet_medical_records WHERE pet_id=?";

        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, petId);

        ResultSet rs = pst.executeQuery();

        System.out.println("\n===== PET MEDICAL RECORDS =====");
        System.out.printf(
                "%-15s %-7s %-18s %-18s %-20s %-20s %-15s %-15s %-15s%n",
                "Vaccination ID",
                "Pet ID",
                "Vaccine Name",
                "Health Condition",
                "Last Date",
                "Next Schedule",
                "Status",
                "Diet",
                "Vitamins"
        );

        while (rs.next()) {

            String last = String.valueOf(rs.getTimestamp("last_vaccination_date"));
            String next = String.valueOf(rs.getTimestamp("next_vaccination_schedule"));

            System.out.printf(
                    "%-15d %-10d %-15s %-20s %-25s %-25s %-15s %-15s %-15s%n",
                    rs.getInt("vaccination_id"),
                    rs.getInt("pet_id"),
                    rs.getString("vaccine_name"),
                    rs.getString("health_condition"),
                    last,
                    next,
                    rs.getString("vaccination_status"),
                    rs.getString("diet"),
                    rs.getString("vitamins")
            );
        }

        rs.close();
        pst.close();
    }

    // ================= EXISTS CHECK =================
    private static boolean exists(Connection con, int id) throws SQLException {

        String sql = "SELECT 1 FROM pet_medical_records WHERE vaccination_id=?";

        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, id);

        ResultSet rs = pst.executeQuery();

        boolean found = rs.next();

        rs.close();
        pst.close();

        return found;
    }

    // ================= FULL UPDATE =================
    public static void updateFullRecord() {

        try {

            Connection con = DbConnection.getConnection();

            System.out.print("Enter Pet ID: ");
            int petId = input.nextInt();

            input.nextLine();

            showVaccinations(con, petId);

            System.out.print("Enter Vaccination ID: ");
            int vaccinationId = input.nextInt();

            input.nextLine();

            if (!exists(con, vaccinationId)) {
                System.out.println("Invalid Vaccination ID.");
                return;
            }

            System.out.print("Vaccine Name: ");
            String vaccineName = input.nextLine();

            System.out.print("Health Condition: ");
            String health = input.nextLine();

            System.out.print("Last Vaccination Date (yyyy-MM-dd HH:mm:ss): ");
            String last = input.nextLine();

            Timestamp lastVaccination = null;

            System.out.print("Next Vaccination Schedule (yyyy-MM-dd HH:mm:ss): ");
            String next = input.nextLine();

            Timestamp nextVaccination = null;

            System.out.print("Status: ");
            String status = input.nextLine();

            System.out.print("Diet: ");
            String diet = input.nextLine();

            System.out.print("Vitamins: ");
            String vitamins = input.nextLine();

            if (!last.trim().isEmpty()) {
                lastVaccination = Timestamp.valueOf(last);
            }

            if (!next.trim().isEmpty()) {
                nextVaccination = Timestamp.valueOf(next);
            }

            String sql = "UPDATE pet_medical_records SET "
                    + "vaccine_name=?, health_condition=?, last_vaccination_date=?, "
                    + "next_vaccination_schedule=?, vaccination_status=?, diet=?, vitamins=? "
                    + "WHERE vaccination_id=?";

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, vaccineName);
            pst.setString(2, health);

            if (lastVaccination != null) {
                pst.setTimestamp(3, lastVaccination);
            } else {
                pst.setNull(3, Types.TIMESTAMP);
            }

            if (nextVaccination != null) {
                pst.setTimestamp(4, nextVaccination);
            } else {
                pst.setNull(4, Types.TIMESTAMP);
            }
            pst.setString(5, status);
            pst.setString(6, diet);
            pst.setString(7, vitamins);
            pst.setInt(8, vaccinationId);

            pst.executeUpdate();

            System.out.println("Updated successfully!");

            pst.close();
            con.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // ================= SINGLE FIELD UPDATES =================
    public static void updateVaccinationName() {
        updateField("vaccine_name", "Vaccination Name");
    }

    public static void updateHealthCondition() {
        updateField("health_condition", "Health Condition");
    }

    public static void updateVaccinationStatus() {
        updateField("vaccination_status", "Status");
    }

    public static void updateDiet() {
        updateField("diet", "Diet");
    }

    public static void updateVitamins() {
        updateField("vitamins", "Vitamins");
    }

    public static void updateField(String column, String label) {

        try {

            Connection con = DbConnection.getConnection();

            System.out.println("\nEnter Pet ID: ");
            int petId = input.nextInt();

            showVaccinations(con, petId);

            System.out.print("Vaccination ID: ");
            int id = input.nextInt();

            input.nextLine();

            System.out.print(label + ": ");
            String value = input.nextLine();

            String sql = "UPDATE pet_medical_records SET " + column + "= ? WHERE vaccination_id=?";

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, value);
            pst.setInt(2, id);

            pst.executeUpdate();

            System.out.println("Updated successfully!");

            pst.close();
            con.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateLastVaccinationDate() {

        try {

            Connection con = DbConnection.getConnection();

            System.out.print("\nEnter Pet ID: ");
            int petId = input.nextInt();

            input.nextLine();

            showVaccinations(con, petId);

            System.out.print("Vaccination ID: ");
            int id = input.nextInt();

            System.out.print("Last Vaccination Date (yyyy-MM-dd HH-mm-ss): ");
            String lastVaccination = input.nextLine();

            Timestamp lastVaccinationDate = null;

            if (!lastVaccination.trim().isEmpty()) {
                lastVaccinationDate = Timestamp.valueOf(lastVaccination);
            }

            String queryDate
                    = "UPDATE pet_medical_records "
                    + "SET last_vaccination_date = ? "
                    + "WHERE vaccination_id = ?";

            PreparedStatement pstDate = con.prepareStatement(queryDate);

            if (lastVaccinationDate != null) {
                pstDate.setTimestamp(1, lastVaccinationDate);
            } else {
                pstDate.setNull(1, java.sql.Types.TIMESTAMP);
            }

            pstDate.setInt(2, id);

            int rows = pstDate.executeUpdate();

            if (rows > 0) {
                System.out.println("\nLast vaccination date updated successfully!");
            } else {
                System.out.println("\nUpdate failed. Invalid Vaccination ID.");
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void updateNextVaccinationSchedule() {

        try {

            Connection con = DbConnection.getConnection();

            System.out.print("\nEnter Pet ID: ");
            int petId = input.nextInt();

            showVaccinations(con, petId);

            System.out.print("Vaccination ID: ");
            int id = input.nextInt();

            System.out.print("Last Vaccination Date (yyyy-MM-dd HH-mm-ss): ");
            String lastVaccination = input.nextLine();

            Timestamp lastVaccinationDate = null;

            if (!lastVaccination.trim().isEmpty()) {
                lastVaccinationDate = Timestamp.valueOf(lastVaccination);
            }

            String queryDate
                    = "UPDATE pet_medical_records "
                    + "SET next_vaccination_schedule = ? "
                    + "WHERE vaccination_id = ?";

            PreparedStatement pstDate = con.prepareStatement(queryDate);

            if (lastVaccinationDate != null) {
                pstDate.setTimestamp(1, lastVaccinationDate);
            } else {
                pstDate.setNull(1, java.sql.Types.TIMESTAMP);
            }

            pstDate.setInt(2, id);

            int rows = pstDate.executeUpdate();

            if (rows > 0) {
                System.out.println("\nNext vaccination schedule updated successfully!");
            } else {
                System.out.println("\nUpdate failed. Invalid Vaccination ID.");
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
