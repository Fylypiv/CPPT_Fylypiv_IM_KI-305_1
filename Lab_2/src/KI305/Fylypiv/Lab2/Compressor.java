package KI305.Fylypiv.Lab2;

import java.io.IOException;

/**
 * Клас, що описує компресор кондиціонера
 * Складова частина 1 предметної області
 * @author Fylypiv
 * @version 1.0
 */
public class Compressor {
    private String type;
    private int basePower; // Вт
    private int currentPower;
    private boolean isRunning;

    /**
     * Конструктор за замовчуванням
     */
    public Compressor() {
        this.type = "Стандартний";
        this.basePower = 2000;
        this.currentPower = 0;
        this.isRunning = false;
    }

    /**
     * Конструктор з параметрами
     * 
     * @param type тип компресора
     * @param basePower базова потужність у Вт
     */
    public Compressor(String type, int basePower) {
        this.type = type;
        this.basePower = basePower;
        this.currentPower = 0;
        this.isRunning = false;
    }

    /**
     * Запуск компресора
     * 
     * @param logger об'єкт для логування
     * @throws IOException при помилці запису в файл
     */
    public void start(Logger logger) throws IOException {
        if (!isRunning) {
            isRunning = true;
            currentPower = basePower;
            logger.log("Компресор запущено. Тип: " + type + ", Потужнiсть: " + basePower + " Вт");
        }
    }

    /**
     * Зупинка компресора
     * 
     * @param logger об'єкт для логування
     * @throws IOException при помилці запису в файл
     */
    public void stop(Logger logger) throws IOException {
        if (isRunning) {
            isRunning = false;
            currentPower = 0;
            logger.log("Компресор зупинено");
        }
    }

    /**
     * Регулювання потужності компресора
     * 
     * @param power нова потужність
     * @param logger об'єкт для логування
     * @throws IOException при помилці запису в файл
     */
    public void adjustPower(int power, Logger logger) throws IOException {
        if (isRunning) {
            currentPower = Math.min(power, basePower);
            logger.log("Потужнiсть компресора змiнено на: " + currentPower + " Вт");
        }
    }

    /**
     * Перевірка стану компресора
     * 
     * @param logger об'єкт для логування
     * @throws IOException при помилці запису в файл
     */
    public void checkStatus(Logger logger) throws IOException {
        logger.log("Компресор - Тип: " + type + ", Статус: " + 
                  (isRunning ? "Працює" : "Зупинений") + 
                  ", Потужнiсть: " + currentPower + "/" + basePower + " Вт");
    }

    // Геттери та сеттери
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getBasePower() {
        return basePower;
    }

    public void setBasePower(int basePower) {
        this.basePower = basePower;
    }

    public int getCurrentPower() {
        return currentPower;
    }

    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public String toString() {
        return "Компресор: " + type + " (" + 
               (isRunning ? "працює, " + currentPower + " Вт" : "зупинений") + ")";
    }
}