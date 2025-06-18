package com.kybsa;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ResultTest {
    @Test
    public void testResultOk() {
        Result<String, String> okResult = Result.of("Success", null);
        assertTrue(okResult instanceof Result.Some);
        assertEquals("Success", ((Result.Some<String, String>) okResult).get());
    }
    @Test
    public void testResultNone() {
          Result<String, String> noneResult = Result.of(null, null);
        assertTrue(noneResult instanceof Result.None);
    }
    @Test
    public void testResultError() {
        Result<String, String> errorResult = Result.of(null, "Error occurred");
        assertTrue(errorResult instanceof Result.Error);
        assertEquals("Error occurred", ((Result.Error<String, String>) errorResult).getError());
    }
}
