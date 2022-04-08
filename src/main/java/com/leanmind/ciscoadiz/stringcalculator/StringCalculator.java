package com.leanmind.ciscoadiz.stringcalculator;

import com.leanmind.ciscoadiz.stringcalculator.exception.NegativesNotAllowed;

import java.awt.*;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class StringCalculator {

    private String separator;

    public StringCalculator() {
        separator = ",";
    }

    private int addition(String[] expressions, int i) throws NegativesNotAllowed {

        if (expressions.length < 1) {
            return 0;
        }
        String[] next = Arrays.copyOfRange(expressions, 0, expressions.length-1);

        int[] negatives = Stream.of(expressions[i].split(separator)).mapToInt(Integer::parseInt).filter(n-> n < 0).toArray();
        if (negatives.length > 0) {
            throw new NegativesNotAllowed("no se puede sumar números negativos. números afectados:", negatives);
        }
        int currentValue = Stream.of(expressions[i].split(separator)).mapToInt(Integer::parseInt).sum();
        return addition(next, i-1) + currentValue;
    }

    public int operate(String expression) throws NegativesNotAllowed {
        String[] expressionLines = expression.split("\n");
        Pattern separatorPatter = Pattern.compile("//[^A-z0-9]");
        Matcher separatorMatcher = separatorPatter.matcher(expressionLines[0]);
        if (separatorMatcher.find()) {
            separator = separatorMatcher.group().split("//")[1];
            expressionLines = Arrays.copyOfRange(expressionLines, 1, expressionLines.length);
        }
        return addition(expressionLines, expressionLines.length-1);
    }
}
