import java.util.Scanner;

public class BMI {
    public static void main(String[] args) {
        // Read personal details from the keyboard
        Scanner input = new Scanner(System.in);

        System.out.printf("Enter your name: ");
        String name = input.next();

        System.out.printf("Enter your age: ");
        int age = input.nextInt();

        System.out.printf("Enter your weight (kg): ");
        double weight = input.nextDouble();

        System.out.printf("Enter your height (m): ");
        double height = input.nextDouble();

        // BMI = weight (kg) / height (m) squared
        double bmiValue = weight / (height * height);

        // Print a summary of the entered data and the calculated BMI
        System.out.println("Name: " + name);
        System.out.println("Age: " + age + "years");
        System.out.println("Weight: " + weight + "kg");
        System.out.println("Height: " + height + "m");
        System.out.printf("BMI: %.2f%n", bmiValue); // two decimal places
    }
}
