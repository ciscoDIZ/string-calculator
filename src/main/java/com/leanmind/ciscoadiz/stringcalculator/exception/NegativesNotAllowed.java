package com.leanmind.ciscoadiz.stringcalculator.exception;

import java.util.Arrays;

public class NegativesNotAllowed extends Exception{
    public NegativesNotAllowed(String message, int[] numbers) {
        super(message.concat(" ").concat(Arrays.toString(numbers)));
    }
}
