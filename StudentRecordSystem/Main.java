// Main.java
// This is the part the administrator actually sees and types into.
// It just shows a menu, reads the choice, and calls the right method
// from StudentManagement. It doesn't store any student data itself.

package StudentRecordSystem;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        boolean running = true;

        System.out.println("=========================================");
        System.out.println(" Student Record Management System");
        System.out.println("=========================================");

        while (running) {

            System.out.println("\nMenu:");
            System.out.println("1. Add new student");
            System.out.println("2. Update student information");
            System.out.println("3. View a student's details");
            System.out.println("4. View all students");
            System.out.println("5. Exit");
            System.out.print("Choose an option (1-5): ");

            int choice;

            try {
                choice = Integer.parseInt(input.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("That's not a valid number, please pick 1-5.");
                continue; // goes back to the top of the loop and shows the menu again
            }

            switch (choice) {

                case 1:
                    addStudentFlow(input);
                    break;

                case 2:
                    updateStudentFlow(input);
                    break;

                case 3:
                    viewStudentFlow(input);
                    break;

                case 4:
                    StudentManagement.viewAllStudents();
                    break;

                case 5:
                    running = false;
                    System.out.println("Closing the program. Bye!");
                    break;

                default:
                    System.out.println("Please enter a number between 1 and 5.");
            }
        }

        input.close();
    }

    // Handles all the prompts for adding a student
    private static void addStudentFlow(Scanner input) {

        System.out.print("Enter student name: ");
        String name = input.nextLine();

        int id = readInt(input, "Enter student ID: ");
        if (id == Integer.MIN_VALUE) return; // bad input, already reported

        int age = readInt(input, "Enter student age: ");
        if (age == Integer.MIN_VALUE) return;

        System.out.print("Enter student grade: ");
        String grade = input.nextLine();

        StudentManagement.addStudent(name, id, age, grade);
    }

    // Handles all the prompts for updating a student
    private static void updateStudentFlow(Scanner input) {

        int id = readInt(input, "Enter the student ID to update: ");
        if (id == Integer.MIN_VALUE) return;

        System.out.print("Enter new name: ");
        String name = input.nextLine();

        int age = readInt(input, "Enter new age: ");
        if (age == Integer.MIN_VALUE) return;

        System.out.print("Enter new grade: ");
        String grade = input.nextLine();

        StudentManagement.updateStudent(id, name, age, grade);
    }

    // Handles the prompt for viewing one student
    private static void viewStudentFlow(Scanner input) {

        int id = readInt(input, "Enter the student ID to view: ");
        if (id == Integer.MIN_VALUE) return;

        StudentManagement.viewStudent(id);
    }

    // Small helper so I don't repeat the same try-catch everywhere.
    // Returns Integer.MIN_VALUE as a signal that the input was bad,
    // since a real student ID or age should never be that number.
    private static int readInt(Scanner input, String prompt) {
        System.out.print(prompt);
        try {
            return Integer.parseInt(input.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("That's not a valid whole number. Please try this step again from the menu.");
            return Integer.MIN_VALUE;
        }
    }
}
