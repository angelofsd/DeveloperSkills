import java.util.Scanner;

public class MainProgram {

    private UserManager userManager;
    private BashTrainer bashTrainer;
    private SQLGame sqlGame;
    private Scanner scanner;
    public static final String WELCOME_MESSAGE = "Welcome to the Developer Skills Tainer!\nPlease login to continue:";
    public static final String GAME_MENU = "1. Bash Trainer \n2. SQL Game \n3. Exit";
    public static final String LOGIN = "Please login to continue:\nEnter Username:";

    public MainProgram() {
        userManager = new UserManager();
        bashTrainer = new BashTrainer();
        sqlGame = new SQLGame();
        scanner = new Scanner(System.in);

    }

    public void start() {
        System.out.println(WELCOME_MESSAGE);

        User currentUser = null;

        while (currentUser == null) {

            System.out.println("1.Login");
            System.out.println("2.Register as a New User");
            System.out.print("Choose an option: ");
            String option = scanner.nextLine();
            int optionAsInt = Integer.parseInt(option);

            switch(optionAsInt) {
                case 1:
                    String username = inputUsername();
                    String password = inputPassword();

                    currentUser = userManager.loginUser(username, password);
                    if (currentUser == null) {
                        System.out.println("invalid credentials, please try again");
                    }
                    break;
                case 2:
                    currentUser = registerNewUser();
                    if(currentUser == null) {
                        System.out.println("Registration failed, please try again.");
                    }
                    break;
                default:
                    System.out.println("Invalid Option, please try again!");
            }
        }
        showMenu(currentUser);
    }

    private void showMenu(User currentUser) {
        int intChoice;
        do {
            System.out.println("Welcome " + currentUser.getUserName() + "! Please choose an option:");
            System.out.println(GAME_MENU);

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

    public String inputUsername() {
        System.out.println(LOGIN);
        String username = scanner.nextLine();
        return username;
    }

    public String inputPassword() {
        System.out.println("Enter password:");
        String password = scanner.nextLine();
        return password;
    }

    public User registerNewUser() {
        System.out.println("Please Register: \n Enter Username: ");
        String newUsername = scanner.nextLine();

        if (userManager.usernameExists(newUsername)) {
            System.out.println("Username already taken, please try again.");
            return null;
        }

        System.out.println("Create a Password:");
        String newPassword = scanner.nextLine();

        return userManager.registerUser(newUsername, newPassword);
    }

    public static void main(String[] args) {
        new MainProgram().start();
    }
}
