# climate_control/air_conditioner.py

from .device import BaseDevice

class AirConditioner(BaseDevice):
    """
    Клас 'Кондиціонер', похідний від BaseDevice.
    Додає функціонал керування температурою та режимами.
    """
    def __init__(self, brand: str, power_watts: int, model: str):
        # Виклик конструктора батьківського класу
        super().__init__(brand, power_watts)
        self.model = model
        self.current_temp = 24  # Стандартна температура
        self.min_temp = 16      # Мінімальна допустима температура
        self.max_temp = 30      # Максимальна допустима температура

    def set_temperature(self, temperature: int):
        """Встановлює температуру, якщо кондиціонер увімкнений."""
        if not self.is_on:
            print(f"[{self.brand} {self.model}]: Неможливо змінити температуру. Пристрій вимкнено.")
            return
        print(f"[{self.brand} {self.model}]: Спроба встановити температуру на {temperature}°C.")
        if self.min_temp <= temperature <= self.max_temp:
            self.current_temp = temperature
            print(f"[{self.brand} {self.model}]: Температуру встановлено на {self.current_temp}°C.")
        else:
            print(f"[{self.brand} {self.model}]: Помилка! Температура має бути від {self.min_temp} до {self.max_temp}°C.")

    def turbo_mode(self):
        """Специфічний метод кондиціонера: швидке охолодження."""
        if self.is_on:
            print(f"[{self.brand} {self.model}]: Увімкнено режим TURBO! Охолодження на повну потужність.")
            self.current_temp = 18
        else:
            print(f"[{self.brand} {self.model}]: Спочатку увімкніть кондиціонер.")

    def get_info(self):
        """Перевизначення методу базового класу для виводу детальнішої інформації."""
        # Отримуємо інформацію від батьківського класу
        base_info = super().get_info()
        return f"{base_info}, Модель: {self.model}, Температура: {self.current_temp}°C"