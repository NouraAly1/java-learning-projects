/**
 * TruckVehicle interface.
 *
 * Trucks care about things a car or motorcycle wouldn't - cargo capacity
 * and transmission type.
 */
public interface TruckVehicle {

    void setCargoCapacity(double tons);
    double getCargoCapacity();

    void setTransmissionType(String type);
    String getTransmissionType();
}
