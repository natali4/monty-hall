package com.avega;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomNumberGenerator {

    public int generate(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("Max is greater than min!");
        }

        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }
}
