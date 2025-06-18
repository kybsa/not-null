package com.kybsa;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OptionTest {
    @Test
    public void givenValue_WhenOf_ThenReturnSome() {
        Option<String> okOption = Option.of("Success");        
        assertInstanceOf( Option.Some.class,okOption);
        assertEquals("Success",((Option.Some<String>)okOption).get());
    }
    @Test
    public void givenNullValue_WhenOf_ThenReturnNone() {
        Option<String> okOption = Option.of(null);
        assertTrue( okOption instanceof Option.None);
    }
}
