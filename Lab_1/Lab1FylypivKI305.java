import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Клас {@code Lab1FylypivKI305} створює зубчастий візерунок (двовимірний масив змінної довжини рядків),
 * заповнює його заданим символом та записує результат у текстовий файл.
 * @author Fylypiv
 * @version 1.0
 */
public class Lab1FylypivKI305 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Зчитування розміру матриці
            System.out.print("Введiть розмiр матрицi n: ");
            int n = scanner.nextInt();
            scanner.nextLine(); // очищення буфера після nextInt()

            // Зчитування символу-заповнювача
            System.out.print("Введiть символ-заповнювач: ");
            String input = scanner.nextLine();

            // Перевірка правильності введення
            if (input.length() != 1) {
                System.out.println("Помилка: потрібно ввести рiвно один символ!");
                return;
            }

            char fill = input.charAt(0);

            // --- Створення зубчастого масиву ---
            char[][] matrix = new char[n][];

            for (int i = 0; i < n; i++) {
                int count = 0;

                // Обчислення кількості елементів у кожному рядку
                for (int j = 0; j < n; j++) {
                    if (j >= i && j < n - i || j <= i && j >= n - i - 1) {
                        count++;
                    }
                }

                // Виділення пам'яті для поточного рядка
                matrix[i] = new char[count];

                int index = 0;
                for (int j = 0; j < n; j++) {
                    if (j >= i && j < n - i || j <= i && j >= n - i - 1) {
                        matrix[i][index++] = fill;
                    }
                }
            }

            // --- Виведення результату в консоль ---
            System.out.println("\nРезультат:");
            for (int i = 0; i < n; i++) {
                int index = 0;
                for (int j = 0; j < n; j++) {
                    if (j >= i && j < n - i || j <= i && j >= n - i - 1) {
                        System.out.print(matrix[i][index++] + " ");
                    } else {
                        System.out.print("  ");
                    }
                }
                System.out.println();
            }

            // --- Запис результату у файл ---
            try (FileWriter writer = new FileWriter("output.txt")) {
                for (int i = 0; i < n; i++) {
                    int index = 0;
                    for (int j = 0; j < n; j++) {
                        if (j >= i && j < n - i || j <= i && j >= n - i - 1) {
                            writer.write(matrix[i][index++] + " ");
                        } else {
                            writer.write("  ");
                        }
                    }
                    writer.write(System.lineSeparator());
                }
            }

            System.out.println("Зубчастий масив збережено у output.txt");

        } catch (IOException e) {
            System.out.println("IO помилка: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
