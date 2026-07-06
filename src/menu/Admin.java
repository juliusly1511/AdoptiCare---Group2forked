package menu;

import adminchoices.delete.DeletePet;
import adminchoices.update.ApproveRequest;
import adminchoices.update.ArchivePet;
import adminchoices.update.UpdatePet;
import adminchoices.read.ViewRequests;
import adminchoices.read.SearchPet;
import adminchoices.read.ViewPets;
import adminchoices.read.ViewArchivedPets;
import adminchoices.create.AddPet;
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
            System.out.println("6. Archive Pet");
            System.out.println("7. View Archived Pets");
            System.out.println("8. View Adoption Requests");
            System.out.println("10. Logout");
            
            System.out.print("\nChoose: ");
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    AddPet.addPet();
                    break;

                case 2:
                    ViewPets.viewPets();
                    break;

                case 3:
                    UpdatePet.updatePetMenu();
                    break;

                case 4:
                    DeletePet.deletePet();
                    break;

                case 5:
                    SearchPet.searchPet();
                    break;

                case 6:
                    ArchivePet.archivePet();
                    break;

                case 7:
                    ViewArchivedPets.viewArchivedPets();
                    break;

                case 8:
                    ViewRequests.viewRequests();
                    break;
                    
                case 10:
                    System.out.println("Logging out...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 10);
    }
}
