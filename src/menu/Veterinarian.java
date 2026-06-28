
package menu;

import java.util.Scanner;


public class Veterinarian {
    
    static Scanner input = new Scanner(System.in);
    
    public static void veterinarianMenu() {
        
        System.out.println("\nWelcome Veterinarian!");
        
        int choice;
        
        do {
            System.out.println("\n===== VETERINARIAN MENU =====");
            
            System.out.println("1. View Pets");
            System.out.println("2. Search Pet");
            System.out.println("3. Update Vaccination");
            System.out.println("4. View Vaccinated Pets");
            System.out.println("5. View Vaccination Schedule");
            System.out.println("6. Logout");
            
            System.out.print("Choose: ");
            choice = input.nextInt();
            
            input.nextLine();
            
            switch (choice) {
                
                case 1:
                    viewPets();
                    break;
                    
                case 2:
                    searchPet();
                    break;
                    
                case 3:
                    updateVaccination();
                    break;
                    
                case 4:
                    viewVaccinatedPets();
                    break;
                    
                case 5:
                    viewVaccinationSchedule();
                    break;
                    
                case 6:
                    System.out.println("Logging out...");
                    break;
                    
                default:
                    System.out.println("Invalid choice.");
            }
            
        } while (choice != 6);
    }
    
    public static void viewPets() {
        
    }
    
    public static void searchPet() {
        
    }
    
    public static void updateVaccination() {
        
    }
    
    public static void viewVaccinatedPets() {
        
    }
    
    public static void viewVaccinationSchedule() {
        
    }
}
