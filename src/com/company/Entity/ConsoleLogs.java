package com.company.Entity;

import com.company.Exceptions.DivisionByZeroException;

import java.util.Scanner;

public class ConsoleLogs {

    public static void messageStart() {
        System.out.println("Начало работы с калькулятором. Введите выражение и нажмите клавишу Enter:");
    }

    public static String enterTheTokenFromKeyboard () {
        return new Scanner(System.in).nextLine();
    }

    public static void messageDivideByZero() {
        System.out.println("На ноль делить нельзя");
    }
}
