package monacocasinosquare;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FileHandling {

    public static String folderDirectory = System.getProperty("user.dir") + "\\TheLoginList.txt";

    public static void writeFile(ArrayList<Login> loginList) {

        try {
            FileWriter writeToFile = new FileWriter(folderDirectory, false); //false = overwrite, true = add
            PrintWriter printToFile = new PrintWriter(writeToFile);
            for (int i = 0; i < loginList.size(); i++) {
                printToFile.println(loginList.get(i).toString());
            }
            printToFile.close();
            writeToFile.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public static ArrayList<Login> readFile() {

        ArrayList<Login> loginList = new ArrayList<>();
        String lineFromFile;
        try {
            BufferedReader read = new BufferedReader(new FileReader(folderDirectory));
            while ((lineFromFile = read.readLine()) != null) {
                String[] loginDetails = lineFromFile.split(", ");
                //String username, string password, int account number
                Login myLogin = new Login(loginDetails[0], loginDetails[1], Integer.parseInt(loginDetails[2]), Integer.parseInt(loginDetails[3]), Integer.parseInt(loginDetails[4]), Integer.parseInt(loginDetails[5]), Integer.parseInt(loginDetails[6]));
                loginList.add(myLogin);
            }
            read.close();

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return loginList;
    }
    
    
}
