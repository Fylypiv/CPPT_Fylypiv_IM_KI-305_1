package KI305.Fylypiv.Lab2;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * КЛАС-ДРАЙВЕР для тестування та демонстрації роботи класу Conditioner
 * 
 * @author Fylypiv
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        // Механізм коректного завершення роботи з файлом (try-with-resources)
        try (
            Logger logger = new Logger("conditioner_log.txt");
            Scanner scanner = new Scanner(new InputStreamReader(System.in))
        ) {
            System.out.println("=== Створення кондицiонера ===");

            // --- Значення за замовчуванням ---
            String defaultBrand = "Samsung";
            int defaultPower = 2500;
            String defaultCompressorType = "Iнверторний";
            int defaultFanSpeed = 3;
            int defaultFilterLevel = 5;

            // --- Зчитування параметрів ---
            String brand = askString(scanner, "Бренд кондицiонера", defaultBrand);
            int power = askInt(scanner, "Потужнiсть (Вт)", defaultPower);
            String compressorType = askString(scanner, "Тип компресора", defaultCompressorType);
            int fanSpeed = askInt(scanner, "Швидкiсть вентилятора (1-5)", defaultFanSpeed);
            int filterLevel = askInt(scanner, "Рiвень фiльтрацiї (1-10)", defaultFilterLevel);

            // Створення обєкта з використанням конструктора з параметрами
            Conditioner conditioner = new Conditioner(
                new Compressor(compressorType, power),
                new Fan(fanSpeed),
                new Filter(filterLevel),
                brand
            );
            
            System.out.println("\nСтворено кондицiонер:\n" + conditioner);

            // Демонстрація роботи методів класу
            
            // 1. Увімкнення кондиціонера
            conditioner.turnOn(logger);
            
            // 2. Встановлення температури
            int targetTemp = askInt(scanner, "\nВстановiть цiльову температуру (°C)", 22);
            conditioner.setTargetTemperature(targetTemp, logger);
            
            // 3. Вибір режиму роботи
            System.out.println("\nРежими: COOLING (охолодження), HEATING (нагрiв), FAN (вентиляцiя), AUTO (авто)");
            String mode = askString(scanner, "Оберiть режим", "COOLING");
            conditioner.setMode(mode, logger);
            
            // 4. Зміна швидкості вентилятора
            int newFanSpeed = askInt(scanner, "\nЗмiнiть швидкiсть вентилятора (1-5)", 3);
            conditioner.changeFanSpeed(newFanSpeed, logger);
            
            // 5. Встановлення напрямку повітря
            String direction = askString(scanner, "\nНапрямок повiтря (HORIZONTAL/VERTICAL/AUTO)", "AUTO");
            conditioner.setAirDirection(direction, logger);
            
            // 6. Увімкнення режиму сну
            System.out.print("\nУвiмкнути режим сну? (yes/no): ");
            if (scanner.nextLine().trim().equalsIgnoreCase("yes")) {
                conditioner.enableSleepMode(logger);
            }
            
            // 7. Перевірка стану фільтра
            conditioner.checkFilter(logger);
            
            // 8. Очищення фільтра (якщо потрібно)
            System.out.print("\nОчистити фiльтр? (yes/no): ");
            if (scanner.nextLine().trim().equalsIgnoreCase("yes")) {
                conditioner.cleanFilter(logger);
            }
            
            // 9. Діагностика системи
            conditioner.runDiagnostics(logger);
            
            System.out.println("\nПоточний стан:\n" + conditioner);
            
            // 10. Вимкнення кондиціонера
            System.out.println("\nНатиснiть Enter для вимкнення кондицiонера...");
            scanner.nextLine();
            conditioner.turnOff(logger);
            
            System.out.println("\nФiнальний стан:\n" + conditioner);

        } catch (IOException ex) {
            System.err.println("Помилка вводу/виводу: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Помилка виконання: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // --- Допоміжні методи ---
    private static String askString(Scanner sc, String msg, String def) {
        System.out.print(msg + " [" + def + "]: ");
        String input = sc.nextLine().trim();
        return input.isEmpty() ? def : input;
    }

    private static int askInt(Scanner sc, String msg, int def) {
        try {
            return Integer.parseInt(askString(sc, msg, String.valueOf(def)));
        } catch (Exception e) {
            return def;
        }
    }
}