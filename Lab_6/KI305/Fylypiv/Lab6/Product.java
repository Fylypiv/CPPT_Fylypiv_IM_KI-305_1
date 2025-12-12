package KI305.Fylypiv.Lab6;

/**
 * Абстрактний клас "Товар". Є фундаментом для всiєї iєрархiї.
 * Реалiзує iнтерфейс Comparable<Product>, щоб товари можна було порiвнювати за цiною.
 * * @author Fylypiv
 * @version 1.1
 */
public abstract class Product implements Comparable<Product> {
    private static final String NEGATIVE_PRICE_ERROR = "Цiна не може бути вiд'ємною";
    private static final String NEGATIVE_WEIGHT_ERROR = "Вага не може бути вiд'ємною";
    
    private final String name;
    private final double price;
    private final double weight;
    
    /**
     * Конструктор базового класу.
     * Перевiряє вхiднi данi: цiна та вага не можуть бути вiд'ємними.
     */
    public Product(String name, double price, double weight) {
        if (price < 0) {
            throw new IllegalArgumentException(NEGATIVE_PRICE_ERROR);
        }
        if (weight < 0) {
            throw new IllegalArgumentException(NEGATIVE_WEIGHT_ERROR);
        }
        this.name = name;
        this.price = price;
        this.weight = weight;
    }
    
    public String getName() {
        return name;
    }
    
    public double getPrice() {
        return price;
    }
    
    public double getWeight() {
        return weight;
    }
    
    /**
     * Метод з iнтерфейсу Comparable.
     * Дозволяє сортувати товари або шукати мiнiмум/максимум.
     * @return >0 якщо цей товар дорожчий, <0 якщо дешевший, 0 якщо рiвнi.
     */
    @Override
    public int compareTo(Product other) {
        return Double.compare(this.price, other.price);
    }
    
    // Абстрактний метод: кожен тип товару (Food/Electronics) сам знає, яка у нього категорiя
    public abstract String getCategory();
    
    /**
     * Формує текстове представлення об'єкта.
     * Використовує %.2f для виведення чисел з 2 знаками пiсля коми.
     */
    @Override
    public String toString() {
        return String.format("%s: %s (%.2f грн, %.2f кг)", 
            getCategory(), name, price, weight);
    }
    
    /**
     * Перевiряє рiвнiсть об'єктiв за вмiстом (назва, цiна, вага), а не за посиланням.
     * Потрiбен для коректного пошуку товару в списках.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Це той самий об'єкт у пам'ятi
        if (obj == null || getClass() != obj.getClass()) return false; // Перевiрка типiв
        
        Product product = (Product) obj;
        
        // Порiвнюємо всi поля
        return Double.compare(product.price, price) == 0 
            && Double.compare(product.weight, weight) == 0 
            && name.equals(product.name);
    }
    
    /**
     * Генерує унiкальний код для об'єкта.
     */
    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + Double.hashCode(price);
        result = 31 * result + Double.hashCode(weight);
        return result;
    }
}