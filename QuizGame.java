import java.util.Scanner;

/**
 * QuizGame
 *
 * This program asks the player five multiple-choice questions.
 * The player answers each one by typing A, B, C, or D. After all
 * five questions, the program shows how many were answered
 * correctly and displays the final score as a percentage.
 * 
 */
public class QuizGame {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int score = 0;                   // Number of correct answers so far
        final int TOTAL_QUESTIONS = 5;   // Total number of questions in the quiz

        System.out.println("Welcome to the Quiz Game!");
        System.out.println("Type A, B, C, or D to answer each question.\n");

        /* ---------- Question 1 ---------- */
        {
            System.out.println("Question 1: What is the capital of France?");
            System.out.println("A. Berlin");
            System.out.println("B. Madrid");
            System.out.println("C. Paris");
            System.out.println("D. Rome");

            char answer = ' '; // will be overwritten once a valid letter is entered
            boolean validInput = false;

            // Keep asking until the player types A, B, C, or D
            while (!validInput) {
                System.out.print("Your answer: ");
                String input = scanner.nextLine();
                input = input.trim();
                input = input.toUpperCase();

                if (input.length() > 0) {
                    answer = input.charAt(0);
                } else {
                    answer = '?';
                }

                switch (answer) {
                    case 'A':
                    case 'B':
                    case 'C':
                    case 'D':
                        validInput = true;
                        break;
                    default:
                        System.out.println("Please type A, B, C, or D.");
                }
            }

            if (answer == 'C') {
                System.out.println("Correct!\n");
                score = score + 1;
            } else {
                System.out.println("Incorrect. The correct answer was C.\n");
            }
        }

        /* ---------- Question 2 ---------- */
        {
            System.out.println("Question 2: What is 7 + 5?");
            System.out.println("A. 10");
            System.out.println("B. 11");
            System.out.println("C. 12");
            System.out.println("D. 13");

            char answer = ' ';  // will be overwritten once a valid letter is entered        
            boolean validInput = false;

            while (!validInput) {
                System.out.print("Your answer: ");
                String input = scanner.nextLine();
                input = input.trim();
                input = input.toUpperCase();

                if (input.length() > 0) {
                    answer = input.charAt(0);
                } else {
                    answer = '?';
                }

                switch (answer) {
                    case 'A':
                    case 'B':
                    case 'C':
                    case 'D':
                        validInput = true;
                        break;
                    default:
                        System.out.println("Please type A, B, C, or D.");
                }
            }

            if (answer == 'C') {
                System.out.println("Correct!\n");
                score = score + 1;
            } else {
                System.out.println("Incorrect. The correct answer was C.\n");
            }
        }

        /* ---------- Question 3 ---------- */
        {
            System.out.println("Question 3: Which planet is known as the Red Planet?");
            System.out.println("A. Earth");
            System.out.println("B. Venus");
            System.out.println("C. Mars");
            System.out.println("D. Jupiter");

            char answer = ' '; // will be overwritten once a valid letter is entered
            boolean validInput = false;

            while (!validInput) {
                System.out.print("Your answer: ");
                String input = scanner.nextLine();
                input = input.trim();
                input = input.toUpperCase();

                if (input.length() > 0) {
                    answer = input.charAt(0);
                } else {
                    answer = '?';
                }

                switch (answer) {
                    case 'A':
                    case 'B':
                    case 'C':
                    case 'D':
                        validInput = true;
                        break;
                    default:
                        System.out.println("Please type A, B, C, or D.");
                }
            }

            if (answer == 'C') {
                System.out.println("Correct!\n");
                score = score + 1;
            } else {
                System.out.println("Incorrect. The correct answer was C.\n");
            }
        }

        /* ---------- Question 4 ---------- */
        {
            System.out.println("Question 4: Who wrote \"Romeo and Juliet\"?");
            System.out.println("A. Charles Dickens");
            System.out.println("B. William Shakespeare");
            System.out.println("C. Mark Twain");
            System.out.println("D. Jane Austen");

            char answer = ' ';  // will be overwritten once a valid letter is entered
            boolean validInput = false;

            while (!validInput) {
                System.out.print("Your answer: ");
                String input = scanner.nextLine();
                input = input.trim();
                input = input.toUpperCase();

                if (input.length() > 0) {
                    answer = input.charAt(0);
                } else {
                    answer = '?';
                }

                switch (answer) {
                    case 'A':
                    case 'B':
                    case 'C':
                    case 'D':
                        validInput = true;
                        break;
                    default:
                        System.out.println("Please type A, B, C, or D.");
                }
            }

            if (answer == 'B') {
                System.out.println("Correct!\n");
                score = score + 1;
            } else {
                System.out.println("Incorrect. The correct answer was B.\n");
            }
        }

        /* ---------- Question 5 ---------- */
        {
            System.out.println("Question 5: What is the largest ocean on Earth?");
            System.out.println("A. Atlantic Ocean");
            System.out.println("B. Indian Ocean");
            System.out.println("C. Arctic Ocean");
            System.out.println("D. Pacific Ocean");

            char answer = ' ';  // will be overwritten once a valid letter is entered
            boolean validInput = false;

            while (!validInput) {
                System.out.print("Your answer: ");
                String input = scanner.nextLine();
                input = input.trim();
                input = input.toUpperCase();

                if (input.length() > 0) {
                    answer = input.charAt(0);
                } else {
                    answer = '?';
                }

                switch (answer) {
                    case 'A':
                    case 'B':
                    case 'C':
                    case 'D':
                        validInput = true;
                        break;
                    default:
                        System.out.println("Please type A, B, C, or D.");
                }
            }

            if (answer == 'D') {
                System.out.println("Correct!\n");
                score = score + 1;
            } else {
                System.out.println("Incorrect. The correct answer was D.\n");
            }
        }

        /* ---------- Final score ---------- */

        double percentage = (score * 100.0) / TOTAL_QUESTIONS;

        System.out.println("Quiz finished!");
        System.out.println(
            "You got " + score + " out of " + TOTAL_QUESTIONS + " correct.");
        System.out.printf("Your final score is %.1f%%%n", percentage);

        scanner.close();
    }
}
