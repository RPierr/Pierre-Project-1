/*
 * This class is for a Car object.
 * 
 * CSC 1351 Programming Project No 1
 * Section 02
 * 
 * @author Roberto Pierre
 * @since 10/16/2023
 */

public class Car implements Comparable<Car> {
    private String make; // Make of the Car
    private int year; // Year of the Car
    private int price; // Price of the Car

    // Constructor
    public Car(String make, int year, int price) {
        this.make = make;
        this.year = year;
        this.price = price;
    }

    // Get method for make
    public String getMake() {
        return make;
    }

    // Get method for year
    public int getYear() {
        return year;
    }

    // Get method for price
    public int getPrice() {
        return price;
    }

    // Implementation for abstract compareTo() from Comparable. Will compare based
    // on make and year
    @Override
    public int compareTo(Car other) {

        int compared = this.make.compareTo(other.make);

        if (compared == 0)
            return Integer.compare(this.year, other.year);
        else
            return compared;
    }

    // toString() method with simple format
    @Override
    public String toString() {
        return "Make: " + make + ", Year: " + year + ", Price: " + price + ";";
    }
}
