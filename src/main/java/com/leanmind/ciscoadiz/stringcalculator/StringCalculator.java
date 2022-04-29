package com.leanmind.ciscoadiz.stringcalculator;

import com.leanmind.ciscoadiz.stringcalculator.exception.NegativesNotAllowed;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    public int sumNumbersIn(String expression) throws NegativesNotAllowed {
        final String delimiter;
        String[] expressions = expression.split("\n");
        final Pattern delimiterRegExp = Pattern.compile("//(\\D+)");
        final Matcher delimiterMatcher = delimiterRegExp.matcher(expressions[0]);
        if (delimiterMatcher.find()) {
            delimiter = determinateDelimiter(delimiterMatcher);
            expressions = Arrays.copyOfRange(expressions, 1, expressions.length);
        }else {
            delimiter = ",";
        }

        final Integer[] numbers = (!expression.isEmpty()) ? Arrays.stream(expressions)
                .flatMap(e -> Arrays.stream(e.split(delimiter)).map(Integer::parseInt))
                .filter(n -> n <= 1000)
                .toArray(Integer[]::new):new Integer[0];
        return sum(numbers);
    }

    private String determinateDelimiter(Matcher delimiterMatcher) {
        final String delimiter;
        final String wrappedDelimiter = delimiterMatcher.group(1);
        final char delimiterFistPlace = wrappedDelimiter.charAt(0);
        final char delimiterLastPlace = wrappedDelimiter.charAt(wrappedDelimiter.length()-1);
        final boolean isRegExp = delimiterFistPlace=='[' && delimiterLastPlace == ']';
        final String unwrappedDelimiter =(isRegExp)? wrappedDelimiter.substring(1, wrappedDelimiter.length() - 1) :
                wrappedDelimiter;
        final String[] splitForMultipleDelimiters = unwrappedDelimiter.split("]\\[");
        if (splitForMultipleDelimiters.length > 1) {
            delimiter ="["+Arrays.stream(splitForMultipleDelimiters).reduce(String::concat).get()+"]";
        }else {
            delimiter = (!isRegExp) ?
                    unwrappedDelimiter :
                    "[" + unwrappedDelimiter + "]{" + unwrappedDelimiter.length() + "}";
        }
        return delimiter;
    }

    private int sum(Integer[] numbers) throws NegativesNotAllowed {
        final Integer[] negatives = Arrays.stream(numbers).filter(n -> n < 0).toArray(Integer[]::new);
        if (negatives.length>0) {
            throw new NegativesNotAllowed("you can't add up negative numbers. affected numbers:", negatives);
        }
        if (numbers.length == 1) {
            return numbers[0];
        }
        if (numbers.length == 0) {
            return 0;
        }
        final int current = numbers[0];
        final Integer[] next = Arrays.copyOfRange(numbers, 1, numbers.length);
        return sum(next) + current;
    }
}
