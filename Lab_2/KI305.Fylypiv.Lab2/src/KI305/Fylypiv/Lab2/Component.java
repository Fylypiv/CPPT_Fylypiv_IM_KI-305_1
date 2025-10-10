package KI305.Fylypiv.Lab2;

/**
 * Клас, що описує складову частину кондиціонера (наприклад, іонізатор або фільтр)
 */
public class Component {
    private String name;
    private boolean active;

    public Component(String name, boolean active) {
        this.name = name;
        this.active = active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public String getName() {
        return name;
    }
}
