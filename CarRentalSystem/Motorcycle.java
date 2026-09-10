/**
 * Motorcycle class.
 *
 * Represents a motorcycle in the rental system. Implements Vehicle for the
 * basic info and MotorVehicle for the motorcycle-specific stuff.
 */
public class Motorcycle implements Vehicle, MotorVehicle {

    private String make;
    private String model;
    private int year;
    private int numWheels;
    private String motorcycleType;

    public Motorcycle(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public String getMake() { return make; }
    public String getModel() { return model; }
    public int getYear() { return year; }

    public void setNumWheels(int wheels) {
        if (wheels <= 0) {
            System.out.println("That doesn't look right, wheels should be more than 0. Setting to 2 by default.");
            this.numWheels = 2;
        } else {
            this.numWheels = wheels;
        }
    }

    public int getNumWheels() { return numWheels; }

    public void setMotorcycleType(String type) { this.motorcycleType = type; }
    public String getMotorcycleType() { return motorcycleType; }
}
