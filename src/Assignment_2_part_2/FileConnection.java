package Assignment_2_part_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileConnection {

    static FileConnection userConnection;

    private FileConnection() {

    }

    public static FileConnection getUserConnection() {
        if (userConnection == null) {
            userConnection = new FileConnection();
        }
        return userConnection;
    }

    public boolean verifyUser(String username, String password) {
        boolean isVerified = false;
        try {
            Scanner scanner = new Scanner(new File("./src/Assignment_2_part_2/users.txt"));
            while (scanner.hasNextLine()) {
                String name = scanner.next();
                String pass = scanner.next();
                if (username.equals(name) && password.equals(pass)) {
                    isVerified = true;
                    break;
                }
            }
            scanner.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

        return isVerified;
    }

    public String openFile(File file) {
        StringBuilder text = new StringBuilder();
        try {
            Scanner input = new Scanner(file);
            while (input.hasNextLine()) {
                text.append(input.nextLine()).append("\n");
            }
            input.close();
        } catch (FileNotFoundException ex) {
        }
        return text.toString();
    }

    public void saveFile(String path, String saved) throws IOException {
        File file = new File(path);
        try (FileWriter fileWriter = new FileWriter(file); PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.print(saved);
        }
    }
}
