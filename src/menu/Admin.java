package menu;

import adminchoices.delete.DeletePet;
import adminchoices.update.UpdatePet;
import adminchoices.read.ViewRequests;
import adminchoices.read.SearchPet;
import adminchoices.read.ViewPets;
import adminchoices.create.AddPet;
import adminchoices.update.ArchiveMenu;
import java.util.Scanner;

public class Admin {

    static Scanner input = new Scanner(System.in);

    public static void adminMenu() {

        System.out.println("\n🏡 Welcome to AdoptiCare, Administrator! 🐾");

        int choice;

        do {
            System.out.println("\n===== 👑 ADMIN MENU =====");

            System.out.println("[1] ➕ Add Pet");
            System.out.println("[2] 🐾 View All Pets");
            System.out.println("[3] ✏ Update Pet");
            System.out.println("[4] 🗑 Delete Pet");
            System.out.println("[5] 🔍 Search Pet");
            System.out.println("[6] 📦 Archive Pets Menu");
            System.out.println("[7] 📋 View Adoption Requests");
            System.out.println("[8] ⏻ Logout");
            
            System.out.print("\n👉 Choose an option [1-8]: ");
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
                    SearchPet.searchPetMenu();
                    break;

                case 6:
                    ArchiveMenu.archivedMenu();
                    break;

                case 7:
                    ViewRequests.viewRequests();
                    break;
                            
                    
                case 8:
                    System.out.println("\n✅ Logout Successfully!");
                    break;

                default:
                    System.out.println("\n❌ Invalid option.");
            }

        } while (choice != 8);
    }
}
