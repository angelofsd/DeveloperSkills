import java.util.Scanner;

public class BashTrainer {

    private ScreenManager screenManager = new ScreenManager();
    private static final String WELCOME = "**WELCOME TO THE BASH TRAINER AND FILE SYSTEM EMULATOR**\nType man to" +
            " see a list and discription of commands.";
    private static final String ROOT_DIR = "/c/Users/";
    private Scanner consoleInput;

    public BashTrainer() {
        consoleInput = new Scanner(System.in);
    }

    public void start(User currentUser) {
        String homeDirectory = ROOT_DIR + currentUser.getUserName();
        String currentDirectory = homeDirectory;

        screenManager.clearScreen();
        screenManager.printInColor(WELCOME, "yellow");
        screenManager.skipLines(11);
        String command;
        do {
            screenManager.printInColor(currentDirectory, "red");
            System.out.print("$");
            command = consoleInput.nextLine();

            //split the command into words so that arguments and flags can eventually be implemented
            String[] fullCommand = command.split(" ");
        } while (!command.equalsIgnoreCase("exit"));


    }


}
