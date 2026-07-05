package adminchoices.update;

import database.DbConnection;
import java.sql.*;
import java.util.Scanner;

public class UpdatePet {

    static Scanner input = new Scanner(System.in);
    
    public static void updatePetMenu() {
        
        int menu;
        
        do {
            
            System.out.println("\nWhich pet details would you like to update?");
            
            System.out.println("1. Update All Pet Details");
            System.out.println("2. Update Pet Name");
            System.out.println("3. Update Pet Gender");
            System.out.println("4. Update Pet Age");
            System.out.println("5. Update Pet Species");
            System.out.println("6. Update Pet Breed");
            System.out.println("7. Update Pet Description");
            System.out.println("8. Back");
            
            System.out.print("Choose: ");
            menu = input.nextInt();
            
            input.nextLine();
            
            switch (menu) {
                
                case 1:
                    updatePet();
                    break;
                    
                case 2:
                    updatePetName();
                    break;
                    
                case 3:
                    updatePetGender();
                    break;
                    
                case 4:
                    updatePetAge();
                    break;
                    
                case 5:
                    updatePetSpecies();
                    break;
                    
                case 6:
                    updatePetBreed();
                    break;
                    
                case 7:
                    updatePetDescription();
                    break;
                    
                case 8:
                    System.out.println("Going back...");
                    break;
                    
                default:
                    System.out.println("Invalid option.");
            }
            
        } while (menu != 8);
    }
    
    public static void updatePet() {
        // switch cases
        
        System.out.println("===== UPDATE PET DETAILS =====");
        
        try {
            
            System.out.print("Enter Pet ID: ");
            int petId = input.nextInt();
            
            input.nextLine();
            
            System.out.print("Pet Name: ");
            String petName = input.nextLine();
            
            System.out.print("Gender: ");
            String gender = input.nextLine();
            
            System.out.print("Age: ");
            int age = input.nextInt();
            
            input.nextLine();
            
            System.out.print("Species: ");
            String species = input.nextLine();
            
            System.out.print("Breed: ");
            String breed = input.nextLine();
            
            System.out.print("Description: ");
            String desc = input.nextLine();
            
            Connection con = DbConnection.getConnection();
            
            String queryPet = 
                    "UPDATE pets"
                    + "SET pet_name = ?,"
                    + "gender = ?, "
                    + "age = ?, "
                    + "species = ?, "
                    + "breed = ?, "
                    + "description = ? "
                    + "WHERE pet_id = ?";
            
            PreparedStatement pst = con.prepareStatement(queryPet);
            
            pst.setString(1, petName);
            pst.setString(2, gender);
            pst.setInt(3, age);
            pst.setString(4, species);
            pst.setString(5, breed);
            pst.setString(6, desc);
            pst.setInt(7, petId);
            
            int rows = pst.executeUpdate();
            
            if (rows > 0) {
                System.out.println("Pet updated successfully.");
            } else {
                System.out.println("Pet not found.");
            }
            
            con.close();
            
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public static void updatePetName() {
        
        try {
            
            System.out.println("\n===== UPDATE PET NAME =====");
            
            System.out.print("Enter Pet ID: ");
            int petId = input.nextInt();
            
            input.nextLine();
            
            System.out.print("Pet Name: ");
            String petName = input.nextLine();
            
            Connection con = DbConnection.getConnection();
            
            String queryName = 
                    "UPDATE pets "
                    + "SET pet_name = ? "
                    + "WHERE pet_id = ?";
            
            PreparedStatement pst = con.prepareStatement(queryName);
            
            pst.setString(1, petName);
            pst.setInt(2, petId);
            
            int rows = pst.executeUpdate();
            
            if (rows > 0) {
                System.out.println("Pet updated successfully.");
            } else {
                System.out.println("Pet not found.");
            }
            
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public static void updatePetGender() {
        
        try {
            
            System.out.println("\n===== UPDATE PET GENDER =====");
            
            System.out.print("Enter Pet ID: ");
            int petId = input.nextInt();
            
            input.nextLine();
            
            System.out.print("Pet Gender: ");
            String gender = input.nextLine();
            
            Connection con = DbConnection.getConnection();
            
            String queryName = 
                    "UPDATE pets "
                    + "SET gender = ? "
                    + "WHERE pet_id = ?";
            
            PreparedStatement pst = con.prepareStatement(queryName);
            
            pst.setString(1, gender);
            pst.setInt(2, petId);
            
            int rows = pst.executeUpdate();
            
            if (rows > 0) {
                System.out.println("Pet updated successfully.");
            } else {
                System.out.println("Pet not found.");
            }
            
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public static void updatePetAge() {
        
        try {
            
            System.out.println("\n===== UPDATE PET AGE =====");
            
            System.out.print("Enter Pet ID: ");
            int petId = input.nextInt();
            
            input.nextLine();
            
            System.out.print("Pet Age: ");
            int age = input.nextInt();
            
            Connection con = DbConnection.getConnection();
            
            String queryName = 
                    "UPDATE pets "
                    + "SET age = ? "
                    + "WHERE pet_id = ?";
            
            PreparedStatement pst = con.prepareStatement(queryName);
            
            pst.setInt(1, age);
            pst.setInt(2, petId);
            
            int rows = pst.executeUpdate();
            
            if (rows > 0) {
                System.out.println("Pet updated successfully.");
            } else {
                System.out.println("Pet not found.");
            }
            
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public static void updatePetSpecies() {
        
        try {
            
            System.out.println("\n===== UPDATE PET SPECIES =====");
            
            System.out.print("Enter Pet ID: ");
            int petId = input.nextInt();
            
            input.nextLine();
            
            System.out.print("Pet Species: ");
            String species = input.nextLine();
            
            Connection con = DbConnection.getConnection();
            
            String queryName = 
                    "UPDATE pets "
                    + "SET species = ? "
                    + "WHERE pet_id = ?";
            
            PreparedStatement pst = con.prepareStatement(queryName);
            
            pst.setString(1, species);
            pst.setInt(2, petId);
            
            int rows = pst.executeUpdate();
            
            if (rows > 0) {
                System.out.println("Pet updated successfully.");
            } else {
                System.out.println("Pet not found.");
            }
            
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public static void updatePetBreed() {
        
        try {
            
            System.out.println("\n===== UPDATE PET BREED =====");
            
            System.out.print("Enter Pet ID: ");
            int petId = input.nextInt();
            
            input.nextLine();
            
            System.out.print("Pet Breed: ");
            String breed = input.nextLine();
            
            Connection con = DbConnection.getConnection();
            
            String queryName = 
                    "UPDATE pets "
                    + "SET breed = ? "
                    + "WHERE pet_id = ?";
            
            PreparedStatement pst = con.prepareStatement(queryName);
            
            pst.setString(1, breed);
            pst.setInt(2, petId);
            
            int rows = pst.executeUpdate();
            
            if (rows > 0) {
                System.out.println("Pet updated successfully.");
            } else {
                System.out.println("Pet not found.");
            }
            
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public static void updatePetDescription() {
        
        try {
            
            System.out.println("\n===== UPDATE PET DESCRIPTION =====");
            
            System.out.print("Enter Pet ID: ");
            int petId = input.nextInt();
            
            input.nextLine();
            
            System.out.print("Pet Description: ");
            String desc = input.nextLine();
            
            Connection con = DbConnection.getConnection();
            
            String queryName = 
                    "UPDATE pets "
                    + "SET desc = ? "
                    + "WHERE pet_id = ?";
            
            PreparedStatement pst = con.prepareStatement(queryName);
            
            pst.setString(1, desc);
            pst.setInt(2, petId);
            
            int rows = pst.executeUpdate();
            
            if (rows > 0) {
                System.out.println("Pet updated successfully.");
            } else {
                System.out.println("Pet not found.");
            }
            
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
