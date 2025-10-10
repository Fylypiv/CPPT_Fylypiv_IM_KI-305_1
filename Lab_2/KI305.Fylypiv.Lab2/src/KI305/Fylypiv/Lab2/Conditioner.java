package KI305.Fylypiv.Lab2;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Клас, що моделює кондиціонер з його основними складовими.
 * Веде протокол роботи у файл "conditioner_log.txt".
 *
 * @author Fylypiv
 * @version 1.0
 * @since 2025-10-09
 */
public class Conditioner implements AutoCloseable {
    private PowerSupply powerSupply;
    private RemoteControl remoteControl;
    private Component indoorUnit;
    private boolean isOn;
    private double temperature;
    private FileWriter logWriter;

    /**
     * Конструктор за замовчуванням
     */
    public Conditioner() {
        this.powerSupply = new PowerSupply("220V", true);
        this.remoteControl = new RemoteControl("Samsung", true);
        this.indoorUnit = new Component("Іонізатор", true);
        this.isOn = false;
        this.temperature = 22.0;
        initLog();
    }

    /**
     * Конструктор з параметрами
     */
    public Conditioner(PowerSupply ps, RemoteControl rc, Component unit) {
        this.powerSupply = ps;
        this.remoteControl = rc;
        this.indoorUnit = unit;
        this.isOn = false;
        this.temperature = 22.0;
        initLog();
    }

    /**
     * Ініціалізація лог-файлу
     */
    private void initLog() {
        try {
            logWriter = new FileWriter("conditioner_log.txt", true);
            log("=== Старт роботи кондиціонера ===");
        } catch (IOException e) {
            System.out.println("Помилка створення файлу журналу: " + e.getMessage());
        }
    }

    /**
     * Запис повідомлення у лог-файл
     */
    private void log(String message) {
        try {
            if (logWriter != null) {
                logWriter.write(LocalDateTime.now() + " | " + message + "\n");
            }
        } catch (IOException e) {
            System.out.println("Помилка запису у лог: " + e.getMessage());
        }
    }

    public void turnOn() {
        if (!isOn && powerSupply.hasPower()) {
            isOn = true;
            log("Кондиціонер увімкнено");
        }
    }

    public void turnOff() {
        if (isOn) {
            isOn = false;
            log("Кондиціонер вимкнено");
        }
    }

    public void increaseTemperature() {
        temperature++;
        log("Температура збільшена до " + temperature);
    }

    public void decreaseTemperature() {
        temperature--;
        log("Температура зменшена до " + temperature);
    }

    public void enableIonizer() {
        indoorUnit.setActive(true);
        log("Іонізатор увімкнено");
    }

    public void disableIonizer() {
        indoorUnit.setActive(false);
        log("Іонізатор вимкнено");
    }

    public void showStatus() {
        System.out.println("\n=== Стан кондиціонера ===");
        System.out.println("Живлення: " + (powerSupply.hasPower() ? "є" : "нема"));
        System.out.println("Пульт: " + (remoteControl.isWorking() ? "працює" : "несправний"));
        System.out.println("Іонізатор: " + (indoorUnit.isActive() ? "увімкнено" : "вимкнено"));
        System.out.println("Температура: " + temperature + "°C");
        System.out.println("Стан: " + (isOn ? "Увімкнено" : "Вимкнено"));
        log("Виведено стан кондиціонера");
    }

    /**
     * Коректне закриття ресурсу
     */
    @Override
    public void close() {
        try {
            if (logWriter != null) {
                log("=== Завершення роботи кондиціонера ===");
                logWriter.close();
            }
        } catch (IOException e) {
            System.out.println("Помилка при закритті лог-файлу: " + e.getMessage());
        }
    }
}
