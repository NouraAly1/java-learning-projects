import java.util.Scanner;

public class GradingScale {

    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            int score = 0;
            System.out.print("Enter your score : ");
            score = input.nextInt();
            if (score < 0 || score > 100) {
                System.out.println("Enter a valid score between 0 and 100");
            }
            else if (score >= 90) {
                System.out.println("Your grade is A");
            }
            else if (score >= 80 && score < 90) {
                System.out.println("Your grade is B");
            }
            else if (score >= 70 && score < 80) {
                System.out.println("your grade is C");
            }
            else if (score >= 60 && score < 70) {
                System.out.println("Your grade is D");
            } 
            else {
                System.out.println("Unfortunately you failed ");
            }
        }
    }
}
