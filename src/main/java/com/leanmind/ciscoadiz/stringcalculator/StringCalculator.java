package com.leanmind.ciscoadiz.stringcalculator;

import com.sun.source.tree.UsesTree;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;
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
    public int addition(String expression) throws Exception {

        if (expression.isEmpty()) {
            return 0;
        }

        if (expression.contains("\n")) {
            String pattern = "(//)([;:,])";
            String[] splitExpression = expression.split("\n");
            if (splitExpression[0].matches(pattern)) {
                Pattern regExp = Pattern.compile(pattern);
                Matcher matcher = regExp.matcher(splitExpression[0]);
                if (matcher.find()){
                    separator = matcher.group(2);
                }
                splitExpression = Stream.of(splitExpression)
                        .filter(s -> !s.matches(pattern))
                        .toArray(String[]::new);
            }
            String mergedExpression = "";
            for (String line : splitExpression) {
                mergedExpression = mergedExpression.concat(line).concat(separator);
            }
            expression = mergedExpression.substring(0, mergedExpression.length() - 1);
        }
        errorHandler(expression);
        return Stream.of(expression.split(separator))
                .mapToInt(Integer::parseInt)
                .sum();
    }
}
