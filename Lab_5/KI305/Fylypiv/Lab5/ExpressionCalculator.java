package KI305.Fylypiv.Lab5;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Клас {@code ExpressionCalculator} реалізує обчислення виразу y = 1 / sin(x)
 * та запис результату у файл.
 *
 * У випадку виникнення помилок (наприклад, sin(x) = 0 або проблеми з файлом)
 * генерується виняток, який обробляється у програмі-драйвері.
 *
 * @author Fylypiv
 * @version 1.0
 */
public class ExpressionCalculator {

    /**
     * Обчислює значення виразу y = 1 / sin(x).
     *
     * @param x значення аргументу
     * @return результат обчислення виразу
     * @throws ArithmeticException якщо sin(x) = 0
     */
    public double calculate(double x) throws ArithmeticException {
        double denominator = Math.sin(x);
        if (denominator == 0) {
            throw new ArithmeticException("Error: sin(x) = 0, division by zero!");
        }
        return 1 / denominator;
    }

    /**
     * Записує результат обчислення у файл.
     * (Цей метод взято з вашого прикладу, він універсальний)
     *
     * @param fileName ім’я файлу
     * @param x значення аргументу
     * @param y результат обчислення
     * @throws IOException якщо не вдалося записати у файл
     */
    public void saveToFile(String fileName, double x, double y) throws IOException {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(String.format("x = %.4f; y = %.4f%n", x, y));
        }
    }
}