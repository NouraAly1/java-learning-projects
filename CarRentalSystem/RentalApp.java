import java.util.ArrayList;
import java.util.Scanner;

/**
 * RentalApp - the main program for the car rental agency system.
 *
 * Shows a text menu, lets the user add Cars, Motorcycles, and Trucks with
 * their own details, and prints out everything added so far. All vehicles
 * get stored in one list using the Vehicle interface type, since Car,
 * Motorcycle, and Truck all implement it - that's the whole point of using
 * the interface here, one list can hold all three types.
 */
public class RentalApp {

    private static ArrayList<Vehicle> vehicles = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n--- Car Rental Agency ---");
            System.out.println("1. Add a Car");
            System.out.println("2. Add a Motorcycle");
            System.out.println("3. Add a Truck");
            System.out.println("4. Show all vehicles");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1": addCar(); break;
                case "2": addMotorcycle(); break;
                case "3": addTruck(); break;
                case "4": showAllVehicles(); break;
                case "5":
                    running = false;
                    System.out.println("Thanks, closing the program now.");
                    break;
                default:
                    System.out.println("That's not a valid option, try again.");
            }
        }
        scanner.close();
    }

    private static int readIntSafely(String prompt) {
        int value = -1;
        boolean valid = false;
        while (!valid) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                value = Integer.parseInt(input);
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("That's not a whole number. Please try again.");
            }
        }
        return value;
    }

    private static double readDoubleSafely(String prompt) {
        double value = -1;
        boolean valid = false;
        while (!valid) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                value = Double.parseDouble(input);
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("That's not a valid number. Please try again.");
            }
        }
        return value;
    }

    private static void addCar() {
        System.out.println("\n-- Adding a new Car --");
        System.out.print("Make: ");
        String make = scanner.nextLine();
        System.out.print("Model: ");
        String model = scanner.nextLine();
        int year = readIntSafely("Year: ");

        Car car = new Car(make, model, year);

        int doors = readIntSafely("Number of doors: ");
        car.setNumDoors(doors);

        System.out.print("Fuel type (petrol, diesel, or electric): ");
        String fuel = scanner.nextLine();
        if (!fuel.equalsIgnoreCase("petrol") && !fuel.equalsIgnoreCase("diesel") && !fuel.equalsIgnoreCase("electric")) {
            System.out.println("Didn't recognize that fuel type, setting it to petrol by default.");
            fuel = "petrol";
        }
        car.setFuelType(fuel);

        vehicles.add(car);
        System.out.println("Car added!");
    }

    private static void addMotorcycle() {
        System.out.println("\n-- Adding a new Motorcycle --");
        System.out.print("Make: ");
        String make = scanner.nextLine();
        System.out.print("Model: ");
        String model = scanner.nextLine();
        int year = readIntSafely("Year: ");

        Motorcycle bike = new Motorcycle(make, model, year);

        int wheels = readIntSafely("Number of wheels: ");
        bike.setNumWheels(wheels);

        System.out.print("Type (sport, cruiser, or off-road): ");
        String type = scanner.nextLine();
        if (!type.equalsIgnoreCase("sport") && !type.equalsIgnoreCase("cruiser") && !type.equalsIgnoreCase("off-road")) {
            System.out.println("Didn't recognize that type, setting it to sport by default.");
            type = "sport";
        }
        bike.setMotorcycleType(type);

        vehicles.add(bike);
        System.out.println("Motorcycle added!");
    }

    private static void addTruck() {
        System.out.println("\n-- Adding a new Truck --");
        System.out.print("Make: ");
        String make = scanner.nextLine();
        System.out.print("Model: ");
        String model = scanner.nextLine();
        int year = readIntSafely("Year: ");

        Truck truck = new Truck(make, model, year);

        double cargo = readDoubleSafely("Cargo capacity (in tons): ");
        truck.setCargoCapacity(cargo);

        System.out.print("Transmission type (manual or automatic): ");
        String trans = scanner.nextLine();
        if (!trans.equalsIgnoreCase("manual") && !trans.equalsIgnoreCase("automatic")) {
            System.out.println("Didn't recognize that, setting it to automatic by default.");
            trans = "automatic";
        }
        truck.setTransmissionType(trans);

        vehicles.add(truck);
        System.out.println("Truck added!");
    }

    private static void showAllVehicles() {
        if (vehicles.isEmpty()) {
            System.out.println("\nNo vehicles added yet.");
            return;
        }

        System.out.println("\n--- All Vehicles ---");
        for (Vehicle v : vehicles) {
            System.out.println("\nMake: " + v.getMake());
            System.out.println("Model: " + v.getModel());
            System.out.println("Year: " + v.getYear());

            if (v instanceof Car) {
                Car c = (Car) v;
                System.out.println("Type: Car");
                System.out.println("Doors: " + c.getNumDoors());
                System.out.println("Fuel type: " + c.getFuelType());
            } else if (v instanceof Motorcycle) {
                Motorcycle m = (Motorcycle) v;
                System.out.println("Type: Motorcycle");
                System.out.println("Wheels: " + m.getNumWheels());
                System.out.println("Motorcycle type: " + m.getMotorcycleType());
            } else if (v instanceof Truck) {
                Truck t = (Truck) v;
                System.out.println("Type: Truck");
                System.out.println("Cargo capacity: " + t.getCargoCapacity() + " tons");
                System.out.println("Transmission: " + t.getTransmissionType());
            }
        }
    }
}
