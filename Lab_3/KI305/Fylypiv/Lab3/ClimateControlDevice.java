package KI305.Fylypiv.Lab3;

/**
 * Клас {@code ClimateControlDevice} представляє систему клiматконтролю,
 * який розширює {@link Conditioner} та реалiзує {@link Controllable}.
 */
public class ClimateControlDevice extends Conditioner implements Controllable {

    private double temperature;
    private double humidity;

    /**
     * Конструктор системи клiматконтролю.
     *
     * @param model модель
     * @param power потужнiсть (Вт)
     */
    public ClimateControlDevice(String model, double power) {
        super(model, power);
        this.temperature = 22;
        this.humidity = 40;
    }

    @Override
    public void turnOn() {
        System.out.println("Система клiматконтролю увiмкнена.");
    }

    @Override
    public void turnOff() {
        System.out.println("Система клiматконтролю вимкнена.");
    }

    @Override
    public void setTemperature(double value) {
        this.temperature = value;
        System.out.printf("Температура встановлена на %.1f°C.\n", value);
    }

    @Override
    public void setHumidity(double value) {
        this.humidity = value;
        System.out.printf("Вологiсть встановлена на %.1f%%\n", value);
    }

    @Override
    public String toString() {
    return String.format(
        "Система клiматконтролю:\n%s \nТемпература: %.1f°C \nВологiсть: %.1f%%",
        super.toString(),
        temperature,
        humidity
);
    }
}
