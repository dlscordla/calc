package com.company.Entity;

import com.company.Exceptions.DivisionByZeroException;

import java.util.*;

public class Calculator {

    public Calculator() {
    }

    public static double getResult(String expression) throws DivisionByZeroException {
        String prepared = Calculator.prepareNegative(expression);
        String rpn = Calculator.expressionToRPN(prepared);
        return Calculator.calculate(rpn);
    }

    static String prepareNegative(String expression) {
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

    static String expressionToRPN(String expression) {
        Stack<Character> stack = new Stack<>();
        String current = "";
        int priority;

        for (int i = 0; i < expression.length(); i++) {
            priority = getPriority(expression.charAt(i));
            if (priority == 0) {
                current += expression.charAt(i);
            }
            if (priority == 1) {
                stack.push(expression.charAt(i));
            }
            if (priority > 1) {
                current += " ";
                while (!stack.empty()) {
                    if (getPriority(stack.peek()) >= priority) {
                        current += stack.pop();
                    } else {
                        break;
                    }
                }
                stack.push(expression.charAt(i));
            }
            if (priority == -1) {
                current += " ";
                while (getPriority(stack.peek()) != 1) {
                    current += stack.pop();
                }
            }
        }
        while (!stack.empty()) {
            current += stack.pop();
        }
        return current;
    }

    static double calculate(String rpn) throws DivisionByZeroException {
        Stack<Double> stackValues = new Stack<>();
        for (int i = 0; i < rpn.length(); i++) {
            if (rpn.charAt(i) == ' ') {
                continue;
            }
            if (getPriority(rpn.charAt(i)) == 0) {
                String operand = "";
                while (rpn.charAt(i) != ' ' && getPriority(rpn.charAt(i)) == 0) {
                    operand += rpn.charAt(i++);
                    if (i == rpn.length()) {
                        break;
                    }
                }
                stackValues.push(Double.parseDouble(operand));
            }
            if (getPriority(rpn.charAt(i)) > 1) {
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

    private static int getPriority(char token) {
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