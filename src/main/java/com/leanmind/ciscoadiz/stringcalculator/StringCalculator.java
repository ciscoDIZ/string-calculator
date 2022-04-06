package com.leanmind.ciscoadiz.stringcalculator;

import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

public class StringCalculator {
    public int addition(String expression) {

        if (expression.isEmpty()) {
            return 0;
        }
        if (expression.contains("\n")) {
            String[] splitExpression = expression.split("\n");
            String mergedExpression = "";
            for (String line : splitExpression) {
                mergedExpression = mergedExpression.concat(line).concat(",");
            }
            expression = mergedExpression.substring(0, mergedExpression.length() - 1);
        }

        return Stream.of(expression.split(",")).mapToInt(Integer::parseInt).sum();
    }
}
