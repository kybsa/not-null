package com.kybsa;

import java.util.function.Consumer;

/**
 *  A generic Optional type that can hold a value or be empty.
 *  This is a sealed class with two concrete implementations:
 *  - Optional.Some: Holds a value.
 *  - Optional.None: Represents an empty Optional.
 *  This class is useful for representing optional values without resorting to null checks.
 *  It provides a way to handle the presence or absence of a value in a type-safe manner.
 * @param <V> The type of the value contained in the Optional.
 */
public sealed interface Optional<V> permits Optional.Some, Optional.None {

    /**
     *  Creates an Optional instance based on the provided value.
     *  If the value is null, it returns an instance of None.
     *  Otherwise, it returns an instance of Some containing the value.
     * @param <V> The type of the value.
     * @param value The value to be wrapped in the Optional.
     * @return An Optional instance representing the provided value.
     */
    static <V> Optional<V> of(V value) {       
        if (value == null) {
            return None.instance();
        }
        return new Optional.Some<>(value);
    }

    /**
     * Checks if this Optional contains a value.
     * @return true if this Optional contains a value (i.e., it is an instance of Some), false otherwise (i.e., it is an instance of None).
     */
    boolean isPresent() ;

    /**
     * Calls the provided consumer if this Optional contains a value.
     * If this Optional is None, the consumer is not called.
     * @param consumer The consumer to be called with the value if present.
     */
    void ifPresent(Consumer<? super V> consumer);

    /**
     * Checks if this Optional is empty.
     * An Optional is considered empty if it is an instance of None.
     * This method provides a way to determine if there is a value present in the Optional.
     * @return true if this Optional is empty (i.e., it is an instance of None), false otherwise.
     */
    boolean isEmpty() ;
    
    /**
     * Executes the provided action if this Optional contains a value.
     * If this Optional is None, the emptyAction is executed instead.
     * This method provides a way to handle both cases (value present and value absent) in a type-safe manner.
     * @param consumerIfNotEmpty The action to be executed with the value if present.
     * @param runnableIfEmpty The action to be executed if this Optional is empty.
     */
    void ifPresentOrElse(Consumer<? super V> consumerIfNotEmpty, Runnable runnableIfEmpty);

    /** 
     * Creates an Optional instance containing a value.
     * This method is a convenience method to create an Optional.Some instance.
     * @param <T> The type of the value.
    */
    public static final class Some<T> implements Optional<T> {
        private final T value;
        /**
         * Constructor for Some.
         * This constructor initializes the Some instance with the provided value.
         * It is used to create an Optional that contains a value.
         * It is a final class, meaning it cannot be subclassed.
         * @param value The value to be contained in this Optional.
         */
        private Some(T value) {
            this.value = value;
        }
        /**
         * Gets the value contained in this Optional.
         * This method provides access to the value that was successfully computed.
         * It is useful for handling successful computations in a type-safe manner.
         * @return The value contained in this Optional.
         */
        public T get() {
            return value;
        }

        /**
         * Since this Optional is Some, it is always present.
         * @return true, indicating that this Optional contains a value.
         */
        @Override
        public boolean isPresent() {
            return true;
        }

        /**
         * Calls the provided consumer with the value if this Optional.
         * @param consumer The consumer to be called with the value.
         */
        @Override
        public void ifPresent(Consumer<? super T> consumer) {
            if (consumer == null) {
                throw new IllegalArgumentException("Consumer must not be null");
            }
            consumer.accept(value);
        }

        /**
         * Checks if this Optional is empty.
         * Since this is Some, it is never empty.
         * @return false, indicating that this Optional contains a value.
         */
        @Override
        public boolean isEmpty() {
            return false;
        }

        /**
         * Executes the provided consumerIfNotEmpty.
         * @param consumerIfNotEmpty The action to be executed with the value present.
         * @param runnableIfEmpty The action to be executed if this Optional is empty since this is Some it will never call the runnable.
         */
        @Override
        public void ifPresentOrElse(Consumer<? super T> consumerIfNotEmpty, Runnable runnableIfEmpty) {
            if(consumerIfNotEmpty == null) {
                throw new IllegalArgumentException("Consumer must not be null");
            }
            consumerIfNotEmpty.accept(value);   
        }
    }    

    /**
     *  Represents an empty Optional.
     *  This class is a singleton, meaning there is only one instance of None.
     *  It is used to represent the absence of a value in an Optional.
     *  It is a final class, meaning it cannot be subclassed.
     *  The instance method provides a way to get the singleton instance of None.
     *  This class is useful for representing computations that do not yield a value.
     *  It provides a type-safe way to represent the absence of a value without using null.
     *  @param <T> Not used in this class, but kept for consistency with Optional.
     */
    public static final class None<T> implements Optional<T> {
        private static final None<?> INSTANCE = new None<>();   
        /**
         * Private constructor for None.
         * This constructor is private to enforce the singleton pattern.
         * It prevents the creation of multiple instances of None.
         */     
        private None() {
        }

        /**
         * Provides a singleton instance of None.
         * This method returns the single instance of None, ensuring that there is only one instance of this class.
         * It is a static method that can be called without creating an instance of None.
         * @param <T> The type of the value, not used in this class but kept for consistency with Optional.
         * @return The singleton instance of None.
         */
        @SuppressWarnings("unchecked")
        private static <T> None<T> instance() {
            return (None<T>)INSTANCE;
        }

        /**
         * Since this Optional is None, it is never present.
         * @return false, indicating that this Optional does not contain a value.
         */
        @Override
        public boolean isPresent() {
            return false;
        }

        /**
         * Calls the provided consumer with the value if this Optional.
         * Since this is None, the consumer is not called.
         * @param consumer The consumer to be called with the value, if present.
         */
        @Override
        public void ifPresent(Consumer<? super T> consumer) {
            // Do nothing, as there is no value to consume.
        }

        /**
         * Checks if this Optional is empty.
         * Since this is None, it is always empty.
         * @return true, indicating that this Optional does not contain a value.
         */
        @Override
        public boolean isEmpty() {
            return true;
        }

        /**
         * Executes the provided runnableIfEmpty.
         * Since this is None, the runnable is executed.
         * @param consumerIfNotEmpty The action to be executed with the value present, not called in this case.
         * @param runnableIfEmpty The action to be executed.
         */
        @Override
        public void ifPresentOrElse(Consumer<? super T> consumerIfNotEmpty, Runnable runnableIfEmpty) {
            if(runnableIfEmpty == null) {
                throw new IllegalArgumentException("runnable must not be null");
            }
            runnableIfEmpty.run(); // Execute the empty action since this is None   
        }
    }
} 


