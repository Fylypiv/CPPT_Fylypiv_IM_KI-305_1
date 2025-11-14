package KI305.Fylypiv.Lab3;

/**
 * iнтерфейс {@code Controllable} описує здатнiсть пристрою
 * змiнювати робочi параметри.
 */
public interface Controllable {

    /**
     * Метод для встановлення температури.
     *
     * @param value значення температури
     */
    void setTemperature(double value);

    /**
     * Метод для встановлення вологостi.
     *
     * @param value значення вологостi у %
     */
    void setHumidity(double value);
}
