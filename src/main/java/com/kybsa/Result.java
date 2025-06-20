package com.kybsa;

import java.util.function.Consumer;


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
public sealed interface Result<V,E> permits Result.Some, Result.None, Result.Error {

    static final None<?,?> NONE = new None<>();      

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
    @SuppressWarnings("unchecked")
    public static <V,E> Result<V,E> of(V value, E error) {
        if(error != null) {
            return new Error<>(error);
        }
        if (value == null) {
            return (None<V,E>)NONE;
        }
        return new Result.Some<>(value);
    }
    
    /**
     * Checks if this Result contains a value.
     * @return  true if this Result contains a value (i.e., it is an instance of Some),
     *          false otherwise (i.e., it is an instance of None or Error).
     */
    boolean isPresent();

    /**
     * Checks if this Result is empty.
     * A Result is considered empty if it is an instance of None.
     * This method provides a way to determine if there is a value present in the Result.
     * @return true if this Result is empty (i.e., it is an instance of None),
     */
    boolean isEmpty();

    /**
     * Calls the provided consumer if this Result contains a value.
     * If this Result is None or Error, the consumer is not called.
     * This method is used to perform an action with the value if it is present.
     * @param consumer The consumer to be called with the value if present.
     */
    void ifPresent(Consumer<? super V> consumer);

    /**
     * Calls the provided consumer if this Result contains an error.
     * @param consumer The consumer to be called with the error if present.
     */
    void ifError(Consumer<? super E> consumer);

    /** 
     *  Creates a Result instance containing a value.
     * @param <T> The type of the value.
     * @param <E> Not used in this class, but kept for consistency with Result.
    */
    public static final class Some<T,E> implements Result<T,E> {
        private final T value;

        /**
         * Constructor for Some.
         * @param value The value to be contained in this Result.
         */
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

        /**
         * Since this Result contains a value, this method always returns true.
         * @return true, indicating that this Result contains a value.
         */
        @Override
        public boolean isPresent() {
            return true;
        }

        /**
         * Checks if this Result is empty.
         * Since this Result is Some, it is never empty.
         * @return false, indicating that this Result is not empty.
         */
        @Override
        public boolean isEmpty() {
            return false;
        }

        /**
         * Calls the provided consumer with the value contained in this Result.
         * This method is used to perform an action with the value if it is present.
         * @param consumer The consumer to be called with the value.
         * @throws IllegalArgumentException if the consumer is null.
         */
        @Override
        public void ifPresent(Consumer<? super T> consumer) {
            if (consumer == null) {
                throw new IllegalArgumentException("Consumer must not be null");
            }
            consumer.accept(value);
        }

        /**
         * This method does nothing since this Result is Some and does not contain an error.
         * @param consumer The consumer to be called with the error, which will never be called.
         */
        public  void ifError(Consumer<? super E> consumer){
            // No action is taken since this Result is Some and does not contain an error.
            // This method is provided to allow for a consistent interface with Result.Error.
            // It is a no-op in this case.
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
    public static final class None<T,E> implements Result<T,E> {    
        
        /**
         * Constructor for None.
         * This constructor is private to enforce the singleton pattern.
         */
        private None() {
        }

        /**
         * Since this Result is None, it is always return false.
         * @return false, indicating that this Result does not contain a value.
         */
        @Override
        public boolean isPresent() {
            return false;
        }

        /**
         * Checks if this Result is empty.
         * Since this Result is None, it is always empty.
         * @return true, indicating that this Result is empty.
         */
        @Override
        public boolean isEmpty() {
            return true;
        }

        /**
         * This method does nothing since this Result is None.
         * It is provided to allow for a consistent interface with Result.Some.
         * @param consumer The consumer to be called with the value, which will never be called.
         */
        @Override
        public void ifPresent(Consumer<? super T> consumer) {          
            // No action is taken since this Result is None. 
        }

        /**
         * This method does nothing since this Result is None and does not contain an error.
         * @param consumer The consumer to be called with the error, which will never be called
         */
        public  void ifError(Consumer<? super E> consumer){
            // No action is taken since this Result is Some and does not contain an error.
            // This method is provided to allow for a consistent interface with Result.Error.
            // It is a no-op in this case.
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
    public static final class Error<T,E> implements Result<T,E> {
        private final E cause;
        /**
         * Constructor for Error.
         * This constructor initializes the error contained in this Result.
         * @param cause The error to be contained in this Result.
         */
        Error(E cause) {
            this.cause = cause;
        }

        /**
         *  Gets the error contained in this Result.
         *  This method provides access to the error that occurred during the computation.
         *  It is useful for handling errors in a type-safe manner without resorting to exceptions.
         *  @return The error contained in this Result.
         */
        public E getCause() {
            return cause;
        }

        /**
         * Since this Result contains an error, it is never present.
         * @return false, indicating that this Result does not contain a value.
         */
        @Override
        public boolean isPresent() {
            return false;
        }

        /**
         * This method does nothing since this Result is an Error.
         * It is provided to allow for a consistent interface with Result.Some.
         * @param consumer The consumer to be called with the value, which will never be called.
         */
        @Override
        public void ifPresent(Consumer<? super T> consumer) {  
            // No action is taken since this Result is an Error.        
        }

        /**
         * Checks if this Result is empty.
         * Since this Result is an Error, it is never empty.
         * @return false, indicating that this Result is not empty.
         */
        @Override
        public boolean isEmpty() {
            return false;
        }

        /**
         * Calls the provided consumer with the error contained in this Result.
         * This method is used to handle errors in a type-safe manner without resorting to exceptions.
         * @param consumer The consumer to be called with the error.
         * @throws IllegalArgumentException if the consumer is null.
         */
        public void ifError(Consumer<? super E> consumer){
            if (consumer == null) {
                throw new IllegalArgumentException("Consumer must not be null");
            }
            consumer.accept(cause);
        }
    }
} 


