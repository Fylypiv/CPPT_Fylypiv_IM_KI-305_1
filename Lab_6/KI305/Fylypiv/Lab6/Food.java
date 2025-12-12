package KI305.Fylypiv.Lab6;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Клас "Продукти харчування" для демонстрацiї роботи параметризованого класу Warehouse.
 * Представляє продукт харчування з назвою, цiною, вагою та термiном придатностi.
 * 
 * @author Fylypiv
 * @version 1.1
 */
public class Food extends Product {
    private static final String NULL_DATE_ERROR = "Термiн придатностi не може бути null";
    private static final String CATEGORY_NAME = "Продукти";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    
    private final LocalDate expirationDate;
    
    /**
     * Конструктор класу Food
     * 
     * @param name назва продукту
     * @param price цiна продукту в гривнях
     * @param weight вага продукту в кiлограмах
     * @param expirationDate термiн придатностi
     * @throws IllegalArgumentException якщо дата null
     */
    public Food(String name, double price, double weight, LocalDate expirationDate) {
        super(name, price, weight);
        if (expirationDate == null) {
            throw new IllegalArgumentException(NULL_DATE_ERROR);
        }
        this.expirationDate = expirationDate;
    }
    
    public LocalDate getExpirationDate() {
        return expirationDate;
    }
    
    /**
     * Перевiряє чи прострочений продукт
     * Порiвнює дату придатностi з поточною датою (LocalDate.now()).
     * @return true якщо продукт прострочений, false iнакше
     */
    public boolean isExpired() {
        return LocalDate.now().isAfter(expirationDate);
    }
    
    @Override
    public String getCategory() {
        return CATEGORY_NAME;
    }
    
    @Override
    public String toString() {
        return String.format("%s, придатний до: %s", 
            super.toString(), expirationDate.format(DATE_FORMATTER));
    }
}