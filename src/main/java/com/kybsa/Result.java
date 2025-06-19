package com.kybsa;

/**
 *  A Result type that can hold a value, an error, or be empty.
 *  This is a sealed class with three concrete implementations:
 *  - Result.Some: Holds a value.
 *  - Result.None: Represents an empty result.
 *  - Result.Error: Holds an error.
 *
 *  This class is useful for representing computations that can either succeed with a value,
 *  fail with an error, or be empty.
 *  It provides a way to handle these cases without resorting to null checks or exceptions.
 *  The Result type is generic, allowing you to specify the type of value and the type of error.
 *  The Result.Some and Result.Error classes are final, meaning they cannot be subclassed.
 *  The Result.None class is also final and provides a singleton instance for empty results.
 *  The Result class is designed to be used in a functional programming style, allowing for
 *  pattern matching and functional transformations.
 * @param <V> The type of the value contained in the Result.
 * @param <E> The type of the error contained in the Result.
*/
public sealed abstract class Result<V,E> permits Result.Some, Result.None, Result.Error {

    private Result() {
        // Private constructor to prevent instantiation from outside the class hierarchy.
    }

    /**
     * Creates a Result instance based on the provided value and error.
     * If the error is not null, it returns an Error instance.
     * If the value is null, it returns a None instance.
     * Otherwise, it returns a Some instance containing the value.
     *
     * @param value The value to be wrapped in the Result.
     * @param error The error to be wrapped in the Result, if any.
     * @param <V> The type of the value.
     * @param <E> The type of the error.
     * @return A Result instance representing the provided value and error.
     */
    public static <V,E> Result<V,E> of(V value, E error) {
        if(error != null) {
            return new Error<V,E>(error);
        }
        if (value == null) {
            return None.instance();
        }
        return new Result.Some<V,E>(value);
    }

    /** 
     *  Creates a Result instance containing a value.
     * @param <T> The type of the value.
     * @param <E> Not used in this class, but kept for consistency with Result.
    */
    public static final class Some<T,E> extends Result<T,E> {
        private final T value;
        private Some(T value) {
            this.value = value;
        }
        /**
         *  Gets the value contained in this Result.
         *  This method provides access to the value that was successfully computed.
         *  It is useful for handling successful computations in a type-safe manner.
         * @return The value contained in this Result.
         */
        public T get() {
            return value;
        }
    }    

    /**
     *  Represents an empty Result.
     *  This class is a singleton, meaning there is only one instance of None.
     *  It is used to represent the absence of a value in a Result.
     *  It is a final class, meaning it cannot be subclassed.
     *  The instance method provides a way to get the singleton instance of None.
     *  This class is useful for representing computations that do not yield a value.
     * @param <T> Not used in this class, but kept for consistency with Result.
     * @param <E> Not used in this class, but kept for consistency with Result.
     */
    public static final class None<T,E> extends Result<T,E> {
        private static final None<?,?> NONE = new None<>();        
        private None() {
        }

        @SuppressWarnings("unchecked")
        private static <T,E> None<T,E> instance() {
            return (None<T,E>)NONE;
        }
    }

    /**
     *  Represents a Result that contains an error.
     *  This class is used to encapsulate an error that occurred during a computation.
     *  It is a final class, meaning it cannot be subclassed.
     *  The error is stored in a private final field and can be accessed via the getError method.
     *  This class is useful for representing computations that fail with an error.
     * @param <T> Not used in this class, but kept for consistency with Result.
     * @param <E> The type of the error contained in the Result.
     */
    public static final class Error<T,E> extends Result<T,E> {
        private final E error;
        Error(E error) {
            this.error = error;
        }

        /**
         *  Gets the error contained in this Result.
         *  This method provides access to the error that occurred during the computation.
         *  It is useful for handling errors in a type-safe manner without resorting to exceptions.
         *  @return The error contained in this Result.
         */
        public E getError() {
            return error;
        }
    }
} 


