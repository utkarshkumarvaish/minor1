import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SignInSystem {
    private static final String LAWYER_DATA_FILE = "lawyer.csv";

    public static void main(String[] args) {
        SignInSystem signInSystem = new SignInSystem();
        signInSystem.authlaw();
    }

    void authlaw() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Sign In System");
            System.out.println("1. Sign In");
            System.out.println("2. Sign Up");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    signIn();
                    break;
                case 2:
                    signUp();
                    break;
                case 3:
                    System.out.println("Exiting the program.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void signIn() {
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(LAWYER_DATA_FILE)).build()) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter email address: ");
            String emailAddress = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
    
            String[] nextLine;
            boolean found = false;
    
            while ((nextLine = reader.readNext()) != null) {
                if (nextLine.length == 5 &&
                        nextLine[3].equals(emailAddress) &&
                        nextLine[4].equals(password)) {
                    found = true;
                    System.out.println("Sign in successful.");
                    System.out.println("Name: " + nextLine[0]);
                    System.out.println("Lawyer ID: " + nextLine[1]);
                    System.out.println("Court Name: " + nextLine[2]);
                    System.out.println("Email Address: " + nextLine[3]);
                    break;
                }
            }
    
            if (!found) {
                System.out.println("Sign in failed. Invalid email address or password.");
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }

    private void signUp() {
        try (FileWriter fileWriter = new FileWriter(LAWYER_DATA_FILE, true)) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter name: ");
            String name = scanner.nextLine();
            System.out.print("Enter lawyer ID: ");
            String lawyerID = scanner.nextLine();
            System.out.print("Enter court name: ");
            String courtName = scanner.nextLine();
            System.out.print("Enter email address: ");
            String emailAddress = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            String newUser = name + "," + lawyerID + "," + courtName + "," + emailAddress + "," + password;
            fileWriter.write(newUser + "\n");
            System.out.println("Sign up successful.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void law() {
        // Your logic for the 'law' functionality goes here
        System.out.println("Inside 'law' method. You can implement your logic here.");
    }
}
