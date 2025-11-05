package KI305.Fylypiv.Lab2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Клас для логування дій у файл
 * Механізм коректного завершення роботи з файлом(implements AutoCloseable)
 * Протокол діяльності методів
 * 
 * @author Fylypiv
 * @version 1.0
 */
public class Logger implements AutoCloseable {
    private BufferedWriter writer;
    private DateTimeFormatter formatter;

    /**
     * Конструктор логера
     * 
     * @param filename ім'я файлу для логування
     * @throws IOException при помилці створення файлу
     */
    public Logger(String filename) throws IOException {
        writer = new BufferedWriter(new FileWriter(filename, true));
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        // Запис заголовка нової сесії
        writer.write("\n========================================\n");
        writer.write("Нова сесiя: " + LocalDateTime.now().format(formatter) + "\n");
        writer.write("========================================\n");
        writer.flush();
    }

    /**
     * Запис повідомлення в лог-файл
     * 
     * @param message повідомлення для логування
     * @throws IOException при помилці запису
     */
    public void log(String message) throws IOException {
        String timestamp = LocalDateTime.now().format(formatter);
        writer.write("[" + timestamp + "] " + message);
        writer.newLine();
        writer.flush(); // Одразу записуємо в файл
    }

    /**
     * Коректне завершення роботи з файлом
     * Метод автоматично викликається при використанні try-with-resources
     * 
     * @throws IOException при помилці закриття файлу
     */
    @Override
    public void close() throws IOException {
        if (writer != null) {
            writer.write("========================================\n");
            writer.write("Сесiя завершена: " + LocalDateTime.now().format(formatter) + "\n");
            writer.write("========================================\n\n");
            writer.flush();
            writer.close();
        }
    }
}