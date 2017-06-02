package com.avega;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.*;

public class RandomNumberGeneratorTest {

    private RandomNumberGenerator randomNumberGenerator;

    @Before
    public void setUp() throws Exception {
        randomNumberGenerator = new RandomNumberGenerator();
    }

    @Test
    public void can_generate_random_number_within_bottom_and_top_limit() {
        int generatedNumber = randomNumberGenerator.generate(1, 3);

        assertThat(generatedNumber, greaterThan(0));
        assertThat(generatedNumber, lessThan(5));
    }

    @Test(expected = IllegalArgumentException.class)
    public void throws_exception_when_min_is_greater_than_max_value() {
        randomNumberGenerator.generate(3, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throws_exception_when_min_value_is_equal_max() {
        randomNumberGenerator.generate(3, 3);
    }
}