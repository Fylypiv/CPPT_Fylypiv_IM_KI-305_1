package KI305.Fylypiv.Lab5;

import java.io.*;

/**
 * Клас {@code FileHandler} реалізує методи запису та читання результатів
 * у текстовому та двійковому форматах.
 *
 * <p>Використовується для збереження та відновлення результатів обчислень.</p>
 *
 * @author Fylypiv
 * @version 1.0
 */
public class FileHandler {

    /**
     * Записує результат у текстовий файл.
     *
     * @param fileName ім’я файлу
     * @param x значення аргументу
     * @param y результат обчислення
     * @throws IOException якщо не вдалося записати у файл
     */
    public void writeText(String fileName, double x, double y) throws IOException {
        // Використовуємо PrintWriter для зручного форматованого запису
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)))) {
            writer.printf("x = %.4f; y = %.4f%n", x, y);
        }
    }

    /**
     * Зчитує дані з текстового файлу та виводить у консоль.
     *
     * @param fileName ім’я файлу
     * @throws IOException якщо не вдалося прочитати файл
     */
    public void readText(String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            System.out.printf("\n --- Вмiст текстового файлу (%s) ---\n", fileName);
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("----------------------------------------");
        }
    }

    /**
     * Записує результат у двійковий файл, використовуючи DataOutputStream.
     *
     * @param fileName ім’я файлу
     * @param x значення аргументу
     * @param y результат обчислення
     * @throws IOException якщо не вдалося записати у файл
     */
    public void writeBinary(String fileName, double x, double y) throws IOException {
        // DataOutputStream дозволяє записувати примітивні типи (double) напряму
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(fileName, false))) {
            dos.writeDouble(x);
            dos.writeDouble(y);
        }
    }

    /**
     * Зчитує дані з двійкового файлу, використовуючи DataInputStream.
     *
     * @param fileName ім’я файлу
     * @return масив double, що містить [x, y]
     * @throws IOException якщо не вдалося прочитати файл
     */
    public double[] readBinary(String fileName) throws IOException {
        double x, y;
        try (DataInputStream dis = new DataInputStream(new FileInputStream(fileName))) {
            x = dis.readDouble();
            y = dis.readDouble();
        }
        System.out.printf("Зчитано з двiйкового файлу (%s): x = %.4f; y = %.4f%n", fileName, x, y);
        return new double[]{x, y};
    }
}