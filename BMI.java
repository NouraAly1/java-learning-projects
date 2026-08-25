import java.util.Scanner;

public class BMI {
    public static void main(String[] args) {
        // Read personal details from the keyboard
        Scanner input = new Scanner(System.in);

        System.out.printf("Enter your name: ");
        String name = input.nextLine();

        System.out.printf("Enter your age: ");
        int age = input.nextInt();

        System.out.printf("Enter your weight (kg): ");
        double weight = input.nextDouble();

        System.out.printf("Enter your height (m): ");
        double height = input.nextDouble();

        // BMI = weight (kg) / height (m) squared
        double bmiValue = weight / (height * height);

        // Print a summary of the entered data and the calculated BMI
        System.out.printf("Name: %s%n" , name);
        System.out.printf("Age: %d years %n " , age);
        System.out.printf("Weight: %f kg %n" , weight );
        System.out.printf("Height: %f m %n" , height);
        System.out.printf("BMI: %.2f%n", bmiValue); // two decimal places
        input.close();
    }
}
