package KI305.Fylypiv.Lab6;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Параметризований клас-контейнер "Сховище" для зберiгання товарiв.
 * Реалiзує основнi операцiї з товарами: додавання, вилучення, пошук максимального.
 * 
 * @param <T> тип товарiв, якi зберiгаються у сховищi (має успадковуватись вiд Product)
 * @author Fylypiv
 * @version 1.1
 */
public class Warehouse<T extends Product> implements Iterable<T> {
    private static final String MAX_WEIGHT_ERROR = "Максимальна вага має бути бiльше 0";
    private static final String NULL_PRODUCT_ERROR = "Не можна додати null товар";
    private static final String INVALID_INDEX_ERROR = "Недiйсний iндекс: ";
    private static final double DEFAULT_MAX_WEIGHT = 1000.0;
    private static final String DEFAULT_NAME = "Сховище";
    
    private final ArrayList<T> products;
    private final double maxWeight;
    private final String name;
    
    /**
     * Конструктор з параметрами
     * 
     * @param name назва сховища
     * @param maxWeight максимальна вага сховища в кiлограмах
     * @throws IllegalArgumentException якщо максимальна вага менша або дорiвнює 0
     */
    public Warehouse(String name, double maxWeight) {
        if (maxWeight <= 0) {
            throw new IllegalArgumentException(MAX_WEIGHT_ERROR);
        }
        this.name = name;
        this.maxWeight = maxWeight;
        this.products = new ArrayList<>();
    }
    
    /**
     * Конструктор за замовчуванням (максимальна вага 1000 кг)
     */
    public Warehouse() {
        this(DEFAULT_NAME, DEFAULT_MAX_WEIGHT);
    }
    
    /**
     * Додає товар до сховища
     * 
     * @param product товар для додавання
     * @return true якщо товар успiшно додано, false якщо перевищено вагу
     * @throws IllegalArgumentException якщо товар null
     */
    public boolean add(T product) {
        if (product == null) {
            throw new IllegalArgumentException(NULL_PRODUCT_ERROR);
        }
        if (getCurrentWeight() + product.getWeight() > maxWeight) {
            return false;
        }
        return products.add(product);
    }
    
    /**
     * Вилучає товар за iндексом
     * 
     * @param index iндекс товару для вилучення
     * @return вилучений товар
     * @throws IndexOutOfBoundsException якщо iндекс недiйсний
     */
    public T remove(int index) {
        validateIndex(index);
        return products.remove(index);
    }
    
    /**
     * Вилучає перший знайдений товар
     * 
     * @param product товар для вилучення
     * @return true якщо товар знайдено та вилучено, false iнакше
     */
    public boolean remove(T product) {
        return products.remove(product);
    }
    
    /**
     * Знаходить максимальний товар за цiною
     * 
     * @return найдорожчий товар або null якщо сховище порожнє
     */
    public T findMax() {
        if (isEmpty()) {
            return null;
        }
        
        T max = products.get(0);
        for (int i = 1; i < products.size(); i++) {
            T current = products.get(i);
            if (current.compareTo(max) > 0) {
                max = current;
            }
        }
        return max;
    }
    
    /**
     * Отримує товар за iндексом без вилучення
     * 
     * @param index iндекс товару
     * @return товар за вказаним iндексом
     * @throws IndexOutOfBoundsException якщо iндекс недiйсний
     */
    public T get(int index) {
        validateIndex(index);
        return products.get(index);
    }
    
    private void validateIndex(int index) {
        if (index < 0 || index >= products.size()) {
            throw new IndexOutOfBoundsException(INVALID_INDEX_ERROR + index);
        }
    }
    
    public boolean isEmpty() {
        return products.isEmpty();
    }
    
    /**
     * Отримує поточну вагу всiх товарiв у сховищi
     * 
     * @return сумарна вага товарiв в кiлограмах
     */
    public double getCurrentWeight() {
        double totalWeight = 0;
        for (T product : products) {
            totalWeight += product.getWeight();
        }
        return totalWeight;
    }
    
    /**
     * Отримує загальну вартiсть всiх товарiв
     * 
     * @return сумарна вартiсть товарiв в гривнях
     */
    public double getTotalValue() {
        double totalValue = 0;
        for (T product : products) {
            totalValue += product.getPrice();
        }
        return totalValue;
    }
    
    public int size() {
        return products.size();
    }
    
    public double getMaxWeight() {
        return maxWeight;
    }
    
    public String getName() {
        return name;
    }
    
    public void clear() {
        products.clear();
    }
    
    @Override
    public Iterator<T> iterator() {
        return products.iterator();
    }
    
    /**
     * Повертає рядкове представлення сховища з товарами в окремих рядках
     * 
     * @return рядок з iнформацiєю про вмiст сховища
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Сховище \"%s\" [%.2f/%.2f кг, товарiв: %d]:%n", 
                  name, getCurrentWeight(), maxWeight, products.size()));
        
        if (isEmpty()) {
            sb.append("  порожнє");
        } else {
            for (int i = 0; i < products.size(); i++) {
                sb.append(String.format("  %d. %s", i + 1, products.get(i)));
                if (i < products.size() - 1) {
                    sb.append(System.lineSeparator());
                }
            }
        }
        return sb.toString();
    }
}