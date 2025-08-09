package com.kybsa;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OptionalTest {
    @Test
    void givenValue_WhenOf_ThenReturnSome() {
        Optional<String> okOption = Optional.of("Success");
        assertInstanceOf(Optional.Some.class, okOption);
        assertEquals("Success", ((Optional.Some<String>) okOption).get());
    }

    @Test
    void givenNullValue_WhenOf_ThenReturnNone() {
        Optional<String> okOption = Optional.of(null);
        assertTrue(okOption instanceof Optional.None);
    }

    @Test
    void givenSome_WhenIfPresent_ThenConsumerCalled() {
        Optional<String> someOption = Optional.of("Hello");
        StringBuilder result = new StringBuilder();
        someOption.ifPresent(value -> result.append(value));
        assertEquals("Hello", result.toString());
    }

    @Test
    void givenNone_WhenIfPresent_ThenConsumerNotCalled() {
        Optional<String> noneOption = Optional.of(null);
        StringBuilder result = new StringBuilder();
        noneOption.ifPresent(value -> result.append(value));
        assertEquals("", result.toString());
    }

    @Test
    void givenSome_WhenIfPresentOrElse_ThenConsumerCalled() {
        Optional<String> someOption = Optional.of("Hello");
        StringBuilder result = new StringBuilder();
        someOption.ifPresentOrElse(
                value -> result.append(value),
                () -> result.append("No value"));
        assertEquals("Hello", result.toString());
    }

    @Test
    void givenNone_WhenIfPresentOrElse_ThenRunnableCalled() {
        Optional<String> noneOption = Optional.of(null);
        StringBuilder result = new StringBuilder();
        noneOption.ifPresentOrElse(
                value -> result.append(value),
                () -> result.append("No value"));
        assertEquals("No value", result.toString());
    }

    @Test
    void giveNullConsumer_WhenIfPresent_ThenConsumerThrowIllegalArgumentException() {
        Optional<String> noneOption = Optional.of("value");
        assertThrows(IllegalArgumentException.class, () -> {
            noneOption.ifPresent(null);
        }, "ifPresent must not be throw IllegalArgumentException when consumer is null");
    }

    @Test
    void giveSomeAndNullConsumer_WhenIfPresentOrElse_ThenConsumerThrowIllegalArgumentException() {
        Optional<String> noneOption = Optional.of("value");
        assertThrows(IllegalArgumentException.class, () -> {
            noneOption.ifPresentOrElse(null, () -> {
            });
        }, "ifPresentOrElse must not be throw IllegalArgumentException when consumer is null");
    }

    @Test
    void giveNoneAndNullConsumer_WhenIfPresentOrElse_ThenConsumerThrowIllegalArgumentException() {
        Optional<String> noneOption = Optional.of(null);
        assertThrows(IllegalArgumentException.class, () -> {
            noneOption.ifPresentOrElse(value -> {
            }, null);
        }, "ifPresentOrElse must not be throw IllegalArgumentException when runnable is null");
    }

    @Test
    void givenSome_WhenIsPresent_ThenReturnTrue() {
        Optional<String> someOption = Optional.of("Hello");
        assertTrue(someOption.isPresent(), "isPresent must return true for Some");
    }

    @Test
    void givenSome_WhenIsPresent_ThenReturnFalse() {
        Optional<String> someOption = Optional.of(null);
        assertFalse(someOption.isPresent(), "isPresent must return false for None");
    }

    @Test
    void givenSome_WhenIsEmpty_ThenReturnFalse() {
        Optional<String> someOption = Optional.of("Hello");
        assertFalse(someOption.isEmpty(), "isEmpty must return false for Some");
    }

    @Test
    void givenNone_WhenIsEmpty_ThenReturnTrue() {
        Optional<String> noneOption = Optional.of(null);
        assertTrue(noneOption.isEmpty(), "isEmpty must return true for None");
    }
}
