import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/*
 * This is the class which contains the main method and the file input and output methods. 
 * 
 * CSC 1351 Programming Project No 1
 * Section 02
 * 
 * @author Roberto Pierre
 * @since 10/16/2023
 */

public class Prog01_aOrderedList {

    /*
     * This is the main method. An aOrderedList object is created. The program
     * will prompt the user to enter the filepaths for existing input and output
     * files. The operations
     * of the inputfile.txt will be processed into the orderedList object. Then it
     * will print
     * the orderedList into the specified outputfile.txt.
     * 
     * CSC 1351 Programming Project No 1
     * Section 02
     * 
     * @author Roberto Pierre
     * 
     * @since 10/16/2023
     */
    public static void main(String[] args) {
        aOrderedList orderedList = new aOrderedList();

        // Try Catch for exception handling, start of inputfile code
        try {
            // Scanner object created from getInputFile() method with user prompt as
            // parameter
            Scanner inputFile = getInputFile("Enter the Input Filename: ");

            // While loop to seperate intended elements per line from inputfile, storing
            // into a temporary array parts
            while (inputFile.hasNextLine()) {
                String line = inputFile.nextLine();
                String[] parts = line.split(",");

                // if statements to process "Addition/Deletion operations"
                // Add Car object to orderedList
                if (parts.length >= 4 && parts[0].equals("A")) {
                    String make = parts[1];
                    int year = Integer.parseInt(parts[2]);
                    int price = Integer.parseInt(parts[3]);

                    Car car = new Car(make, year, price);
                    orderedList.add(car);
                }
                // Delete Car object at index
                else if (parts.length == 2 && parts[0].equals("D")) { // D,index
                    int index = Integer.parseInt(parts[1]);
                    orderedList.remove(index);
                }
                // Delete Car object with specified make and year
                else if (parts.length == 3 && parts[0].equals("D")) { // D,Make,Year
                    String dMake = parts[1];
                    int dYear = Integer.parseInt(parts[2]);
                    orderedList.deleteCar(dMake, dYear);
                }
            }

            inputFile.close();
        }
        // FileNotFoundException when user enters "N", will terminate program if thrown
        catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
            System.exit(0);
        }

        // Try Catch for exception handling, start of outputfile code
        try {
            // PrintWriter object created from getOutputFile() method with user prompt as
            // parameter
            PrintWriter outFile = getOutputFile("Enter the Output Filename: ");

            // printing orderedList into output file formatted
            outFile.println("Number of cars:\t" + orderedList.size());
            for (int i = 0; i < orderedList.size(); i++) { // Iterates through orderedList
                Car currentCar = (Car) orderedList.get(i); // Type casting Comparable to Car
                outFile.printf("\nMake:          %s\nYear:          %d\nPrice:         $%,d\n", currentCar.getMake(),
                        currentCar.getYear(), currentCar.getPrice());

            }
            outFile.close();

        // FileNotFoundException thrown when user enters "N", will terminate program if thrown
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
            System.exit(0);
        }

    }

    /*
     * This method will take in a String, which represents a user prompt, and
     * returns a Scanner object
     * to be used later in the main method for creating an aOrderedList object.
     * 
     * CSC 1351 Programming Project No 1
     * Section 02
     * 
     * @author Roberto Pierre
     * 
     * @since 10/16/2023
     */
    public static Scanner getInputFile(String userPrompt) throws FileNotFoundException {

        Scanner in = new Scanner(System.in);
        boolean cont = true;

        // Loop to enable user to retry inputting filePath
        while (cont) {
            System.out.print(userPrompt);
            String filePath = in.nextLine();

            // allows user to enter file as "file.txt" with or without ".\"
            if (!filePath.startsWith(".\\")) {
                filePath = ".\\" + filePath;
            }

            File file = new File(filePath);

            // Validates input as a file and returns Scanner, will either
            // throw FileNotFoundException if not valid or allow user to retry
            if (file.exists() && file.isFile()) {
                return new Scanner(file);
            } else {
                System.out
                        .print("File specified <" + filePath + "> does not exist. Would you like to continue? <Y/N> ");
                if (in.nextLine().equals("N")) {
                    throw new FileNotFoundException("User cancelled program");
                }
            }
        }
        in.close();
        return null;

    }

    /*
     * This method will take in a String, which represents a user prompt, and
     * returns a PrintWriter object
     * to be used later in the main method for printing to the output file.
     * 
     * CSC 1351 Programming Project No 1
     * Section 02
     * 
     * @author Roberto Pierre
     * 
     * @since 10/16/2023
     */
    public static PrintWriter getOutputFile(String userPrompt) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        boolean cont = true;

        // Loop to enable user to retry inputting filePath
        while (cont) {
            System.out.print(userPrompt);
            String filePath = in.nextLine();

            // allows user to enter file as "file.txt" with or without ".\"
            if (!filePath.startsWith(".\\")) {
                filePath = ".\\" + filePath;
            }

            File file = new File(filePath);

            // Validates input as a file and returns Scanner, will either
            // throw FileNotFoundException if not valid or allow user to retry
            if (file.exists() && file.isFile())
                return new PrintWriter(file);
            else {
                System.out.print("File specified <" + filePath + "> does not exist. Would you like to continue? <Y/N> ");
                if (in.nextLine().equals("N")) {
                    throw new FileNotFoundException("User cancelled program");
                }
            }
        }

        in.close();
        return null;
    }
}
