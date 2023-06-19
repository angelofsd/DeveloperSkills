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
        return new User(userID, newUsername, newPassword);
    }

    public User loginUser(String username, String password) {
        return new User(5,"Tommy","password");
    }

    public int getNextUserID() {
        try (FileReader fr = new FileReader(MISC_DATA_FILE);
             BufferedReader br = new BufferedReader(fr)) {
            String line;
            /* learned the following while loop syntax on SO. Reads a line of text from BR, br assigns the value
            to line. Checks whether the line read from br is null. If it is null, there are no more lines to read */
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
            System.err.format("Error reading File!");
        }
    }

    public boolean usernameExists(String username) {
        try (FileReader fr = new FileReader(USER_DATA_FILE); BufferedReader br = new BufferedReader(fr)) {

            String line;
            while ((line = br.readLine()) != null) {   //see above comment for info on this syntax
                String[] rowOfUserData = line.split(",");
                if (rowOfUserData.length > 1 && rowOfUserData[1].equals(username)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading File!");
        }
        return false;
    }

}
