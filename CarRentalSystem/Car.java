/**
 * Car class.
 *
 * Represents a car in the rental system. Implements both Vehicle (for the
 * basic make/model/year info every vehicle needs) and CarVehicle (for the
 * car-specific stuff like doors and fuel type).
 */
public class Car implements Vehicle, CarVehicle {

    private String make;
    private String model;
    private int year;
    private int numDoors;
    private String fuelType;

    public Car(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public String getMake() { return make; }
    public String getModel() { return model; }
    public int getYear() { return year; }

    public void setNumDoors(int doors) {
        if (doors <= 0) {
            System.out.println("That doesn't look right, doors should be more than 0. Setting to 4 by default.");
            this.numDoors = 4;
        } else {
            this.numDoors = doors;
        }
    }

    public int getNumDoors() { return numDoors; }

    public void setFuelType(String fuelType) { this.fuelType = fuelType; }
    public String getFuelType() { return fuelType; }
}
