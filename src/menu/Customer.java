package menu;

import java.util.Scanner;
import customermenu.*;

public class Customer {

    static Scanner input = new Scanner(System.in);

    public static void customerMenu() {
        
        System.out.println("\nWelcome Customer!");
        
        int choice;
        
        do {
            System.out.println("\n===== CUSTOMER MENU =====");
            
            System.out.println("1. View Available Pets");
            System.out.println("2. Search Pet");
            System.out.println("3. View Pet Details");
            System.out.println("4. Submit Adoption Request");
            System.out.println("5. Logout");
            
            System.out.print("Choose: ");
            choice = input.nextInt();
            
            input.nextLine();
            
            switch (choice) {
                
                case 1:
                    ViewAvailablePets.viewAvailablePets();
                    break;
                    
                case 2:
                    SearchPet.searchPet();
                    break;
                    
                case 3:
                    ViewPetDetails.viewPetDetails();
                    break;
                    
                case 4:
                    SubmitAdoptionRequest.submitAdoptionRequest();
                    break;
                    
                case 5:
                    System.out.println("Logging out...");
                    break;
                    
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 5);
    }
}
