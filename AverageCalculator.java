import java.util.Scanner;

public class AverageCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask user how many numbers to enter
        System.out.print("How many numbers would you like to enter? ");
        int count = scanner.nextInt();

        double sum = 0;

        // Get each number from the user
        for (int i = 1; i <= count; i++) {
            System.out.print("Enter number " + i + ": ");
            double number = scanner.nextDouble();
            sum += number;
        }

        // Calculate average
        double average = sum / count;

        // Display result
        System.out.println("The average is: " + average);

        scanner.close();
    }
}
