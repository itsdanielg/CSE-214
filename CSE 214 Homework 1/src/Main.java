// Daniel Garcia
// SBU ID: *********
// Homework 1

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

/** @author Ritwik Banerjee */
public class Main {
    private static final String INPUT_FILE_PATH = "C:\\Users\\Daniel\\IdeaProjects\\CSE 214 Homework 1\\src\\input.txt";
    public static void main(String[] args)
            throws URISyntaxException, IOException, InputFileFormatException {
        File inFile = new File(INPUT_FILE_PATH);
        InputDataHandler handler = new InputDataHandler(inFile);
        handler.printAllDetails();
        System.out.print("Enter the GPA cutoff for the course: ");

        int check = 0;
        double cutoff = 0;
        String strInput;
        java.util.Scanner input =  new java.util.Scanner(System.in);
        while (check == 0) {
            try {
                strInput = input.nextLine();
                cutoff = Double.parseDouble(strInput);
                if (cutoff < 1.0 || cutoff > 4.0) {
                    throw new InputFileFormatException();
                }
                else {
                    check++;
                }
            }
            catch (NumberFormatException e) {
                System.out.println("\nCutoff is not a double. Please try again.");
                System.out.print("Enter the GPA cutoff for the course: ");
            }
            catch (InputFileFormatException e) {
                System.out.println("\nCutoff is invalid. Please try again.");
                System.out.print("Enter the GPA cutoff for the course: ");
            }
        }
        System.out.println("\nThe cutoff is " + cutoff + ".\n");

        handler.filter(cutoff);
        handler.printAllDetails();
    }
}
