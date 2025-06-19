package com.kybsa;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OptionTest {
    @Test
    public void givenValue_WhenOf_ThenReturnSome() {
        Option<String> okOption = Option.of("Success");
        assertInstanceOf(Option.Some.class, okOption);
        assertEquals("Success", ((Option.Some<String>) okOption).get());
    }

    @Test
    public void givenNullValue_WhenOf_ThenReturnNone() {
        Option<String> okOption = Option.of(null);
        assertTrue(okOption instanceof Option.None);
    }

    @Test
    public void givenSome_WhenIfPresent_ThenConsumerCalled() {
        Option<String> someOption = Option.of("Hello");
        StringBuilder result = new StringBuilder();
        someOption.ifPresent(value -> result.append(value));
        assertEquals("Hello", result.toString());
    }

    @Test
    public void givenNone_WhenIfPresent_ThenConsumerNotCalled() {
        Option<String> noneOption = Option.of(null);
        StringBuilder result = new StringBuilder();
        noneOption.ifPresent(value -> result.append(value));
        assertEquals("", result.toString());
    }

    @Test
    public void givenSome_WhenIfPresentOrElse_ThenConsumerCalled() {
        Option<String> someOption = Option.of("Hello");
        StringBuilder result = new StringBuilder();
        someOption.ifPresentOrElse(
                value -> result.append(value),
                () -> result.append("No value"));
        assertEquals("Hello", result.toString());
    }

    @Test
    public void givenNone_WhenIfPresentOrElse_ThenRunnableCalled() {
        Option<String> noneOption = Option.of(null);
        StringBuilder result = new StringBuilder();
        noneOption.ifPresentOrElse(
                value -> result.append(value),
                () -> result.append("No value"));
        assertEquals("No value", result.toString());
    }

    @Test
    public void giveNullConsumer_WhenIfPresent_ThenConsumerThrowIllegalArgumentException() {
        Option<String> noneOption = Option.of("value");
        assertThrows(IllegalArgumentException.class, () -> {
            noneOption.ifPresent(null);
        }, "ifPresent must not be throw IllegalArgumentException when consumer is null");
    }

    @Test
    public void giveSomeAndNullConsumer_WhenIfPresentOrElse_ThenConsumerThrowIllegalArgumentException() {
        Option<String> noneOption = Option.of("value");
        assertThrows(IllegalArgumentException.class, () -> {
            noneOption.ifPresentOrElse(null, () -> {
            });
        }, "ifPresentOrElse must not be throw IllegalArgumentException when consumer is null");
    }

    @Test
    public void giveNoneAndNullConsumer_WhenIfPresentOrElse_ThenConsumerThrowIllegalArgumentException() {
        Option<String> noneOption = Option.of(null);
        assertThrows(IllegalArgumentException.class, () -> {
            noneOption.ifPresentOrElse(value -> {
            }, null);
        }, "ifPresentOrElse must not be throw IllegalArgumentException when runnable is null");
    }

    @Test
    public void givenSome_WhenIsPresent_ThenReturnTrue() {
        Option<String> someOption = Option.of("Hello");
        assertTrue(someOption.isPresent(), "isPresent must return true for Some");
    }

    @Test
    public void givenSome_WhenIsPresent_ThenReturnFalse() {
        Option<String> someOption = Option.of(null);
        assertFalse(someOption.isPresent(), "isPresent must return false for None");
    }
}
