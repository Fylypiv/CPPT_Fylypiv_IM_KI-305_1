package KI305.Fylypiv.Lab4;

import java.io.IOException;
import java.util.Scanner;

/**
 * Програма-драйвер для демонстрації роботи класу {@link ExpressionCalculator}.
 * Дозволяє користувачу ввести значення x, обчислює y = 1 / sin(x)
 * і зберігає результат у файл.
 */
public class Lab4Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExpressionCalculator calculator = new ExpressionCalculator();

        try {
            System.out.print("Enter value for x: ");
            double x = scanner.nextDouble();

            double y = calculator.calculate(x);
            System.out.printf("Result: y = %.4f%n", y);

            calculator.saveToFile("result.txt", x, y);
            System.out.println("Result saved to file result.txt");
        } catch (ArithmeticException e) {
            System.err.println("Calculation error: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("File writing error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unknown error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}