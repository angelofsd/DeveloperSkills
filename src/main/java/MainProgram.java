import java.util.Scanner;

public class MainProgram {

    private UserManager userManager;
    private BashTrainer bashTrainer;
    private SQLGame sqlGame;
    private Scanner scanner;

    public MainProgram() {
        userManager = new UserManager();
        bashTrainer = new BashTrainer();
        sqlGame = new SQLGame();
        scanner = new Scanner(System.in);

    }

    public void start() {
        System.out.println("Welcome to the Developer Skills Tainer!");
        System.out.println("Please login to continue:");

        User currentUser = null;

        while (currentUser == null) {

            System.out.println("1.Login");
            System.out.println("2.Register as a New User");
            System.out.print("Choose an option: ");
            String option = scanner.nextLine();
            int optionAsInt = Integer.parseInt(option);

            switch(optionAsInt) {
                case 1:
                    System.out.println("Please login to continue:");
                    System.out.println("Enter username:");
                    String username = scanner.nextLine();

                    System.out.println("Enter password:");
                    String password = scanner.nextLine();

                    currentUser = userManager.loginUser(username, password);
                    if (currentUser == null) {
                        System.out.println("invalid credentials, please try again");
                    }
                    break;
                case 2:
                    System.out.println("Please Register: \n Enter Username: ");
                    String newUsername = scanner.nextLine();

                    System.out.println("Create a Password:");
                    String newPassword = scanner.nextLine();

                    currentUser = userManager.registerUser(newUsername, newPassword);
                    if(currentUser == null) {
                        System.out.println("Registration failed, please try again.");
                    }
                    break;

                default:
                    System.out.println("Invalid Option, please try again!");
            }
        }


    }

    private void showMenu(User currentUser) {
        int intChoice;
        do {
            System.out.println("Please choose an option:");
            System.out.println("1. Bash Trainer");
            System.out.println("2. SQL Game");
            System.out.println("3. Exit");

            String selection = scanner.nextLine();
            intChoice = Integer.parseInt(selection);

            switch(intChoice) {
                case 1:
                    bashTrainer.start(currentUser);
                    break;
                case 2:
                    sqlGame.start(currentUser);
                    break;
                case 3:
                    System.out.println("Thank you for using Developer Skills Trainer! Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please choose a Valid Option");
            }
        } while (intChoice != 3);
    }

    public static void main(String[] args) {
        new MainProgram().start();
    }
}
