package com.company.Entity;

public class CalculatorApp {

    /**
     * Метод начинает работу с калькулятором.
     * Выводится соответствующее сообщение, предлагается выбрать режим работы калькулятора,
     * а также вывести справку по командам.
     * @param args
     */

    public static void main(String[] args) {
        ConsoleLogs.messageStart();
        ReadFromKeyboard.readNextMode();
    }

    /**
     * Метод позволяет очистить текущее значение в режиме работы с целыми числами в десятичной системе.
     */

    public static void clearInt() {
        int result = 0;
        System.out.println(result);
    }

    /**
     * Метод позволяет очистить текущее значение в режиме работы с вещественными числами в десятичной системе.
     */

    public static void clearDouble() {
        double result = 0;
        System.out.println(result);
    }

    /**
     * Метод позволяет вернуться в главное меню и выбрать новый режим работы калькулятора.
     */

    public static void exitToTheMenu() {
        ConsoleLogs.messageExit();
        ConsoleLogs.messageStart();
        ReadFromKeyboard.readNextMode();
    }

    /**
     * Метод предназначен для получения результата введенного выражения. Вычисление происходит в несколько этапов:
     * @link com.company.Entity.Calculator#expressionToRPN(String) - метод прогоняет исходную строку через алгоритм обратной польской нотации
     * @link com.company.Entity.Calculator#calculateRPN(String) - метод вычисляет результат исходного выражения
     * @link com.company.Entity.Calculator#prepareNegative(String) - если в исходном выражении содержатся отрицательные числа, то данный метод учитывает это
     * @link com.company.Entity.Calculator#getResultRPN(String) - метод выдает готовый ответ
     */

    public static void calcTheStringExpression() {
        String expression = ReadFromKeyboard.readNextString();
        do {
            System.out.println(expression + " = " + Calculator.getResultRPN(expression));
            expression = ReadFromKeyboard.readNextString();
        } while (!expression.equals("exit"));
    }

    /**
     * Метод позволяет перейти в режим работы калькулятора с целыми числами в десятичной системе счисления.
     * Пользователь вводит первый операнд, затем операцию, далее второй операнд, и при нажатии клавиши "=" получает результат.
     * Каждый ввод операнда, операции и знака = должеен сопровождаться нажатием клавиши Enter для взятия данных в обработку.
     * При выборе операции "с" программа сбрасывает текущее значение на 0. Работа калькулятора в данном режиме начинается заново.
     * При вводе "exit" программа возвращается в главное меню к выбору другого режима работы.
     */

    public static void calcInts() {
        int first = (int) ReadFromKeyboard.readNextDouble();
        String operation = ReadFromKeyboard.readNextString();
        double second = (int) ReadFromKeyboard.readNextDouble();
        Calculator calculator = new Calculator(first, second);
        int result = 0;
        do {
            switch (operation) {
                case "+":
                    result = (int) calculator.add();
                    break;
                case "-":
                    result = (int) calculator.subtract();
                    break;
                case "*":
                    result = (int) calculator.multiply();
                    break;
                case "/":
                    result = (int) calculator.divide();
                    break;
                default:
                    ConsoleLogs.messageIncorrectOperation();
                    ReadFromKeyboard.readNextString();
            }
            if (ReadFromKeyboard.readNextString().equals("=")) {
                System.out.println(result);
            }
            calculator.setA(result);
            operation = ReadFromKeyboard.readNextString();
            if (operation.equals("exit")) {
                exitToTheMenu();
                break;
            }
            if (operation.equals("c")) {
                clearInt();
                calcInts();
            }
            calculator.setB(ReadFromKeyboard.readNextDouble());
        } while (!operation.equalsIgnoreCase("exit"));
    }

    /**
     * Метод позволяет перейти в режим работы калькулятора с вещественными числами в десятичной системе счисления.
     * Пользователь вводит первый операнд, затем операцию, далее второй операнд, и при нажатии клавиши "=" получает результат.
     * Каждый ввод операнда, операции и знака = должеен сопровождаться нажатием клавиши Enter для взятия данных в обработку.
     * При выборе операции "с" программа сбрасывает текущее значение на 0. Работа калькулятора в данном режиме начинается заново.
     * При вводе "exit" программа возвращается в главное меню к выбору другого режима работы.
     */


    public static void calcDoubles() {
        double first = ReadFromKeyboard.readNextDouble();
        String operation = ReadFromKeyboard.readNextString();
        double second = ReadFromKeyboard.readNextDouble();
        Calculator calculator = new Calculator(first, second);
        double result = 0;
        do {
            switch (operation) {
                case "+":
                    result = calculator.add();
                    break;
                case "-":
                    result = calculator.subtract();
                    break;
                case "*":
                    result = calculator.multiply();
                    break;
                case "/":
                    result = calculator.divide();
                    break;
                default:
                    ConsoleLogs.messageIncorrectOperation();
                    ReadFromKeyboard.readNextString();
            }
            if (ReadFromKeyboard.readNextString().equals("=")) {
                System.out.println(result);
            }
            calculator.setA(result);
            operation = ReadFromKeyboard.readNextString();
            if (operation.equals("exit")) {
                exitToTheMenu();
                break;
            }
            if (operation.equals("c")) {
                clearDouble();
                calcDoubles();
            }
            calculator.setB(ReadFromKeyboard.readNextDouble());
        } while (!operation.equalsIgnoreCase("exit"));
    }
}