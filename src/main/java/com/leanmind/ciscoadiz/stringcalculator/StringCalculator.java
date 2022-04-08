package com.leanmind.ciscoadiz.stringcalculator;

import java.awt.*;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class StringCalculator {

    private final String separator;

    public StringCalculator() {
        separator = ",";
    }

    private int addition(String[] expressions, int i) {

        if (expressions.length < 1) {
            return 0;
        }
        String[] next = Arrays.copyOfRange(expressions, 0, expressions.length-1);
        int currentValue = Stream.of(expressions[i].split(separator)).mapToInt(Integer::parseInt).sum();
        return addition(next, i-1) + currentValue;
    }

    public int operate(String expression) {
        String[] expressionLines = expression.split("\n");
        return addition(expressionLines, expressionLines.length-1);
    }
}
