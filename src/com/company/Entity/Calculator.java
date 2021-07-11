package com.company.Entity;

import com.company.Exceptions.DivisionByZeroException;

import java.util.*;

public class Calculator {

    private double a;
    private double b;

    public Calculator(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public void setA(double a) {
        this.a = a;
    }

    public void setB(double b) {
        this.b = b;
    }

    /**
     * Суммирует два значения.
     * @return возвращает сумму двух операнд
     */

    public double add() {
        return a + b;
    }

    /**
     * Производит вычитание второго значения из первого.
     * @return возвращает разность двух операнд.
     */

    public double subtract() {
        return a - b;
    }

    /**
     * Перемножает два значения.
     * @return возвращает произведение двух операнд.
     */

    public double multiply() {
        return a * b;
    }

    /**
     * Находит частное двух значений.
     * @return возвращает результат деления первого операнда на второй.
     * Если второй операнд равен нулю, то бросает исключение и просит ввести второй операнд повторно.
     */

    public double divide() {
        try {
            if (b == 0) {
                throw new DivisionByZeroException("Деление на ноль запрещено. Повторите ввод делителя.");
            }
        } catch (DivisionByZeroException e) {
            ConsoleLogs.messageDivideByZero();
            setB(ReadFromKeyboard.readNextDouble());
        }
        return a / b;
    }

    /**
     * Метод вычисляет значение введенного выражения, учитывая в том числе, содержатся ли в нем отрицательные члены.
     * @param expression выражение
     * @return результат
     */

    public static double getResultRPN(String expression) {
        String prepared = Calculator.prepareNegativeRPN(expression);
        String rpn = Calculator.expressionToRPN(prepared);
        return Calculator.calculateRPN(rpn);
    }

    /**
     * Метод позволяет производить операции с отрицательными членами в выражении.
     * Например, выражение "-2" трансформируется в "0-2" (или в обратной польской записи "0 2 -")
     * @param expression выражение
     * @return готовое к вычислению выражение с отрицательными членами
     */

    static String prepareNegativeRPN(String expression) {
        String preparedExpression = "";
        for (int token = 0; token < expression.length(); token++) {
            char symbol = expression.charAt(token);
            if (symbol == '-') {
                if (token == 0) {
                    preparedExpression += '0';
                } else if (expression.charAt(token - 1) == '(') {
                    preparedExpression += '0';
                }
            }
            preparedExpression += symbol;
        }

        return preparedExpression;
    }

    /**
     * Метод возвращает выражение, записанное обратной польской нотацией. Например, запись "3 + 4" будет
     * конвертирована в запись "3 4 +"
     * @param expression введенное с клавиатуры выражение
     * @return возвращает выражение, записанное обратной польской нотацией
     */

    static String expressionToRPN(String expression) {
        Stack<Character> stack = new Stack<>();
        String current = "";
        int priority;

        for (int i = 0; i < expression.length(); i++) {
            priority = getPriorityRPN(expression.charAt(i));
            if (priority == 0) {
                current += expression.charAt(i);
            }
            if (priority == 1) {
                stack.push(expression.charAt(i));
            }
            if (priority > 1) {
                current += " ";
                while (!stack.empty()) {
                    if (getPriorityRPN(stack.peek()) >= priority) {
                        current += stack.pop();
                    } else {
                        break;
                    }
                }
                stack.push(expression.charAt(i));
            }
            if (priority == -1) {
                current += " ";
                while (getPriorityRPN(stack.peek()) != 1) {
                    current += stack.pop();
                }
            }
        }
        while (!stack.empty()) {
            current += stack.pop();
        }
        return current;
    }

    /**
     * Метод возвращает результат выражения, записанного в обратной польской нотации.
     * @param rpn - выражение, конвертированное в обратную польскую нотацию
     * @return возвращает результат вычислений
     */

    static double calculateRPN(String rpn) {
        Stack<Double> stackValues = new Stack<>();
        for (int i = 0; i < rpn.length(); i++) {
            if (rpn.charAt(i) == ' ') {
                continue;
            }
            if (getPriorityRPN(rpn.charAt(i)) == 0) {
                String operand = "";
                while (rpn.charAt(i) != ' ' && getPriorityRPN(rpn.charAt(i)) == 0) {
                    operand += rpn.charAt(i++);
                    if (i == rpn.length()) {
                        break;
                    }
                }
                stackValues.push(Double.parseDouble(operand));
            }
            if (getPriorityRPN(rpn.charAt(i)) > 1) {
                double firstItemOutOfStack = stackValues.pop();
                double secondItemOutOfStack = stackValues.pop();
                switch (rpn.charAt(i)) {
                    case '+':
                        stackValues.push(secondItemOutOfStack + firstItemOutOfStack);
                        break;
                    case '-':
                        stackValues.push(secondItemOutOfStack - firstItemOutOfStack);
                        break;
                    case '*':
                        stackValues.push(secondItemOutOfStack * firstItemOutOfStack);
                        break;
                    case '/':
                        stackValues.push(secondItemOutOfStack / firstItemOutOfStack);
                        break;
                }
            }
        }
        double result = stackValues.pop();
        try {
            if (result == Double.POSITIVE_INFINITY
                    || result == Double.NEGATIVE_INFINITY) {
                throw new DivisionByZeroException("Деление на ноль запрещено.");
            }
        } catch (DivisionByZeroException e) {
            ConsoleLogs.messageDivideByZero();
        }
        return result;
    }

    /**
     * Метод определяет приоритет, то есть, порядок выполнения той или иной операции в выражении.
     * Например, умножение и деление имеют больший приоритет, чем сложение и вычитание, и поэтому должны выполняться
     * в первую очередь.
     * @param token операция
     * @return приоритет операции
     */

    private static int getPriorityRPN(char token) {
        switch (token) {
            case ')':
                return -1;
            case '(':
                return 1;
            case '+':
            case '-':
                return 2;
            case '*':
            case '/':
                return 3;
            default:
                return 0;
        }
    }
}