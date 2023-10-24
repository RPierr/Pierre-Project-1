import java.util.Arrays;

/*
 * This is for an Ordered List. 
 * 
 * CSC 1351 Programming Project No 1
 * Section 02
 * 
 * @author Roberto Pierre
 * @since 10/16/2023
 */

public class aOrderedList {
    private final int SIZEINCREMENTS = 20; // size of increments for increasing ordered list
    private Comparable[] oList; // the ordered list
    private int listSize; // size of ordered list
    private int numObjects; // number of objects in ordered list
    private int curr; // index of the current element accessed via itterator

    // Constructor with default values
    public aOrderedList() {
        numObjects = 0;
        listSize = SIZEINCREMENTS;
        oList = new Comparable[listSize];
    }

    // Returns the number of objects in the list
    public int size() {
        return numObjects;
    }

    // Returns the element at the specified index in this list
    public Comparable get(int index) {
        return oList[index];
    }

    // Returns true if the array is empty and false otherwise
    public boolean isEmpty() {
        if (numObjects > 0) {
            return true;
        } else {
            return false;
        }

    }

    // Removes the element at the specified opsition in this list and
    // reorders the list to accommadate removal
    public void remove(int index) {

        // Create temp array
        Comparable[] temp = new Comparable[oList.length - 1];

        // Copy elements excecpt element at the index
        for (int i = 0, k = 0; i < oList.length; i++) {

            // If index = i, skip the element
            if (i == index) {
                continue;
            }

            // Copies current element into temp array
            temp[k++] = oList[i];

        }
        // Update oList
        oList = temp;

        // Update numObjects
        numObjects--;
    }

    // Method to delete a specific Car object based on make and year
    public void deleteCar(String make, int year) {
        for (int i = 0; i < numObjects; i++) { // iterate through oList
            Car currentCar = (Car) oList[i];

            // If element matches specified Car object, calls remove() method
            if (currentCar.getMake().equals(make) && currentCar.getYear() == (year)) {
                remove(i);
                break;
            }
        }
    }

    // Method to add a new object into oList at the end of the list
    public void add(Comparable newObject) {

        // Check to update size of array if max elements have been reached
        if (listSize == numObjects)
            resizeArray();

        // Find correct position to insert the newObject to maintain sorted order
        int index = 0;
        while (index < numObjects && newObject.compareTo(oList[index]) > 0)
            index++;

        // Shift elements to right to make room for newCar
        for (int i = numObjects; i > index; i--)
            oList[i] = oList[i - 1];

        // Insert newCar at correct position
        oList[index] = newObject;

        // Update the num objects
        numObjects++;
    }

    // Method to resize array if max has been reached
    private void resizeArray() {
        // Create new array with increased size using copyOf() method
        listSize += SIZEINCREMENTS;
        Comparable[] newArray = Arrays.copyOf(oList, listSize);

        // Copy elements from old array into the new array
        oList = newArray;
    }

    // Resets curr to 0;
    public void reset() {
        curr = 0;
    }

    // Returns next element in the iteration and increments curr
    public Comparable next() {
        if (hasNext()) {
            return oList[curr++];
        } else {
            return null;
        }

    }

    // Returns true of the iteration has more elments to iterate through
    public boolean hasNext() {
        return curr < numObjects;
    }

    // Removes the last element returned by the next() method
    public void remove() {
        if (curr > 0) {
            for (int i = curr - 1; i < numObjects - 1; i++) { // Iterates from 1st to 2nd to last element
                oList[i] = oList[i + 1]; // Copy next element into current
            }
            numObjects--;
            curr--;
        }
    }

    // toString() method. Returns toString calues of the list objects, seperated by
    // commas, and declimited by brackets
    @Override
    public String toString() {
        String result = "[";
        for (int i = 0; i < numObjects; i++) { // iterates through each object in the list and adds to result
            result += (oList[i]);
            if (i < numObjects - 1) { // adds "," in between consecutive elements
                result += ", ";
            }
        }
        result+= "]";
        return result.toString(); // returns finished String result
    }
}
