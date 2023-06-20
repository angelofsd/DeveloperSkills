public class ScreenManager {

    private static final int NUM_LINES = 50;


    // ANSI escape codes
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";

    public void clearScreen() {
        for( int i = 0; i < NUM_LINES; i++) {
            System.out.println();
        }
    }

    public void skipLines(int linesToSkip) {
        for( int i = 0; i < linesToSkip; i++) {
            System.out.println();
        }
    }


    public void printInColor(String text, String color) {
        switch (color) {
            case "red":
                System.out.println(ANSI_RED + text + ANSI_RESET);
                break;
            case "green":
                System.out.println(ANSI_GREEN + text + ANSI_RESET);
                break;
            case "blue":
                System.out.println(ANSI_BLUE + text + ANSI_RESET);
                break;
            case "yellow":
                System.out.println(ANSI_YELLOW + text + ANSI_RESET);
                break;
            // Add more colors as needed...
            default:
                System.out.println(text);
        }
    }
}
