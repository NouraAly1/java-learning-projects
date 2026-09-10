/**
 * Vehicle interface.
 *
 * This is the base contract every vehicle in the rental system has to follow.
 * Doesn't matter if it's a car, motorcycle, or truck - all of them need to be
 * able to tell us their make, model, and year. Keeping this separate from the
 * more specific interfaces (CarVehicle, MotorVehicle, TruckVehicle) means the
 * main program can treat every vehicle the same way when it just needs the
 * basic info, without caring what type it actually is.
 */
public interface Vehicle {

    /** @return the make of the vehicle, e.g. "Toyota" */
    String getMake();

    /** @return the model of the vehicle, e.g. "Corolla" */
    String getModel();

    /** @return the year the vehicle was made */
    int getYear();
}