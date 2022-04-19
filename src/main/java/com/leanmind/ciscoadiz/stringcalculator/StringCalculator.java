package com.leanmind.ciscoadiz.stringcalculator;

import com.leanmind.ciscoadiz.stringcalculator.exception.NegativesNotAllowed;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class StringCalculator {


    public int sumNumbersIn(String expression) {
        Integer[] numbers = Stream.of(expression.split(","))
                .map(Integer::parseInt)
                .toArray(Integer[]::new);
        return numbers[0] + numbers[1];
    }

}
