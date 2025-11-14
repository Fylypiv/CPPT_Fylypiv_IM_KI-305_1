package KI305.Fylypiv.Lab3;

/**
 * Абстрактний клас {@code Conditioner} описує базовi властивостi
 * кондицiонеру.
 */
public abstract class Conditioner {
    protected String model;
    protected double power;

    /**
     * Конструктор базового кондицiонеру.
     *
     * @param model модель
     * @param power номiнальна потужнiсть (Вт)
     */
    public Conditioner(String model, double power) {
        this.model = model;
        this.power = power;
    }

    // Абстрактний метод увiмкнення
    public abstract void turnOn();

    // Абстрактний метод вимкнення
    public abstract void turnOff();

    @Override
    public String toString() {
        return String.format(
        "Модель: %s \nПотужнiсть: %s Вт", model, power);
    }
}
