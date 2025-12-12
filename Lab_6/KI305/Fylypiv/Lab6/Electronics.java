package KI305.Fylypiv.Lab6;

/**
 * Клас "Електронiка" для демонстрацiї роботи параметризованого класу Warehouse.
 * Представляє електронний товар з назвою, цiною, вагою та термiном гарантiї.
 * 
 * @author Fylypiv
 * @version 1.1
 */
public class Electronics extends Product {
    private static final String NEGATIVE_WARRANTY_ERROR = "Термiн гарантiї не може бути вiд'ємним";
    private static final String CATEGORY_NAME = "Електронiка";
    
    private final int warrantyMonths;
    
    /**
     * Конструктор класу Electronics
     * 
     * @param name назва товару
     * @param price цiна товару в гривнях
     * @param weight вага товару в кiлограмах
     * @param warrantyMonths термiн гарантiї в мiсяцях
     * @throws IllegalArgumentException якщо термiн гарантiї вiд'ємний
     */
    public Electronics(String name, double price, double weight, int warrantyMonths) {
        super(name, price, weight);
        if (warrantyMonths < 0) {
            throw new IllegalArgumentException(NEGATIVE_WARRANTY_ERROR);
        }
        this.warrantyMonths = warrantyMonths;
    }
    
    public int getWarrantyMonths() {
        return warrantyMonths;
    }
    
    @Override
    public String getCategory() {
        return CATEGORY_NAME;
    }
    
    /**
     * Використовує батькiвський toString (super.toString()) i додає iнформацiю про гарантiю.
     */
    @Override
    public String toString() {
        return String.format("%s, гарантiя: %d мiс.", super.toString(), warrantyMonths);
    }
}
