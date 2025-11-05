package KI305.Fylypiv.Lab2;

import java.io.IOException;

/**
 * Клас, що описує кондиціонер
 * Містить мінімум 3 поля - об'єкти класів (Compressor, Fan, Filter)
 * Містить кілька конструкторів
 * Містить мінімум 10 методів
 * Методи ведуть протокол діяльності у файл
 * 
 * @author Fylypiv
 * @version 1.0
 */
public class Conditioner {
    // Поле 1: Об'єкт класу Compressor (компресор)
    private Compressor compressor;
    
    // Поле 2: Об'єкт класу Fan (вентилятор)
    private Fan fan;
    
    // Поле 3: Об'єкт класу Filter (фільтр)
    private Filter filter;
    
    // Додаткові поля
    private String brand;
    private boolean isOn;
    private int targetTemperature;
    private String mode; // COOLING, HEATING, FAN, AUTO
    private String airDirection; // HORIZONTAL, VERTICAL, AUTO
    private boolean sleepMode;
    private int currentTemperature;

    // Конструктор 1: За замовчуванням
    /**
     * Конструктор за замовчуванням
     * Створює кондиціонер з базовими параметрами
     */
    public Conditioner() {
        this.compressor = new Compressor("Стандартний", 2000);
        this.fan = new Fan(3);
        this.filter = new Filter(5);
        this.brand = "NoName";
        this.isOn = false;
        this.targetTemperature = 24;
        this.mode = "COOLING";
        this.airDirection = "AUTO";
        this.sleepMode = false;
        this.currentTemperature = 26;
    }

    // Конструктор 2: З основними параметрами
    /**
     * Конструктор з параметрами
     * 
     * @param compressor компресор кондиціонера
     * @param fan вентилятор кондиціонера
     * @param filter фільтр кондиціонера
     * @param brand бренд кондиціонера
     */
    public Conditioner(Compressor compressor, Fan fan, Filter filter, String brand) {
        this.compressor = compressor;
        this.fan = fan;
        this.filter = filter;
        this.brand = brand;
        this.isOn = false;
        this.targetTemperature = 24;
        this.mode = "COOLING";
        this.airDirection = "AUTO";
        this.sleepMode = false;
        this.currentTemperature = 26;
    }

    // Конструктор 3: Повний конструктор
    /**
     * Повний конструктор з усіма параметрами
     * 
     * @param compressor компресор
     * @param fan вентилятор
     * @param filter фільтр
     * @param brand бренд
     * @param targetTemperature цільова температура
     * @param mode режим роботи
     */
    public Conditioner(Compressor compressor, Fan fan, Filter filter, 
                      String brand, int targetTemperature, String mode) {
        this.compressor = compressor;
        this.fan = fan;
        this.filter = filter;
        this.brand = brand;
        this.isOn = false;
        this.targetTemperature = targetTemperature;
        this.mode = mode;
        this.airDirection = "AUTO";
        this.sleepMode = false;
        this.currentTemperature = 26;
    }

    // МЕТОД 1: Увімкнення кондиціонера
    /**
     * Увімкнення кондиціонера
     * ВЕДЕ ПРОТОКОЛ У ФАЙЛ через logger
     * 
     * @param logger об'єкт для логування
     * @throws IOException при помилці запису в файл
     */
    public void turnOn(Logger logger) throws IOException {
        if (!isOn) {
            isOn = true;
            logger.log("Кондицiонер увiмкнено. Бренд: " + brand);
            compressor.start(logger);
            fan.start(logger);
        } else {
            logger.log("Кондицiонер вже увiмкнений");
        }
    }

    // МЕТОД 2: Вимкнення кондиціонера
    /**
     * Вимкнення кондиціонера
     * 
     * @param logger об'єкт для логування
     * @throws IOException при помилці запису в файл
     */
    public void turnOff(Logger logger) throws IOException {
        if (isOn) {
            isOn = false;
            sleepMode = false;
            logger.log("Кондицiонер вимкнено");
            compressor.stop(logger);
            fan.stop(logger);
        } else {
            logger.log("Кондицiонер вже вимкнений");
        }
    }

    // МЕТОД 3: Встановлення цільової температури
    /**
     * Встановлення цільової температури
     * 
     * @param temperature температура в градусах Цельсія
     * @param logger об'єкт для логування
     * @throws IOException при помилці запису в файл
     */
    public void setTargetTemperature(int temperature, Logger logger) throws IOException {
        if (temperature >= 16 && temperature <= 30) {
            this.targetTemperature = temperature;
            logger.log("Встановлено цiльову температуру: " + temperature + "°C");
            
            if (isOn) {
                adjustCompressor(logger);
            }
        } else {
            logger.log("Помилка: температура має бути в дiапазонi 16-30°C");
        }
    }

    // МЕТОД 4: Встановлення режиму роботи
    /**
     * Встановлення режиму роботи кондиціонера
     * 
     * @param mode режим (COOLING, HEATING, FAN, AUTO)
     * @param logger об'єкт для логування
     * @throws IOException при помилці запису в файл
     */
    public void setMode(String mode, Logger logger) throws IOException {
        String upperMode = mode.toUpperCase();
        if (upperMode.equals("COOLING") || upperMode.equals("HEATING") || 
            upperMode.equals("FAN") || upperMode.equals("AUTO")) {
            this.mode = upperMode;
            logger.log("Встановлено режим: " + upperMode);
            
            if (isOn) {
                adjustCompressor(logger);
            }
        } else {
            logger.log("Помилка: невiдомий режим " + mode);
        }
    }

    // МЕТОД 5: Зміна швидкості вентилятора
    /**
     * Зміна швидкості вентилятора
     * 
     * @param speed швидкість (1-5)
     * @param logger об'єкт для логування
     * @throws IOException при помилці запису в файл
     */
    public void changeFanSpeed(int speed, Logger logger) throws IOException {
        fan.setSpeed(speed, logger);
    }

    // МЕТОД 6: Встановлення напрямку повітря
    /**
     * Встановлення напрямку повітря
     * 
     * @param direction напрямок (HORIZONTAL, VERTICAL, AUTO)
     * @param logger об'єкт для логування
     * @throws IOException при помилці запису в файл
     */
    public void setAirDirection(String direction, Logger logger) throws IOException {
        String upperDirection = direction.toUpperCase();
        if (upperDirection.equals("HORIZONTAL") || upperDirection.equals("VERTICAL") || 
            upperDirection.equals("AUTO")) {
            this.airDirection = upperDirection;
            logger.log("Встановлено напрямок повiтря: " + upperDirection);
        } else {
            logger.log("Помилка: невiдомий напрямок " + direction);
        }
    }

    // МЕТОД 7: Увімкнення режиму сну
    /**
     * Увімкнення режиму сну (тихий режим з автоматичним регулюванням температури)
     * 
     * @param logger об'єкт для логування
     * @throws IOException при помилці запису в файл
     */
    public void enableSleepMode(Logger logger) throws IOException {
        if (isOn) {
            sleepMode = true;
            fan.setSpeed(1, logger);
            logger.log("Увiмкнено режим сну");
        } else {
            logger.log("Помилка: кондицiонер має бути увiмкненим");
        }
    }

    // МЕТОД 8: Вимкнення режиму сну
    /**
     * Вимкнення режиму сну
     * 
     * @param logger об'єкт для логування
     * @throws IOException при помилці запису в файл
     */
    public void disableSleepMode(Logger logger) throws IOException {
        if (sleepMode) {
            sleepMode = false;
            logger.log("Вимкнено режим сну");
        }
    }

    // МЕТОД 9: Перевірка стану фільтра
    /**
     * Перевірка стану фільтра
     * 
     * @param logger об'єкт для логування
     * @throws IOException при помилці запису в файл
     */
    public void checkFilter(Logger logger) throws IOException {
        filter.checkStatus(logger);
        if (filter.needsCleaning()) {
            logger.log("УВАГА: Фiльтр потребує очищення!");
        }
    }

    // МЕТОД 10: Очищення фільтра
    /**
     * Очищення фільтра
     * 
     * @param logger об'єкт для логування
     * @throws IOException при помилці запису в файл
     */
    public void cleanFilter(Logger logger) throws IOException {
        filter.clean(logger);
    }

    // МЕТОД 11: Діагностика системи
    /**
     * Запуск діагностики всіх систем кондиціонера
     * 
     * @param logger об'єкт для логування
     * @throws IOException при помилці запису в файл
     */
    public void runDiagnostics(Logger logger) throws IOException {
        logger.log("=== Запуск дiагностики системи ===");
        logger.log("Статус: " + (isOn ? "Увiмкнений" : "Вимкнений"));
        logger.log("Режим: " + mode);
        logger.log("Цiльова температура: " + targetTemperature + "°C");
        logger.log("Поточна температура: " + currentTemperature + "°C");
        logger.log("Режим сну: " + (sleepMode ? "Увiмкнений" : "Вимкнений"));
        
        compressor.checkStatus(logger);
        fan.checkStatus(logger);
        filter.checkStatus(logger);
        
        logger.log("=== Дiагностика завершена ===");
    }

    // МЕТОД 12: Налаштування компресора (допоміжний)
    /**
     * Налаштування роботи компресора залежно від режиму
     * 
     * @param logger об'єкт для логування
     * @throws IOException при помилці запису в файл
     */
    private void adjustCompressor(Logger logger) throws IOException {
        if (mode.equals("FAN")) {
            compressor.stop(logger);
        } else {
            compressor.adjustPower(calculateRequiredPower(), logger);
        }
    }

    // Допоміжний метод розрахунку потужності
    private int calculateRequiredPower() {
        int tempDiff = Math.abs(currentTemperature - targetTemperature);
        return compressor.getBasePower() * tempDiff / 10;
    }

    // Геттери
    public Compressor getCompressor() {
        return compressor;
    }

    public Fan getFan() {
        return fan;
    }

    public Filter getFilter() {
        return filter;
    }

    public boolean isOn() {
        return isOn;
    }

    public int getTargetTemperature() {
        return targetTemperature;
    }

    public String getMode() {
        return mode;
    }

    @Override
    public String toString() {
        return "Кондицiонер " + brand + ":\n" +
               "  Статус: " + (isOn ? "Увiмкнений" : "Вимкнений") + "\n" +
               "  Режим: " + mode + "\n" +
               "  Цiльова температура: " + targetTemperature + "°C\n" +
               "  Поточна температура: " + currentTemperature + "°C\n" +
               "  Напрямок повiтря: " + airDirection + "\n" +
               "  Режим сну: " + (sleepMode ? "Увiмкнений" : "Вимкнений") + "\n" +
               "  " + compressor + "\n" +
               "  " + fan + "\n" +
               "  " + filter;
    }
}