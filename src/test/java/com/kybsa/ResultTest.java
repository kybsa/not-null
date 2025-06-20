package com.kybsa;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ResultTest {
    @Test
    void testResultOk() {
        Result<String, String> okResult = Result.of("Success", null);
        assertTrue(okResult instanceof Result.Some);
        assertEquals("Success", ((Result.Some<String, String>) okResult).get());
    }

    @Test
    void testResultNone() {
        Result<String, String> noneResult = Result.of(null, null);
        assertTrue(noneResult instanceof Result.None);
    }

    @Test
    void testResultError() {
        Result<String, String> errorResult = Result.of(null, "Error occurred");
        assertTrue(errorResult instanceof Result.Error);
        assertEquals("Error occurred", ((Result.Error<String, String>) errorResult).getCause());
    }

    @Test
    void givenSome_WhenIfPresent_ThenCallConsumer() {
        Result<String, String> okResult = Result.of("Hello", null);
        StringBuilder result = new StringBuilder();
        okResult.ifPresent(value -> result.append(value));
        assertEquals("Hello", result.toString());
    }

    @Test
    void givenNullConsumer_WhenIfPresent_ThenThrowException() {
        Result<String, String> okResult = Result.of("Hello", null);
        assertThrows(IllegalArgumentException.class, () -> okResult.ifPresent(null));
    }

    @Test
    void givenNone_WhenIfPresent_ThenConsumerNotCalled() {
        Result<String, String> noneResult = Result.of(null, null);
        StringBuilder result = new StringBuilder();
        noneResult.ifPresent(value -> result.append(value));
        assertEquals("", result.toString());
    }

    @Test
    void givenError_WhenIfPresent_ThenConsumerNotCalled() {
        Result<String, String> errorResult = Result.of(null, "Error occurred");
        StringBuilder result = new StringBuilder();
        errorResult.ifPresent(value -> result.append(value));
        assertEquals("", result.toString());
    }

    @Test
    void givenSome_WhenIsPresent_ThenReturnoTrue() {
        Result<String, String> okResult = Result.of("Hello", null);
        assertTrue(okResult.isPresent());
    }

    @Test
    void givenNone_WhenIsPresent_ThenReturnFalse() {
        Result<String, String> noneResult = Result.of(null, null);
        assertFalse(noneResult.isPresent());
    }

    @Test
    void givenError_WhenIsPresent_ThenReturnFalse() {
        Result<String, String> errorResult = Result.of(null, "Error occurred");
        assertFalse(errorResult.isPresent());
    }

    @Test
    void givenSome_WhenIsEmpty_ThenReturnFalse() {
        Result<String, String> okResult = Result.of("Hello", null);
        assertFalse(okResult.isEmpty());
    }

    @Test
    void givenNone_WhenIsEmpty_ThenReturnTrue() {
        Result<String, String> noneResult = Result.of(null, null);
        assertTrue(noneResult.isEmpty());
    }

    @Test
    void givenError_WhenIsEmpty_ThenReturnTrue() {
        Result<String, String> errorResult = Result.of(null, "Error occurred");
        assertFalse(errorResult.isEmpty());
    }

    @Test
    void givenSome_WhenIfError_ThenNoCallConsumer() {
        Result<String, String> okResult = Result.of("Hello", null);
        StringBuilder result = new StringBuilder();
        okResult.ifError(error -> result.append(error));
        assertEquals("", result.toString());
    }

    @Test
    void givenError_WhenIfError_ThenCallConsumer() {
        Result<String, String> errorResult = Result.of(null, "Error occurred");
        StringBuilder result = new StringBuilder();
        errorResult.ifError(error -> result.append(error));
        assertEquals("Error occurred", result.toString());  
    }

    @Test
    void givenNone_WhenIfError_ThenNoCallConsumer() {
        Result<String, String> noneResult = Result.of(null, null);
        StringBuilder result = new StringBuilder();
        noneResult.ifError(error -> result.append(error));
        assertEquals("", result.toString());
    }
    
    @Test
    void givenSomeAndNullConsumer_WhenIfError_ThenThrowException() {
        Result<String, String> okResult = Result.of(null, "error");
        assertThrows(IllegalArgumentException.class, () -> {
            okResult.ifError(null);
        }, "ifError must not throw IllegalArgumentException when consumer is null");
    }
}
