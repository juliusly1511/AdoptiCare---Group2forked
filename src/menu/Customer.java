package menu;

import auth.Login;
import customerchoices.ViewAvailablePets;
import customerchoices.SubmitAdoptionRequest;
import customerchoices.SearchPetMenu;
import customerchoices.ViewRequestStatus;
import java.util.Scanner;

public class Customer {

    static Scanner input = new Scanner(System.in);

    public static void customerMenu() {
        
        System.out.println("\n🏡 Welcome to AdoptiCare, Customer! 🐾");
        
        int choice;
        
        do {
            System.out.println("\n===== 🏠 CUSTOMER MENU =====");
            
            System.out.println("[1] 🐕 View Available Pets");
            System.out.println("[2] 🔍 Search Pet");
            System.out.println("[3] ❤ Submit Adoption Request");
            System.out.println("[4] 📄 View Request Status");
            System.out.println("[5] ⏻ Logout");
            
            System.out.print("\n👉 Choose an option [1-5]: ");
            choice = input.nextInt();
            
            input.nextLine();
            
            switch (choice) {
                
                case 1:
                    ViewAvailablePets.viewAvailablePets();
                    break;
                    
                case 2:
                    SearchPetMenu.searchPetMenu();
                    break;
                    
                case 3:
                    SubmitAdoptionRequest.submitAdoptionRequest();
                    break;
                    
                case 4:
                    ViewRequestStatus.viewRequestStatus(Login.loggedInUserId);
                    
                case 5:
                    System.out.println("\n🏡 Thank you for using AdoptiCare. 🐶🐱");
                    
                    Login.loggedInUserId = -1;
                    
                    break;
                    
                default:
                    System.out.println("\n❌ Invalid option.");
            }
        } while (choice != 5);
    }
}
