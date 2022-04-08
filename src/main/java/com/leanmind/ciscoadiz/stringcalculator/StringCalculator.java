package com.leanmind.ciscoadiz.stringcalculator;

import java.awt.*;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class StringCalculator {



    public int operate(String expression) {
        String[] expressionLines = expression.split("\n");
        return Stream.of(expressionLines[0].split(","))
                .mapToInt(Integer::parseInt)
                .sum();
    }
}
