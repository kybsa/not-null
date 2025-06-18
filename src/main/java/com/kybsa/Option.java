package com.kybsa;

/**
 *  A generic Option type that can hold a value or be empty.
 *  This is a sealed class with two concrete implementations:
 *  - Option.Some: Holds a value.
 *  - Option.None: Represents an empty option.
 *  This class is useful for representing optional values without resorting to null checks.
 *  It provides a way to handle the presence or absence of a value in a type-safe manner.
 */
public sealed abstract class Option<V> permits Option.Some, Option.None {

    /**
     *  Creates an Option instance based on the provided value.
     *  If the value is null, it returns an instance of None.
     *  Otherwise, it returns an instance of Some containing the value.
     * @param <V>
     * @param value
     * @return
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
     * @param <V> The type of the value.
    */
    public static final class Some<T> extends Option<T> {
        private final T value;
        private Some(T value) {
            this.value = value;
        }
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
     */
    public static final class None<T> extends Option<T> {
        private static final None<?> NONE = new None<>();        
        private None() {
        }

        @SuppressWarnings("unchecked")
        private static <T> None<T> instance() {
            return (None<T>)NONE;
        }
    }
} 


