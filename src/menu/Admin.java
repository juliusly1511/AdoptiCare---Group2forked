package menu;

import database.DBConnect;
import java.sql.*;
import java.util.Scanner;

public class Admin {

    static Scanner input = new Scanner(System.in);

    public static void adminMenu() {

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

    public static void addPet() {
        
        try {
            System.out.println("Pet Name: ");
            String name = input.nextLine();
            
            System.out.println("Species: ");
            String species = input.nextLine();
            
            System.out.println("Breed: ");
            String breed = input.nextLine();
            
            System.out.println("Age: ");
            int age = input.nextInt();
            input.nextLine();
            
            System.out.println("Health Condition: ");
            String healthCondition = input.nextLine();
            
            System.out.println("Vaccination Status");
            String vaccinationStatus = input.nextLine();
            
            Connection con = DBConnect.getConnection();
            
            String sql = 
                    "INSERT INTO "
                    + "(pet_name, species, breed, age,"
                    + "health_condition, vaccination_status)"
                    + " VALUES (?, ?, ?, ?, ?, ?)";
            
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setString(1, name);
            pst.setString(2, species);
            pst.setString(3, breed);
            pst.setInt(4, age);
            pst.setString(5, healthCondition);
            pst.setString(6, vaccinationStatus);
            
            int rows = pst.executeUpdate();
            
            if(rows > 0) {
                System.out.println("Pet Added Successfully!");
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void viewPets() {
    }

    public static void updatePet() {
    }

    public static void deletePet() {
    }

    public static void searchPet() {
    }

    public static void archivePet() {
    }

    public static void viewArchivedPets() {
    }
}
