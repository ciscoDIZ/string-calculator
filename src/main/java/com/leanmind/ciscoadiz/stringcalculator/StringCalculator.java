package com.leanmind.ciscoadiz.stringcalculator;

import com.leanmind.ciscoadiz.stringcalculator.exception.NegativesNotAllowed;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class StringCalculator {
    private int sum(Integer[] numbers) {
        if (numbers.length < 1) {
            return 0;
        }
        int current = numbers[0];
        Integer[] next = Arrays.copyOfRange(numbers, 1, numbers.length);
        return sum(next) + current;
    }

    public int sumNumbersIn(String expression) {
        Integer[] numbers = (!expression.isEmpty())? Arrays.stream(expression.split(","))
                .map(Integer::parseInt)
                .toArray(Integer[]::new):new Integer[0];

        return sum(numbers);
    }

}
