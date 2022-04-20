package com.leanmind.ciscoadiz.stringcalculator;

import com.leanmind.ciscoadiz.stringcalculator.exception.NegativesNotAllowed;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class StringCalculator {
    public int sumNumbersIn(String expression) throws NegativesNotAllowed {
        String[] expressions = expression.split("\n");
        Pattern delimiterRegExp = Pattern.compile("(//)(\\D+)");
        Matcher delimiterMatcher = delimiterRegExp.matcher(expressions[0]);
        String delimiter;
        if (delimiterMatcher.find()) {
            String wrappedDelimiter = delimiterMatcher.group(2);
            char delimiterFistPlace = wrappedDelimiter.charAt(0);
            char delimiterLastPlace = wrappedDelimiter.charAt(wrappedDelimiter.length()-1);
            boolean isRegExp = delimiterFistPlace=='[' && delimiterLastPlace == ']';
            String unwrappedDelimiter =(isRegExp)? wrappedDelimiter.substring(1, wrappedDelimiter.length() - 1) :
                    wrappedDelimiter;
            delimiter = (!isRegExp) ? unwrappedDelimiter : "["+ unwrappedDelimiter +"]{"+unwrappedDelimiter.length()+"}";

            expressions = Arrays.copyOfRange(expressions, 1, expressions.length);
        }else {
            delimiter = ",";
        }

        Integer[] numbers = (!expression.isEmpty()) ? Arrays.stream(expressions)
                .flatMap(e -> Arrays.stream(e.split(delimiter)).map(Integer::parseInt))
                .filter(n -> n <= 1000)
                .toArray(Integer[]::new):new Integer[0];

        return sum(numbers);
    }

    private int sum(Integer[] numbers) throws NegativesNotAllowed {
        Integer[] negatives = Arrays.stream(numbers).filter(n -> n < 0).toArray(Integer[]::new);
        if (negatives.length>0) {
            throw new NegativesNotAllowed("you can't add up negative numbers. affected numbers:", negatives);
        }
        if (numbers.length == 1) {
            return numbers[0];
        }
        if (numbers.length == 0) {
            return 0;
        }
        int current = numbers[0];
        Integer[] next = Arrays.copyOfRange(numbers, 1, numbers.length);
        return sum(next) + current;
    }
}
