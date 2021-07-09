package com.company.Entity;

import com.company.Exceptions.DivisionByZeroException;

public class CalculatorApp {

    public CalculatorApp() {

    }

    public static void main(String[] args) throws DivisionByZeroException {
        ConsoleLogs.messageStart();
        String expression = ConsoleLogs.enterTheTokenFromKeyboard();
        System.out.println(expression + " = " + Calculator.getResult(expression));
    }
}