package com.company.Entity;

public class ConsoleLogs {

    /**
     * Класс ConsoleLogs организует систему сообщений для их вывода на экран.
     * Сообщения предназначены для пользователя; в них содержится ключевая информация о том, что сейчас происходит.
     */

    public static void messageStart() {
        System.out.println("Начало работы с калькулятором.\n" +
                "Выберите режим работы калькулятора:\n" +
                "Для вызова справки наберите команду help");
    }

    public static void messageStartDoubleCalc() {
        System.out.println("Начало работы с калькулятором вещественных чисел в десятичной системе...");
    }

    public static void messageStartIntCalc() {
        System.out.println("Начало работы с калькулятором целых чисел в десятичной системе...");
    }

    public static void messageStartExpressionCalc() {
        System.out.println("Начало работы с калькулятором, обрабатывающим выражение...\n" +
        "Введите выражение и нажмите Enter...");
    }

    public static void messageDivideByZero() {
        System.out.println("Деление на ноль запрещено. Повторите ввод делителя.");
    }

    public static void messageIncorrectOperation() {
        System.out.println("Некорректная операция, повторите ввод");
    }

    public static void messageExit() {
        System.out.println("Выход в главное меню...");
    }

    public static void messageHelp() {
        System.out.println("+ - операция сложения;\n" +
                "- - операция вычитания;\n" +
                "* - операция умножения;\n" +
                "/ - операция деления;\n" +
                "dbl - установка режима работы с вещественными числами в десятичной системе счисления;\n" +
                "dec - установка режима работы с целыми числами в десятичной системе счисления;\n" +
                "rpn - установка режима работы с вычислением выражения;\n");
    }
}
