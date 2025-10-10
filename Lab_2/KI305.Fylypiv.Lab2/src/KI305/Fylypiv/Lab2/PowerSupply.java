package KI305.Fylypiv.Lab2;

/**
 * Клас, що описує джерело живлення кондиціонера
 */
public class PowerSupply {
    private String voltage;
    private boolean powerAvailable;

    public PowerSupply(String voltage, boolean powerAvailable) {
        this.voltage = voltage;
        this.powerAvailable = powerAvailable;
    }

    public boolean hasPower() {
        return powerAvailable;
    }

    public String getVoltage() {
        return voltage;
    }
}
