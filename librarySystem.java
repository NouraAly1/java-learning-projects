import java.util.Scanner;

public class librarySystem {

    // parallel arrays to store book info - each index is one book
    static String[] titles = new String[100];
    static String[] authors = new String[100];
    static int[] quantities = new int[100];
    static int bookCount = 0; // tracks how many books are in the library

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int choice = 0;

        System.out.println("Welcome to the Library System!");

        // this will keep showing the menu until the user picks Exit (4)
        while (choice != 4) {

            System.out.println("\n--- Library Menu ---");
            System.out.println("1. Add Books");
            System.out.println("2. Borrow Books");
            System.out.println("3. Return Books");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            // i used try-catch just in case the user types something that isn't a number
            try {
                choice = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 4.");
                continue;
            }

            switch (choice) {
                case 1:
                    addBook(input);
                    break;
                case 2:
                    borrowBook(input);
                    break;
                case 3:
                    returnBook(input);
                    break;
                case 4:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("That's not a valid option. Please choose 1 to 4.");
            }
        }

        input.close();
    }

    // searching for a book by title and returns its index
    // this will returns -1 if the book isn't found
    static int findBook(String title) {
        for (int i = 0; i < bookCount; i++) {
            if (titles[i].equalsIgnoreCase(title)) {
                return i;
            }
        }
        return -1;
    }

    static void addBook(Scanner input) {

        System.out.print("Enter the book title: ");
        String title = input.nextLine();

        System.out.print("Enter the author name: ");
        String author = input.nextLine();

        int qty = 0;

        try {
            System.out.print("Enter the quantity: ");
            qty = Integer.parseInt(input.nextLine());
            if (qty <= 0) {
                System.out.println("Quantity must be greater than 0.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid quantity. Please enter a number.");
            return;
        }

        int index = findBook(title);

        // this block works if the book already exists, just add to its quantity
        if (index != -1) {
            quantities[index] += qty;
            System.out.println("Book already exists. Updated quantity to " + quantities[index] + ".");
        } else {
            // check if the array is full before adding
            if (bookCount >= 100) {
                System.out.println("Library is full. Cannot add more books.");
                return;
            }
            titles[bookCount] = title;
            authors[bookCount] = author;
            quantities[bookCount] = qty;
            bookCount++;
            System.out.println("Book added successfully!");
        }
    }

    static void borrowBook(Scanner input) {

        System.out.print("Enter the book title: ");
        String title = input.nextLine();

        int index = findBook(title);

        if (index == -1) {
            System.out.println("Sorry, this book is not in our library.");
            return;
        }

        int qty = 0;

        try {
            System.out.print("How many copies do you want to borrow? ");
            qty = Integer.parseInt(input.nextLine());
            if (qty <= 0) {
                System.out.println("Please enter a number greater than 0.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return;
        }

        // this if block to check if enough copies are available
        if (qty <= quantities[index]) {
            quantities[index] -= qty;
            System.out.println("You borrowed " + qty + " copy/copies of \"" + titles[index] + "\". Enjoy reading!");
            System.out.println("Remaining copies: " + quantities[index]);
        } else {
            System.out.println("Sorry, we only have " + quantities[index] + " copy/copies available.");
        }
    }

    static void returnBook(Scanner input) {

        System.out.print("Enter book title: ");
        String title = input.nextLine();

        // make sure this book actually belongs to the library before accepting it
        int index = findBook(title);

        if (index == -1) {
            System.out.println("This book does not belong to our library system.");
            return;
        }

        int qty = 0;

        try {
            System.out.print("How many copies are you returning? ");
            qty = Integer.parseInt(input.nextLine());
            if (qty <= 0) {
                System.out.println("Please enter a number greater than 0.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return;
        }

        quantities[index] += qty;
        System.out.println("Thank you! " + qty + " copy/copies of \"" + titles[index] + "\" returned successfully.");
        System.out.println("Total copies now available: " + quantities[index]);
    }
}


