package KI305.Fylypiv.Lab2;

import java.io.IOException;

/**
 * Клас, що описує фільтр кондиціонера
 * Складова частина 3 предметної області
 * 
 * @author Fylypiv
 * @version 1.0
 */
public class Filter {
    private int filterLevel; // Рівень фільтрації (1-10)
    private int cleanlinessPercent; // Відсоток чистоти
    private int hoursUsed; // Години використання
    private static final int MAX_HOURS = 500; // Максимум годин до очищення

    /**
     * Конструктор за замовчуванням
     */
    public Filter() {
        this.filterLevel = 5;
        this.cleanlinessPercent = 100;
        this.hoursUsed = 0;
    }

    /**
     * Конструктор з параметром рівня фільтрації
     * 
     * @param filterLevel рівень фільтрації (1-10)
     */
    public Filter(int filterLevel) {
        this.filterLevel = Math.max(1, Math.min(10, filterLevel));
        this.cleanlinessPercent = 100;
        this.hoursUsed = 0;
    }

    /**
     * Перевірка стану фільтра
     * 
     * @param logger об'єкт для логування
     * @throws IOException при помилці запису в файл
     */
    public void checkStatus(Logger logger) throws IOException {
        cleanlinessPercent = Math.max(0, 100 - (hoursUsed * 100 / MAX_HOURS));
        logger.log("Фiльтр - Рiвень фiльтрацiї: " + filterLevel + "/10, " +
                  "Чистота: " + cleanlinessPercent + "%, " +
                  "Годин використання: " + hoursUsed + "/" + MAX_HOURS);
    }

    /**
     * Очищення фільтра
     * 
     * @param logger об'єкт для логування
     * @throws IOException при помилці запису в файл
     */
    public void clean(Logger logger) throws IOException {
        hoursUsed = 0;
        cleanlinessPercent = 100;
        logger.log("Фiльтр очищено. Чистота вiдновлена до 100%");
    }

    /**
     * Перевірка, чи потрібне очищення
     * 
     * @return true, якщо фільтр потребує очищення
     */
    public boolean needsCleaning() {
        return cleanlinessPercent < 30 || hoursUsed >= MAX_HOURS;
    }

    /**
     * Збільшення лічильника годин використання
     * 
     * @param hours кількість годин
     * @param logger об'єкт для логування
     * @throws IOException при помилці запису в файл
     */
    public void addUsageHours(int hours, Logger logger) throws IOException {
        hoursUsed += hours;
        cleanlinessPercent = Math.max(0, 100 - (hoursUsed * 100 / MAX_HOURS));
        
        if (needsCleaning()) {
            logger.log("УВАГА: Фiльтр потребує очищення! Чистота: " + cleanlinessPercent + "%");
        }
    }

    // Геттери та сеттери
    public int getFilterLevel() {
        return filterLevel;
    }

    public void setFilterLevel(int filterLevel) {
        this.filterLevel = Math.max(1, Math.min(10, filterLevel));
    }

    public int getCleanlinessPercent() {
        return cleanlinessPercent;
    }

    public int getHoursUsed() {
        return hoursUsed;
    }

    @Override
    public String toString() {
        return "Фiльтр: рiвень " + filterLevel + "/10, чистота " + 
               cleanlinessPercent + "% (" + hoursUsed + "/" + MAX_HOURS + " год)";
    }
}