package menu;

import java.util.Scanner;

public class Customer {

    static Scanner input = new Scanner(System.in);

    public static void customerMenu() {
        
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
                    viewAvailablePets();
                    break;
                    
                case 2:
                    searchPet();
                    break;
                    
                case 3:
                    viewPetDetails();
                    break;
                    
                case 4:
                    submitAdoptionRequest();
                    break;
                    
                case 5:
                    System.out.println("Logging out...");
                    break;
                    
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 5);
    }
    
    public static void viewAvailablePets(){
        
    }
    
    public static void searchPet(){
        
    }
    
    public static void viewPetDetails(){
        
    }
    
    public static void submitAdoptionRequest() {
        
    }
}
