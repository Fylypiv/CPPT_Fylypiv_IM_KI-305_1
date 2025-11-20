package KI305.Fylypiv.Lab5;

import java.io.IOException;
import java.util.Scanner;

/**
 * Програма-драйвер для перевірки роботи класів {@link ExpressionCalculator}
 * та {@link FileHandler}.
 *
 * <p>Виконує обчислення виразу y = 1 / sin(x) та демонструє запис/читання
 * у текстовому і двійковому форматах.</p>
 * * @author Fylypiv
 * @version 1.0
 */
public class Lab5Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ExpressionCalculator calculator = new ExpressionCalculator(); 
        FileHandler handler = new FileHandler();

        String textFileName = "result_text.txt";
        String binaryFileName = "result_bin.bin";

        try {
            System.out.print("Enter value for x:  ");
            double x = scanner.nextDouble();

            // Обчислення з обробкою ArithmeticException
            double y = calculator.calculate(x); 
            System.out.printf("Result: y = %.4f%n", y);

            // Запис у текстовий і двійковий формат з обробкою IOException
            handler.writeText(textFileName, x, y);
            handler.writeBinary(binaryFileName, x, y);
            System.out.printf("Result saved to files %s and %s\n", textFileName, binaryFileName);

            // Зчитування з файлів
            handler.readText(textFileName);
            handler.readBinary(binaryFileName);

        } catch (ArithmeticException e) {
            System.err.println("Calculation error: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("File writing error:  " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unknown error: " + e.getMessage());
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }
}