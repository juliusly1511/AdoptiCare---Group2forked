package adopticare;


import auth.AdminLogin;
import auth.CustomerResetPassword;
import auth.Login;
import auth.Register;
import auth.VetLogin;
import java.util.Scanner;
import menu.Admin;
import menu.Customer;
import menu.Veterinarian;

public class AdoptiCareMain {

    static Scanner input = new Scanner(System.in);

    public static void onSystem() {

        int choice;

        do {
            System.out.println("\n🏡 Welcome to AdoptiCare! 🐶🐱 ");

            System.out.println("[1] 👤 Customer Login");
            System.out.println("[2] 🔐 Reset Passoword (Customer)");
            System.out.println("[3] 👑 Admin Login");
            System.out.println("[4] 👨‍⚕ Veterinarian Login");
            System.out.println("[5] 📝 Register");
            System.out.println("[6] ⏻ Exit");

            System.out.print("\n👉 Choose an option [1-6]: ");
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {

                case 1:
                    handleCustomerLogin();
                    break;
                    
                case 2:
                    CustomerResetPassword.resetPassword();
                    break;
                    
                case 3:
                    handleAdminLogin();
                    break;
                    
                case 4:
                    handleVetLogin();
                    break;
                    
                case 5:
                    Register.registerCustomer();
                    break;

                case 6:
                    return;

                default:
                    System.out.println("\n❌ Invalid choice.");
            }
        } while (choice != 6);
    }

    //======================
    //CUSTOMER VALIDATION
    //======================
    public static void handleCustomerLogin() {
        //================================================
        //Data Validation in where the user will proceed
        //================================================
        String role = Login.login();
        
        if (role == null) {
            System.out.println("\n❌ Invalid username or password.");
            
            return;
        }
        
        switch (role) {
            case "Customer":
                Customer.customerMenu();
                break;
                
            default:
                System.out.println("\n❌ Invalid role.");
        }
    }

    //========================
    //ADMIN VALIDATION
    //========================
    public static void handleAdminLogin() {
        
       String role = AdminLogin.adminLogin();
       
       if (role == null) {
           System.out.println("\n❌ Invalid username or password.");
           
           return;
       }
       
       switch (role) {
           case "Administrator":
               Admin.adminMenu();
               
           default:
               System.out.println("\n❌ Invalid role.");
       }
    }

    //==========================
    //VETERINARIAN VALIDATION
    //==========================
    public static void handleVetLogin() {
        
        String role = VetLogin.vetLogin();
        
        if (role == null) {
            System.out.println("\n❌ Invalid username or password");
            
            return;
        }
        
        switch (role) {
            case "Veterinarian":
                Veterinarian.veterinarianMenu();
                
            default:
                System.out.println("\n❌ Invalid role.");
        }
    }
}
