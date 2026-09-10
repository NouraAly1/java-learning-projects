/**
 * CarVehicle interface.
 *
 * Extra behavior that only makes sense for cars - number of doors and fuel
 * type. A truck or motorcycle doesn't really have "doors" the same way, so
 * this stays as its own interface instead of getting crammed into Vehicle.
 */
public interface CarVehicle {

    void setNumDoors(int doors);
    int getNumDoors();

    void setFuelType(String fuelType);
    String getFuelType();
}