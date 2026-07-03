
package menu;

import vetchoices.read.SearchPet;
import vetchoices.read.ViewPetMedicalRecords;
import vetchoices.read.ViewVaccinatedPets;
import vetchoices.read.ViewPets;
import vetchoices.create.AddVaccination;
import java.util.Scanner;
import vetchoices.update.UpdatePetMedicalRecords;


public class Veterinarian {
    
    static Scanner input = new Scanner(System.in);
    
    public static void veterinarianMenu() {
        
        System.out.println("\nWelcome Veterinarian!");
        
        int choice;
        
        do {
            System.out.println("\n===== VETERINARIAN MENU =====");
            
            System.out.println("\n1. Add Vaccination");
            System.out.println("2. Search Pet");
            System.out.println("3. View Pets");
            System.out.println("4. View Vaccinated Pets");
            System.out.println("5. View Vaccination Schedule");
            System.out.println("6. Update Health Condition");
            System.out.println("7. Update Vaccination Status");
            System.out.println("8. Update Vaccination Schedule");
            System.out.println("9. Logout");
            
            System.out.print("Choose: ");
            choice = input.nextInt();
            
            input.nextLine();
            
            switch (choice) {
                
                case 1:
                    AddVaccination.addVaccination();
                    break;
                    
                case 2:
                    SearchPet.searchPet();
                    break;
                    
                case 3:
                    ViewPets.viewPets();
                    break;
                    
                case 4:
                    ViewVaccinatedPets.viewVaccinatedPets();
                    break;
                    
                case 5:
                    ViewPetMedicalRecords.viewPetMedicalRecords();
                    break;
                    
                case 6:
                    UpdatePetMedicalRecords.updatePetMedicalRecords();
                    break;
                    
                case 7:
                    System.out.println("Logged out successfully!");
                    break;
                    
                default:
                    System.out.println("Invalid choice.");
            }
            
        } while (choice != 9);
    }
}
