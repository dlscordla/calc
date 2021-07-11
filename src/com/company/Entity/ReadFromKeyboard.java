package com.company.Entity;

import java.util.Scanner;

public class ReadFromKeyboard {

    /**
     * Метод предназначен для считывания вещественных чисел с клавиатуры.
     * @return возвращает введенное число, если оно является вещественным.
     */

    public static double readNextDouble() {
        Scanner scanDouble = new Scanner(System.in);
        double d;
        if (!scanDouble.hasNextDouble()) {
            ConsoleLogs.messageIncorrectOperation();
            scanDouble.nextDouble();
        }
        d = scanDouble.nextDouble();
        return d;
    }

    /**
     * Метод предназначен для считывания операций и команд с клавиатуры.
     * @return возвращает строку.
     */

    public static String readNextString() {
        Scanner scanString = new Scanner(System.in);
        String string = "";
        if (scanString.hasNext()) {
            string = scanString.nextLine();
        }
        return string;
    }

    /**
     * Метод предназначен для установки режима работы калькулятора.
     * Описание назначения каждого режима содержится в @link com.company.Entity.ConsoleLogs#messageHelp()
     */

    public static void readNextMode() {
        Scanner scanMode = new Scanner(System.in);
        String mode = "";
        mode = scanMode.nextLine();
        switch (mode) {
            case "rpn":
            case "RPN":
                ConsoleLogs.messageStartExpressionCalc();
                CalculatorApp.calcTheStringExpression();
                break;
            case "dbl":
            case "DBL":
                ConsoleLogs.messageStartDoubleCalc();
                CalculatorApp.calcDoubles();
                break;
            case "dec":
            case "DEC":
                ConsoleLogs.messageStartIntCalc();
                CalculatorApp.calcInts();
            case "help":
            case "HELP":
                ConsoleLogs.messageHelp();
                ReadFromKeyboard.readNextMode();
                break;
            case "exit":
            case "EXIT":
                break;
            default:
                ConsoleLogs.messageIncorrectOperation();
                ReadFromKeyboard.readNextMode();
        }
    }
}
