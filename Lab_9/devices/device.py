# climate_control/device.py

class BaseDevice:
    """
    Базовий клас, що описує загальні характеристики електричного пристрою.
    """
    def __init__(self, brand: str, power_watts: int):
        # Ініціалізація атрибутів бренду та споживаної потужності
        self.brand = brand
        self.power_watts = power_watts
        self.is_on = False  # Стан пристрою (Увімкнено/Вимкнено)

    def turn_on(self):
        """Метод для вмикання пристрою."""
        if not self.is_on:
            self.is_on = True
            print(f"[{self.brand}]: Пристрій увімкнено.")
        else:
            print(f"[{self.brand}]: Пристрій вже працює.")

    def turn_off(self):
        """Метод для вимикання пристрою."""
        if self.is_on:
            self.is_on = False
            print(f"[{self.brand}]: Пристрій вимкнено.")
        else:
            print(f"[{self.brand}]: Пристрій вже вимкнений.")

    def get_info(self):
        """Повертає рядок з інформацією про пристрій."""
        status = "Увімкнено" if self.is_on else "Вимкнено"
        return f"Пристрій: {self.brand}, Потужність: {self.power_watts}Вт, Стан: {status}"