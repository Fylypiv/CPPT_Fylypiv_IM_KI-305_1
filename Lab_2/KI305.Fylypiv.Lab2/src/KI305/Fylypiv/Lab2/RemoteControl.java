package KI305.Fylypiv.Lab2;

/**
 * Клас, що описує пульт керування кондиціонером
 */
public class RemoteControl {
    private String brand;
    private boolean working;

    public RemoteControl(String brand, boolean working) {
        this.brand = brand;
        this.working = working;
    }

    public boolean isWorking() {
        return working;
    }

    public String getBrand() {
        return brand;
    }
}
