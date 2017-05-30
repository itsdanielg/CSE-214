// Daniel Garcia
// SBU ID: *********
// Homework 1

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
/** @author Ritwik Banerjee */
class InputDataHandler {
    private String[] nameArray;
    private Double[] gpaArray;
    private Integer[] idArray;

    /**
     * This approach of filtering may fail because if a student whose GPA does not
     * meet the cutoff is deleted, the next student within the three arrays is skipped.
     * This poses a problem if the student skipped also does not meet the cutoff, since
     * he or she will also remain in the array. This happens because once a student is
     * deleted, the next student obtains the index value of the student previously deleted,
     * while at the same time the index value is incremented after the previous student is
     * deleted. (i.e. If the three arrays each have students in index 0, 1, 2, and students
     * in index 0 and 1 do not meet the cutoff, the student in index 0 will be deleted and
     * the next two students will now have an index value of 0 and 1. However, because the
     * index is incremented to 1, the new student who previously had an index of 1 and did
     * not meet the cutoff value will be skipped since his new index is now 0.) A simple
     * way to fix this would be to replace the for-loop with a while-loop and have
     * the index incremented only if the student meets the cutoff value.
     */
    void filter(double cutoff) {
        for (int i = 0; i < gpaArray.length; i++) {
            if (gpaArray[i] < cutoff)
                deleteStudent(i);
        }
    }
    /**
     * The only constructor for this class. It takes an input file as its argument,
     * and populates the three arrays such that the n’th line’s data is stored in
     * the (n-1)’th index of all three arrays. It throws an <tt>IOException</tt>
     * if the <tt>inputFile</tt> is not a file (e.g., it’s a directory, symbolic link,
     * etc.). It also throws an <tt>InputFileFormatException</tt> if the input file
     * contains lines that are not properly formatted or if it contains a line with
     * an invalid value for the GPA.
     *
     * @param inputFile the input file with 3 columns of tab-separated data in each line.
     * @throws IOException if there is any problem reading the input file.
     * @throws InputFileFormatException if there is any formatting problem in the
     * input file.
     */
    InputDataHandler(File inputFile) throws IOException, InputFileFormatException {
        try {
            BufferedReader readFile = new BufferedReader(new FileReader(inputFile));
            String line;
            String[] data, fileLines;
            int lines = 0, i = 0;
            while (readFile.readLine() != null) {
                lines++;
            }
            fileLines = new String[lines];
            nameArray = new String[lines];
            gpaArray = new Double[lines];
            idArray = new Integer[lines];
            BufferedReader resetReadFile = new BufferedReader(new FileReader(inputFile));
            while ((line = resetReadFile.readLine()) != null) {
                fileLines[i] = line;
                i++;
            }
            for (i = 0; i < lines; i++) {
                data = fileLines[i].split("\\t+");
                if (data.length != 3) {
                    throw new InputFileFormatException("This file is incorrectly formatted. Please try again.");
                }
                if (Double.parseDouble(data[1]) > 4.0 || Double.parseDouble(data[1]) < 1.0) {
                    throw new InputFileFormatException("This file has an invalid GPA value. Please try again.");
                }
                nameArray[i] = data[0];
                gpaArray[i] =  Double.parseDouble(data[1]);
                idArray[i] = Integer.parseInt(data[2]);
            }
        }
        catch (IOException e) {
            System.out.println("This file does not exist. Please try again.");
            System.exit(0);
        }
        catch (InputFileFormatException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }
    /**
     * Prints out the details of all the students in this class.
     * This must be in the following format:
     * <br>
     * Name : <i>name of student</i><br>
     * GPA : <i>GPA of student</i><br>
     * Student ID: <i>ID of student</i><br>
     * There must also be a one-line gap between two students.
     */
    void printAllDetails() {
        for (int i = 0; i < gpaArray.length; i++) {
            System.out.println("Name      : " + nameArray[i]);
            System.out.println("GPA       : " + gpaArray[i]);
            System.out.println("Student ID: " + idArray[i]);
            System.out.println("");
        }
    }

/**
 * This method removes all the information about a student whose information
 * is stored at the specified index (in all three arrays). It also ensures
 * that after the removal, all three arrays in the <tt>InputDataHandler</tt>
 * class have their size reduced by one, and subsequent student information
 * is shifted to the left by one.
 *
 * @param index the specified index where data is removed.
 */
    private void deleteStudent(int index) {
        nameArray[index] = null;
        gpaArray[index] = null;
        idArray[index] = null;
        String[] tempNameArray = new String[nameArray.length - 1];
        Double[] tempGpaArray = new Double[gpaArray.length - 1];
        Integer[] tempIdArray = new Integer[idArray.length];
        int count = 0;
        for (int i = 0; i < nameArray.length; i++) {
            if (nameArray[i] != null) {
                tempNameArray[count] = nameArray[i];
                tempGpaArray[count] = gpaArray[i];
                tempIdArray[count] = idArray[i];
                count++;
            }
        }
        nameArray = new String[tempNameArray.length];
        gpaArray = new Double[tempGpaArray.length];
        idArray = new Integer[tempIdArray.length];
        for (int i = 0; i < tempNameArray.length; i++) {
            nameArray[i] = tempNameArray[i];
            gpaArray[i] = tempGpaArray[i];
            idArray[i] = tempIdArray[i];
        }
    }
}
