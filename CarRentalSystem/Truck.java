/**
 * Truck class.
 *
 * Represents a truck in the rental system. Implements Vehicle for the basic
 * info and TruckVehicle for cargo capacity and transmission type.
 */
public class Truck implements Vehicle, TruckVehicle {

    private String make;
    private String model;
    private int year;
    private double cargoCapacity;
    private String transmissionType;

    public Truck(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public String getMake() { return make; }
    public String getModel() { return model; }
    public int getYear() { return year; }

    public void setCargoCapacity(double tons) {
        if (tons < 0) {
            System.out.println("Cargo capacity can't be negative, setting it to 0.");
            this.cargoCapacity = 0;
        } else {
            this.cargoCapacity = tons;
        }
    }

    public double getCargoCapacity() { return cargoCapacity; }

    public void setTransmissionType(String type) { this.transmissionType = type; }
    public String getTransmissionType() { return transmissionType; }
}