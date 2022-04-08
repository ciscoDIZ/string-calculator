package com.leanmind.ciscoadiz.stringcalculator;

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

    private void errorHandler(String expression) throws Exception {
        int[] result = Stream.of(expression.split(separator))
                .mapToInt(Integer::parseInt)
                .filter(n -> n < 0)
                .toArray();
        if (result.length > 0) {
            throw new Exception("not allowed negatives: " + Arrays.toString(result));
        }
    }



    public int operate(String expression) throws Exception {
        if (expression.isEmpty()) {
            return 0;
        }
        if (expression.contains("\n")) {
            expression = determinateSeparator(expression);
        }
        String[] splitExpression = expression.split("\n");
        return addition(splitExpression);
    }

    private int addition(String[] expressions) throws Exception {
        int result = 0;
        for (String expression : expressions) {
            errorHandler(expression);
            result += Stream.of(expression.split(separator))
                    .mapToInt(Integer::parseInt)
                    .filter(n -> n <= 1000)
                    .sum();
        }

        return result;
    }

    private String determinateSeparator(String expression) {
        String[] splitExpressionLines = expression.split("\n");
        String singleSeparatorPattern = "(//)(\\[?[\\D]+]?)";
        String multipleSeparatorPattern = "(\\[?[^/]([^\\[][*\\D]+[^]])]?)";
        if (splitExpressionLines[0].matches(singleSeparatorPattern)) {
            Pattern singleSeparator = Pattern.compile(singleSeparatorPattern);
            Matcher singleSeparatorMatcher = singleSeparator.matcher(splitExpressionLines[0]);
            Pattern multipleSeparator = Pattern.compile(multipleSeparatorPattern);
            Matcher multipleSeparatorMatcher = multipleSeparator.matcher(splitExpressionLines[0]);
            if (multipleSeparatorMatcher.find()) {
                separator = multipleSeparatorMatcher.group();
                separator = separator.substring(1, separator.length()-1);
                String[] separators = separator.split("]\\[");
                separator = "[";
                for (String separator : separators) {
                    this.separator += separator;
                }
                separator += "]+";
                return expression.split("\n")[1];
            }
            if (singleSeparatorMatcher.find()) {
                separator = singleSeparatorMatcher.group(2);
                String escapableCharacter = "((\\[)?(\\*|\\+|\\.|\\^|\\{|\\||\\[|\\)|\\(\\?|])+(])?)+";
                if (separator.matches(escapableCharacter)) {
                    StringBuilder separatorBuilder = new StringBuilder();
                    separator = separatorBuilder
                            .append("\\")
                            .append(separator.charAt(1))
                            .append("+")
                            .toString();
                }
            }
            expression = splitExpressionLines[1];
        }
        return expression;
    }
}
