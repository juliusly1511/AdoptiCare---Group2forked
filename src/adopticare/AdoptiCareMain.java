package adopticare;

import auth.Register;
import auth.Login;
import java.util.Scanner;

public class AdoptiCareMain {

    static Scanner input = new Scanner(System.in);

    public static void startSystem() {

        int choice;

        do {
            System.out.println("\n===== ADOPTICARE =====");

            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");

            System.out.println("Choose: ");
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {

                case 1:
                    handleLogin();
                    break;

                case 2:
                    Register.registerCustomer();
                    break;

                case 3:
                    System.out.println("Goodbye have a good day!");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 3);
    }

    public static void handleLogin() {

        String role = Login.login();

        if (role == null) {
            System.out.println("Invalid Login.");
        } else if (role.equals("Administrator")) {
            adminMenu();
        } else if (role.equals("Veterinarian")) {
            veterinarianMenu();
        } else if (role.equals("Customer")) {
            customerMenu();
        }
    }

    // =============== MENU ===============
    
    public static void adminMenu() {
        System.out.println("ADMIN MENU (CRUD HERE)");
    }

    public static void veterinarianMenu() {
        System.out.println("VETERINARIAN MENU (VACCINATION)");
    }

    public static void customerMenu() {
        System.out.println("CUSTOMER MENU (VIEW PETS)");
    }
}
