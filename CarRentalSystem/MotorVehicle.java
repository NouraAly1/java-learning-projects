/**
 * MotorVehicle interface.
 *
 * Motorcycle-only behavior. Wheels is included as a settable value (instead
 * of just assuming 2) because some motorcycles, like trikes, don't have the
 * usual 2 wheels - so it made more sense to let the user set it.
 */
public interface MotorVehicle {

    void setNumWheels(int wheels);
    int getNumWheels();

    void setMotorcycleType(String type);
    String getMotorcycleType();
}