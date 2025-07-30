import java.util.Scanner;

public class AverageCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("How many numbers would you like to enter? ");
        int count = scanner.nextInt();

        double sum = 0;


        for (int i = 1; i <= count; i++) {
            System.out.print("Enter number " + i + ": ");
            double number = scanner.nextDouble();
            sum += number;
        }

        double average = sum / count;

        System.out.println("The average is: " + average);

        scanner.close();
    }
}
