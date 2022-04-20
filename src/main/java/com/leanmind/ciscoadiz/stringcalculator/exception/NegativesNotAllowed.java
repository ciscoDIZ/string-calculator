package com.leanmind.ciscoadiz.stringcalculator.exception;

import java.util.Arrays;

public class NegativesNotAllowed extends Exception{
    public NegativesNotAllowed(String message, Integer[] numbers) {
        super(message.concat(" ").concat(Arrays.toString(numbers)));
    }
}
