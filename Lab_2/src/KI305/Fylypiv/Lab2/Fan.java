package KI305.Fylypiv.Lab2;

import java.io.IOException;

/**
 * Клас, що описує вентилятор кондиціонера
 * Складова частина 2 предметної області
 * 
 * @author Fylypiv
 * @version 1.0
 */
public class Fan {
    private int speed; // Швидкість від 1 до 5
    private boolean isRunning;
    private int rotationSpeed; // Обертів за хвилину

    /**
     * Конструктор за замовчуванням
     */
    public Fan() {
        this.speed = 3;
        this.isRunning = false;
        this.rotationSpeed = 0;
    }

    /**
     * Конструктор з параметром швидкості
     * 
     * @param speed швидкість вентилятора (1-5)
     */
    public Fan(int speed) {
        this.speed = Math.max(1, Math.min(5, speed));
        this.isRunning = false;
        this.rotationSpeed = 0;
    }

    /**
     * Запуск вентилятора
     * 
     * @param logger об'єкт для логування
     * @throws IOException при помилці запису в файл
     */
    public void start(Logger logger) throws IOException {
        if (!isRunning) {
            isRunning = true;
            rotationSpeed = speed * 200; // 200 об/хв на кожен рівень швидкості
            logger.log("Вентилятор запущено. Швидкiсть: " + speed + 
                      " (" + rotationSpeed + " об/хв)");
        }
    }

    /**
     * Зупинка вентилятора
     * 
     * @param logger об'єкт для логування
     * @throws IOException при помилці запису в файл
     */
    public void stop(Logger logger) throws IOException {
        if (isRunning) {
            isRunning = false;
            rotationSpeed = 0;
            logger.log("Вентилятор зупинено");
        }
    }

    /**
     * Встановлення швидкості вентилятора
     * 
     * @param speed швидкість (1-5)
     * @param logger об'єкт для логування
     * @throws IOException при помилці запису в файл
     */
    public void setSpeed(int speed, Logger logger) throws IOException {
        if (speed >= 1 && speed <= 5) {
            this.speed = speed;
            if (isRunning) {
                rotationSpeed = speed * 200;
                logger.log("Швидкiсть вентилятора змiнено на: " + speed + 
                          " (" + rotationSpeed + " об/хв)");
            } else {
                logger.log("Швидкiсть вентилятора встановлено на: " + speed);
            }
        } else {
            logger.log("Помилка: швидкiсть має бути вiд 1 до 5");
        }
    }

    /**
     * Перевірка стану вентилятора
     * 
     * @param logger об'єкт для логування
     * @throws IOException при помилці запису в файл
     */
    public void checkStatus(Logger logger) throws IOException {
        logger.log("Вентилятор - Статус: " + (isRunning ? "Працює" : "Зупинений") + 
                  ", Швидкiсть: " + speed + "/5" +
                  (isRunning ? " (" + rotationSpeed + " об/хв)" : ""));
    }

    // Геттери
    public int getSpeed() {
        return speed;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public int getRotationSpeed() {
        return rotationSpeed;
    }

    @Override
    public String toString() {
        return "Вентилятор: швидкiсть " + speed + "/5" + 
               (isRunning ? " (працює, " + rotationSpeed + " об/хв)" : " (зупинений)");
    }
}