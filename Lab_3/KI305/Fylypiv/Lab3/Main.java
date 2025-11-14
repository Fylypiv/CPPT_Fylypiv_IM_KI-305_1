package KI305.Fylypiv.Lab3;

/**
 * Демонстрацiйний клас для перевiрки роботи {@link ClimateControlDevice}.
 */
public class Main {
    public static void main(String[] args) {

        ClimateControlDevice cond = new ClimateControlDevice("Samsung A500", 1200);

        System.out.println(cond);

        cond.turnOn();
        cond.setTemperature(24);
        cond.setHumidity(55);
        cond.turnOff();

        System.out.println(cond);
    }
}
