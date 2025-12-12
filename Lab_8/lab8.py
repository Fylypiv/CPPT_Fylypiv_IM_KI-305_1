import math
import pickle
import os
import sys

def calculate_y(x):
    """
    Обчислює значення функції y = 1/sin(x).
    Аргументи: x (float): Вхідне значення.  
    Повертає: float: Результат обчислення.
    Викидає (Raises):
        ZeroDivisionError: Якщо sin(x) = 0.
    """
    sin_val = math.sin(x)
    
    # Перевіряємо, чи число дорівнює нулю
    if math.isclose(sin_val, 0):
        raise ZeroDivisionError("Помилка: sin(x) = 0, ділення на нуль неможливе.")
    return 1 / sin_val


def write_text_file(filename, x, y):
    """
    Зберігає результат у текстовий файл.
    Використовує режим 'a' (append), щоб додавати нові записи, не стираючи старі.
    """
    try:
        # Відкриваємо файл з кодуванням utf-8 для коректного відображення символів
        with open(filename, 'a', encoding='utf-8') as f:
            # Форматуємо рядок: 4 знаки після коми
            line = f"x = {x:.4f}; y = {y:.4f}\n"
            f.write(line)
    except IOError as e:
        # Обробка помилок вводу-виводу
        print(f"Помилка запису текстового файлу: {e}")
        sys.exit(1)


def read_text_file(filename):
    """
    Зчитує та виводить вміст текстового файлу на екран.
    """
    # Перевірка: чи існує файл перед спробою читання
    if not os.path.exists(filename):
        print(f"Файл {filename} ще не створено.")
        return

    try:
        # 'r' (read) - режим читання
        with open(filename, 'r', encoding='utf-8') as f:
            content = f.read()
            print(f"--- Вміст текстового файлу ---")
            print(content.strip()) # .strip() прибирає зайві порожні рядки на початку/кінці
    except IOError:
        print(f"Не вдалося прочитати файл {filename}")
        sys.exit(1)



def write_bin_file(filename, x, y):
    """
    Зберігає результат у бінарний файл за допомогою pickle.
    Використовує режим 'wb' (write binary), тому файл перезаписується.
    Зберігається тільки останній результат.
    """
    # Створюємо список даних
    data_list = [(x, y)]

    try:
        with open(filename, 'wb') as f:
            # pickle.dump записує data_list у файл
            pickle.dump(data_list, f)
    except IOError as e:
        print(f"Помилка запису бінарного файлу: {e}")
        sys.exit(1)


def read_bin_file(filename):
    """
    Зчитує дані з бінарного файлу.
    """
    if not os.path.exists(filename):
        print(f"Бінарний файл {filename} ще не створено.")
        return

    try:
        with open(filename, 'rb') as f: # 'rb' - read binary
            # Відновлюємо структуру даних з байтів
            data_list = pickle.load(f)
            
            print(f"--- Вміст бінарного файлу ---")
            for x, y in data_list:
                print(f"x = {x:.4f}; y = {y:.4f}")
    except IOError:
        print(f"Не вдалося прочитати файл {filename}")
        sys.exit(1)
    except pickle.PickleError:
        print("Помилка декодування бінарних даних.")
        sys.exit(1)


def main():
    try:
        # 1. Отримання даних від користувача
        user_input = input("Введіть значення x: ")

        # Замінюємо кому на крапку для підтримки обох форматів (3,14 -> 3.14)
        x = float(user_input.replace(',', '.'))
        
        # 2. Виконання обчислень
        y = calculate_y(x)
        print(f"Результат: y = {y:.4f}")
    except ValueError:
        # Якщо користувач ввів текст замість числа
        print("Помилка: Введіть коректне число.")
        sys.exit(1)
    except ZeroDivisionError as e:
        # Перехоплення помилки, яку ми згенерували в calculate_y
        print(e)
        sys.exit(1)

    # Імена файлів для збереження
    txt_filename = "result_text.txt"
    bin_filename = "result_bin.bin"

    # 3. Запис результатів у файли
    write_text_file(txt_filename, x, y)    # Додаємо в історію
    write_bin_file(bin_filename, x, y)     # Перезаписуємо поточним

    print(f"Результати успішно збережено у файли.")
    print("=" * 40)
    
    # 4. Зчитування для перевірки
    read_text_file(txt_filename)
    print("=" * 40)
    read_bin_file(bin_filename)

if __name__ == "__main__":
    main()