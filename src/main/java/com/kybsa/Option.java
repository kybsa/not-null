package com.kybsa;

/**
 *  A generic Option type that can hold a value or be empty.
 *  This is a sealed class with two concrete implementations:
 *  - Option.Some: Holds a value.
 *  - Option.None: Represents an empty option.
 *  This class is useful for representing optional values without resorting to null checks.
 *  It provides a way to handle the presence or absence of a value in a type-safe manner.
 * @param <V> The type of the value contained in the Option.
 */
public sealed abstract class Option<V> permits Option.Some, Option.None {

    private Option() {
        // Private constructor to prevent instantiation from outside the class hierarchy.
    }

    /**
     *  Creates an Option instance based on the provided value.
     *  If the value is null, it returns an instance of None.
     *  Otherwise, it returns an instance of Some containing the value.
     * @param <V> The type of the value.
     * @param value The value to be wrapped in the Option.
     * @return An Option instance representing the provided value.
     */
    public static <V> Option<V> of(V value) {       
        if (value == null) {
            return None.instance();
        }
        return new Option.Some<V>(value);
    }

    /** 
     * Creates an Option instance containing a value.
     * This method is a convenience method to create an Option.Some instance.
     * @param <T> The type of the value.
    */
    public static final class Some<T> extends Option<T> {
        private final T value;
        /**
         * Constructor for Some.
         * This constructor initializes the Some instance with the provided value.
         * It is used to create an Option that contains a value.
         * It is a final class, meaning it cannot be subclassed.
         * @param value
         */
        private Some(T value) {
            this.value = value;
        }
        /**
         * Gets the value contained in this Option.
         * This method provides access to the value that was successfully computed.
         * It is useful for handling successful computations in a type-safe manner.
         * @return The value contained in this Option.
         */
        public T get() {
            return value;
        }
    }    

    /**
     *  Represents an empty Option.
     *  This class is a singleton, meaning there is only one instance of None.
     *  It is used to represent the absence of a value in an Option.
     *  It is a final class, meaning it cannot be subclassed.
     *  The instance method provides a way to get the singleton instance of None.
     *  This class is useful for representing computations that do not yield a value.
     *  It provides a type-safe way to represent the absence of a value without using null.
     *  @param <T> Not used in this class, but kept for consistency with Option.
     */
    public static final class None<T> extends Option<T> {
        private static final None<?> NONE = new None<>();   
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
         * @param <T> The type of the value, not used in this class but kept for consistency with Option.
         * @return The singleton instance of None.
         */
        @SuppressWarnings("unchecked")
        private static <T> None<T> instance() {
            return (None<T>)NONE;
        }
    }
} 


