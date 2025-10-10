package KI305.Fylypiv.Lab2;

/**
 * Клас-драйвер для демонстрації роботи класу Conditioner
 */
public class ConditionerTest {
    public static void main(String[] args) {
        try (Conditioner cond = new Conditioner()) {
            cond.turnOn();
            cond.showStatus();
            cond.increaseTemperature();
            cond.enableIonizer();
            cond.showStatus();
            cond.decreaseTemperature();
            cond.turnOff();
        }
    }
}
