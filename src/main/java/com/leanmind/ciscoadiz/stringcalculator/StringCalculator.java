package com.leanmind.ciscoadiz.stringcalculator;

import com.leanmind.ciscoadiz.stringcalculator.exception.NegativesNotAllowed;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class StringCalculator {


    public int sumNumbersIn(String expression) {
        Integer[] numbers = (!expression.isEmpty())?Stream.of(expression.split(","))
                .map(Integer::parseInt)
                .toArray(Integer[]::new):new Integer[0];
        if (numbers.length < 1) {
            return 0;
        }
        return numbers[0] + numbers[1];
    }

}
