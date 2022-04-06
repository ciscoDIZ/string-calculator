package com.leanmind.ciscoadiz.stringcalculator;

import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

public class StringCalculator {
    public int addition(String expression) {
        if (expression.isEmpty()) {
            return 0;
        }
        return Stream.of(expression.split(",")).mapToInt(Integer::parseInt).sum();
    }
}
