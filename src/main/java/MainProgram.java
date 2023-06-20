import java.util.Scanner;

public class MainProgram {

    private UserManager userManager;
    private BashTrainer bashTrainer;
    private ScreenManager screenManager = new ScreenManager();
    private SQLGame sqlGame;
    private Scanner scanner;
    public static final String WELCOME_MESSAGE = "Welcome to the Developer Skills Tainer!\nPlease login to continue:";
    public static final String GAME_MENU = "1. Bash Trainer \n2. SQL Game \n3. Exit";
    public static final String LOGIN = "Please login to continue:\nEnter Username:";
    public static final String LOGIN_OR_REGISTER =  "1.Login\n2.Register as a New User\nChoose an Option: ";

    public MainProgram() {
        userManager = new UserManager();
        bashTrainer = new BashTrainer();
        screenManager = new ScreenManager();
        sqlGame = new SQLGame();
        scanner = new Scanner(System.in);

    }

    public void start() {
        screenManager.printInColor(WELCOME_MESSAGE, "yellow");

        User currentUser = null;

        while (currentUser == null) {

            System.out.println(LOGIN_OR_REGISTER);
            int optionAsInt = 0;
            try {
                String option = scanner.nextLine();
                optionAsInt = Integer.parseInt(option);
                if (!(optionAsInt == 1) && !(optionAsInt == 2)) {
                    throw new IllegalArgumentException("Invalid Choice!");
                }
            } catch (IllegalArgumentException e) {
                System.err.println("This is not a valid input!");
            }
            switch(optionAsInt) {
                case 1:
                    String username = inputUsername();
                    String password = inputPassword();
                    boolean isAuthorized = userManager.isAuthenticated(username, password);
                    if (isAuthorized) {
                        currentUser = userManager.loginUser(username, password);
                    }
                    if (currentUser == null || !isAuthorized) {
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

        screenManager.clearScreen();
        do {
            System.out.println("Welcome " + currentUser.getUserName() + "! Please choose an option:");
            System.out.println(GAME_MENU);
            screenManager.skipLines(11);

            System.out.print(">");
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
