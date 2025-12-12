package KI305.Fylypiv.Lab6;

import java.time.LocalDate;

/**
 * Програма-драйвер для тестування параметризованого класу Warehouse.
 * Демонструє роботу з рiзними типами товарiв (Electronics та Food).
 * 
 * @author Fylypiv
 * @version 1.1
 */
public class WarehouseDriver {
    private static final String SUCCESS_MESSAGE = "Успiшно";
    private static final String WEIGHT_LIMIT_MESSAGE = "Неможливо - перевищено лiмiт ваги";
    
    /**
     * Головний метод програми
     * 
     * @param args аргументи командного рядка (не використовуються)
     */
    public static void main(String[] args) {
        System.out.println("=== Сховище товарiв ===\n");
        
        testElectronicsWarehouse();
        System.out.println("\n=================================================\n");
        
        testFoodWarehouse();
        System.out.println("\n=================================================\n");
        
        testWarehouseOfWarehouses();
    }
    
    /**
     * Тестує роботу зi сховищем електронiки
     */
    private static void testElectronicsWarehouse() {
        System.out.println(">>> Тест 1: Сховище електронiки <<<\n");
        
        Warehouse<Electronics> warehouse = new Warehouse<>("Електронiка", 50.0);
        System.out.printf("Створено сховище для електронiки (макс. вага: %.1f кг)\n\n", 
                         warehouse.getMaxWeight());
        
        System.out.println("Додаємо електроннi товари:");
        addElectronicsItems(warehouse);
        
        System.out.printf("\n%s\n\n", warehouse);
        
        testWeightLimit(warehouse);
        
        Electronics maxElectronics = warehouse.findMax();
        System.out.printf("Найдорожчий товар: %s\n\n", maxElectronics);
        
        System.out.println("Вилучаємо товар за iндексом 2:");
        Electronics removed = warehouse.remove(2);
        System.out.printf("Вилучено: %s%n%n", removed);
        System.out.println(warehouse);
        
        printWarehouseStatistics(warehouse);
    }
    
    private static void addElectronicsItems(Warehouse<Electronics> warehouse) {
        Electronics[] items = {
            new Electronics("Ноутбук Lenovo", 25000, 2.5, 24),
            new Electronics("Смартфон Samsung", 15000, 0.2, 12),
            new Electronics("Планшет iPad", 18000, 0.5, 12),
            new Electronics("Навушники Sony", 3500, 0.3, 6),
            new Electronics("Монiтор Dell", 12000, 5.0, 36)
        };
        
        for (Electronics item : items) {
            warehouse.add(item);
            System.out.printf("  + %s%n", item);
        }
    }
    
    private static void testWeightLimit(Warehouse<Electronics> warehouse) {
        System.out.println("Спроба додати товар до майже заповненого сховища:");
        boolean added = warehouse.add(new Electronics("Холодильник", 18000, 50.0, 60));
        System.out.printf("Результат: %s%n%n", added ? SUCCESS_MESSAGE : WEIGHT_LIMIT_MESSAGE);
    }
    
    private static void printWarehouseStatistics(Warehouse<?> warehouse) {
        System.out.println("\nОтримання статистики:");
        System.out.printf("  Кiлькiсть товарiв: %d%n", warehouse.size());
        System.out.printf("  Поточна вага: %.2f кг%n", warehouse.getCurrentWeight());
        System.out.printf("  Загальна вартiсть: %.2f грн%n", warehouse.getTotalValue());
    }
    
    /**
     * Тестує роботу зi сховищем продуктiв харчування
     */
    private static void testFoodWarehouse() {
        System.out.println(">>> Тест 2: Сховище продуктiв харчування <<<\n");
        
        Warehouse<Food> warehouse = new Warehouse<>();
        System.out.printf("Створено сховище для продуктiв (макс. вага за замовчуванням: %.1f кг)%n%n",
                         warehouse.getMaxWeight());
        
        System.out.println("Додаємо продукти харчування:");
        addFoodItems(warehouse);
        
        System.out.printf("%n%s%n%n", warehouse);
        
        Food maxFood = warehouse.findMax();
        System.out.printf("Найдорожчий продукт: %s%n%n", maxFood);
        
        checkExpiredProducts(warehouse);
        
        Food toRemove = new Food("Хлiб", 25, 0.5, LocalDate.of(2025, 12, 1));
        System.out.printf("Вилучаємо продукт: %s%n", toRemove);
        boolean removed = warehouse.remove(toRemove);
        System.out.printf("Результат: %s%n%n", removed ? SUCCESS_MESSAGE : "Не знайдено");
        System.out.println(warehouse);
        
        System.out.println("\nОчищаємо сховище...");
        warehouse.clear();
        System.out.println(warehouse);
        System.out.printf("%nСховище порожнє? %s%n", warehouse.isEmpty());
    }
    
    private static void addFoodItems(Warehouse<Food> warehouse) {
        Food[] items = {
            new Food("Молоко", 45, 1.0, LocalDate.of(2025, 12, 5)),
            new Food("Хлiб", 25, 0.5, LocalDate.of(2025, 12, 1)),
            new Food("Сир", 120, 0.3, LocalDate.of(2025, 12, 15)),
            new Food("Яблука", 60, 2.0, LocalDate.of(2026, 1, 10)),
            new Food("Шоколад", 85, 0.1, LocalDate.of(2026, 6, 1))
        };
        
        for (Food item : items) {
            warehouse.add(item);
            System.out.printf("  + %s%n", item);
        }
    }
    
    private static void checkExpiredProducts(Warehouse<Food> warehouse) {
        System.out.println("Перевiрка прострочених продуктiв:");
        boolean hasExpired = false;
        for (Food food : warehouse) {
            if (food.isExpired()) {
                System.out.printf("  ⚠ Прострочений: %s%n", food.getName());
                hasExpired = true;
            }
        }
        if (!hasExpired) {
            System.out.println("  ✓ Всi продукти свiжi");
        }
        System.out.println();
    }
    
    /**
     * Тестує роботу зi змiшаним сховищем (рiзнi типи товарiв)
     */
    private static void testWarehouseOfWarehouses() {
        System.out.println(">>> Тест 3: Змiшане сховище (рiзнi типи товарiв) <<<\n");
        
        Warehouse<Product> warehouse = new Warehouse<>("Змiшане сховище", 100.0);
        System.out.println("Створено змiшане сховище для рiзних типiв товарiв\n");
        
        System.out.println("Додаємо рiзнi типи товарiв:");
        addMixedProducts(warehouse);
        
        System.out.printf("%n%s%n%n", warehouse);
        
        displayProductsByCategory(warehouse);
        
        Product maxProduct = warehouse.findMax();
        System.out.printf("%n%nПошук найдорожчого товару:%nНайдорожчий товар: %s%n", maxProduct);
        
        displayCategoryStatistics(warehouse);
        displayOverallStatistics(warehouse);
    }
    
    private static void addMixedProducts(Warehouse<Product> warehouse) {
        Product[] products = {
            new Electronics("Телевiзор LG", 22000, 15.0, 24),
            new Food("Кава", 250, 0.5, LocalDate.of(2026, 12, 31)),
            new Electronics("Клавiатура", 1200, 0.8, 12),
            new Food("Чай", 180, 0.2, LocalDate.of(2026, 8, 15)),
            new Electronics("Миша Logitech", 800, 0.15, 12),
            new Food("Мед", 320, 0.5, LocalDate.of(2026, 3, 20)),
            new Food("Цукор", 40, 1.0, LocalDate.of(2027, 1, 1))
        };
        
        for (Product product : products) {
            warehouse.add(product);
            System.out.printf("  + %s%n", product);
        }
    }
    
    private static void displayProductsByCategory(Warehouse<Product> warehouse) {
        System.out.println("Вмiст сховища за категорiями:");
        
        System.out.println("\n  Електронiка:");
        for (Product product : warehouse) {
            if (product instanceof Electronics) {
                System.out.printf("    - %s%n", product);
            }
        }
        
        System.out.println("\n  Продукти харчування:");
        for (Product product : warehouse) {
            if (product instanceof Food) {
                System.out.printf("    - %s%n", product);
            }
        }
    }
    
    private static void displayCategoryStatistics(Warehouse<Product> warehouse) {
        System.out.println("\n\nСтатистика за категорiями:");
        
        int electronicsCount = 0;
        double electronicsValue = 0;
        int foodCount = 0;
        double foodValue = 0;
        
        for (Product product : warehouse) {
            if (product instanceof Electronics) {
                electronicsCount++;
                electronicsValue += product.getPrice();
            } else if (product instanceof Food) {
                foodCount++;
                foodValue += product.getPrice();
            }
        }
        
        System.out.printf("  Електронiка: %d товарiв, вартiсть: %.2f грн%n", 
                         electronicsCount, electronicsValue);
        System.out.printf("  Продукти: %d товарiв, вартiсть: %.2f грн%n", 
                         foodCount, foodValue);
    }
    
    private static void displayOverallStatistics(Warehouse<Product> warehouse) {
        System.out.println("\n\nЗагальна статистика:");
        System.out.printf("  Всього товарiв: %d%n", warehouse.size());
        System.out.printf("  Загальна вага: %.2f кг%n", warehouse.getCurrentWeight());
        System.out.printf("  Загальна вартiсть: %.2f грн%n", warehouse.getTotalValue());
        System.out.printf("  Заповненiсть: %.1f%%%n", 
                         (warehouse.getCurrentWeight() / warehouse.getMaxWeight()) * 100);
    }
}