import java.io.*;


public class UserManager {

    private static final String USER_DATA_FILE = "MainUserData.csv";
    private static final String MISC_DATA_FILE = "miscData.csv";

    public User registerUser(String newUsername, String newPassword) {

        int userID = getNextUserID();
        incrementNextUserID();

        try (FileWriter fw = new FileWriter(USER_DATA_FILE, true); PrintWriter pw = new PrintWriter(fw)) {
            pw.print("\n" + userID + "," + newUsername + "," + newPassword);
        } catch (IOException e) {
            System.out.println("Error writing to file!");
        }
        return new User(userID, newPassword, newPassword);
    }

    public User loginUser(String username, String password) {
        return new User(5,"Tommy","password");
    }

    public int getNextUserID() {
        try (FileReader fr = new FileReader(MISC_DATA_FILE);
             BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("NextUserID")) {
                    return Integer.parseInt(line.split(",")[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file!");
        }
        return -1;  // Return an invalid ID if something went wrong
    }

    public void incrementNextUserID() {
        int nextUserID = getNextUserID() + 1;
        try (FileWriter fw = new FileWriter(MISC_DATA_FILE);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println("NextUserID," + nextUserID);
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }
}
