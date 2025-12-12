# main.py

# Імпортуємо клас з нашого пакету
from devices import AirConditioner

def main():
    print("--- Початок роботи програми ---\n")

    # 1. Створення об'єкту класу Кондиціонер
    my_ac = AirConditioner(brand="Samsung", power_watts=2000, model="AR9500T")

    # 2. Виведення початкової інформації
    print(my_ac.get_info())
    print("-" * 20)

    # 3. Спроба змінити температуру на вимкненому пристрої (перевірка логіки)
    my_ac.set_temperature(20)

    # 4. Вмикання пристрою (метод успадкований від BaseDevice)
    my_ac.turn_on()

    # 5. Зміна температури (метод класу AirConditioner)
    my_ac.set_temperature(22)
    
    # 6. Спроба встановити неприпустиму температуру
    my_ac.set_temperature(10) # Занадто холодно

    # 7. Використання специфічного режиму
    my_ac.turbo_mode()

    # 8. Фінальна перевірка стану
    print("-" * 20)
    print(my_ac.get_info())

    # 9. Вимикання
    my_ac.turn_off()

if __name__ == "__main__":
    main()