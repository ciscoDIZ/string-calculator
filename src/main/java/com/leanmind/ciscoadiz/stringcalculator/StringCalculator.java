package com.leanmind.ciscoadiz.stringcalculator;

import com.leanmind.ciscoadiz.stringcalculator.exception.NegativesNotAllowed;

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
        String[] next = Arrays.copyOfRange(expressions, 0, expressions.length - 1);
        String exceptionMessage = "you can't add up negative numbers. affected numbers:";
        errorHandler(exceptionMessage, expressions[i]);
        int currentValue = Stream.of(expressions[i].split(separator))
                .mapToInt(Integer::parseInt)
                .filter(n -> !(n > 1000))
                .sum();
        return addition(next, i-1) + currentValue;
    }

    private void errorHandler(String exceptionMessage, String expressions) throws NegativesNotAllowed {
        int[] negatives = Stream.of(expressions.split(separator))
                .mapToInt(Integer::parseInt)
                .filter(n-> n < 0)
                .toArray();
        if (negatives.length > 0) {
            throw new NegativesNotAllowed(exceptionMessage, negatives);
        }
    }

    public int operate(String expression) throws NegativesNotAllowed {
        String[] expressionLines = expression.split("\n");
        expressionLines = determinateSeparator(expressionLines);
        return addition(expressionLines, expressionLines.length-1);
    }

    private String[] determinateSeparator(String[] expressionLines) {
        Pattern separatorPatter = Pattern.compile("(//)(\\[?[^A-z0-9]+]?)+");
        Matcher separatorMatcher = separatorPatter.matcher(expressionLines[0]);

        if (separatorMatcher.find()) {
            this.separator = separatorMatcher.group(0);
            this.separator = this.separator.substring(2);
            if (this.separator.matches("[\\[][\\D]+[]]")) {
                this.separator = this.separator.substring(1, this.separator.length()-1);
            }
            String[] separators = separator.split("]\\[");
            if (separators.length > 1) {
                separator = Stream.of(separators).reduce("", String::concat);
                separator = "["+separator+"]";
                expressionLines = Arrays.copyOfRange(expressionLines, 1, expressionLines.length);
                return expressionLines;
            }
            if (this.separator.length() > 1) {
                this.separator = "["+this.separator.charAt(0)+"]{"+this.separator.length()+"}";
            }
            expressionLines = Arrays.copyOfRange(expressionLines, 1, expressionLines.length);
        }
        return expressionLines;
    }
}
