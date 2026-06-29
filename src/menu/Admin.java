package menu;

import database.DBConnect;
import java.sql.*;
import java.util.Scanner;

public class Admin {

    static Scanner input = new Scanner(System.in);

    public static void adminMenu() {

        System.out.println("\nWelcome Administrator!");

        int choice;

        do {
            System.out.println("\n===== ADMIN MENU =====");

            System.out.println("1. Add Pet");
            System.out.println("2. View All Pets");
            System.out.println("3. Update Pet");
            System.out.println("4. Delete Pet");
            System.out.println("5. Search Pet");
            System.out.println("6. Archived Adopted Pet");
            System.out.println("7. View Archived Pets");
            System.out.println("8. Logout");

            System.out.print("Choose: ");
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    addPet();
                    break;

                case 2:
                    viewPets();
                    break;

                case 3:
                    updatePet();
                    break;

                case 4:
                    deletePet();
                    break;

                case 5:
                    searchPet();
                    break;

                case 6:
                    archivePet();
                    break;

                case 7:
                    viewArchivedPets();
                    break;

                case 8:
                    System.out.println("Logging out...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 8);
    }

    // =============== ADD PET ===============
    public static void addPet() {

        try {
            System.out.print("Pet Name: ");
            String name = input.nextLine();

            System.out.print("Species: ");
            String species = input.nextLine();

            System.out.print("Breed: ");
            String breed = input.nextLine();

            System.out.print("Age: ");
            int age = input.nextInt();
            input.nextLine();

            System.out.print("Health Condition: ");
            String healthCondition = input.nextLine();

            Connection con = DBConnect.getConnection();

            String sql
                    = "INSERT INTO "
                    + "(pet_name, species, breed, age, health_condition)"
                    + " VALUES (?, ?, ?, ?, ?)";

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, name);
            pst.setString(2, species);
            pst.setString(3, breed);
            pst.setInt(4, age);
            pst.setString(5, healthCondition);

            int rows = pst.executeUpdate();

            if (rows > 0) {
                System.out.println("Pet Added Successfully!");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // =============== VIEW/READ PETS ===============
    public static void viewPets() {
        try {
            Connection con = DBConnect.getConnection();

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

    // =============== SEARCH PET ===============
    public static void searchPet() {
        try {

            System.out.println("Enter Pet ID: ");
            int id = input.nextInt();
            input.nextLine();

            Connection con = DBConnect.getConnection();

            String sql = "SELECT * FROM pets WHERE pet_id = ?";

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                System.out.println("Name: " + rs.getString("pet_name"));

                System.out.println("Species: " + rs.getString("species"));

                System.out.println("Breed: " + rs.getString("breed"));

                System.out.println("Age: " + rs.getInt("age"));

            } else {
                System.out.println("Pet not found.");
            }

            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void updatePet() {
    }

    public static void deletePet() {
    }

    public static void archivePet() {
    }

    public static void viewArchivedPets() {
    }
}
