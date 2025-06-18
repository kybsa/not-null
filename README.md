# Not-Null Java Utilities

This project provides two utility types for Java: `Option` and `Result`. These types are inspired by Rust programming language, this project is designed to help you write safer, more expressive code by avoiding `null` checks and unchecked exceptions.

## Features

- **Option**: Represents an optional value that may or may not be present.
- **Result**: Represents the result of an operation that can succeed with a value, fail with an error, or be empty.

## Classes

### Option

Located at: `com.kybsa.Option`

The `Option<V>` class is a sealed type with two implementations:

- `Option.Some<V>`: Contains a value.
- `Option.None<V>`: Represents the absence of a value.

#### Example

```java
Option<String> maybeString = Option.of("Hello, World!");
switch (maybeString) {
    case Some<String> s -> System.out.println("Found a value: " + s.get());
    case None<String> n -> System.out.println("No value found.");
}
```

### Result

Located at: `com.kybsa.Result`

The `Result<V, E>` class is a sealed type with three implementations:

- `Result.Some<V, E>`: Contains a value.
- `Result.Error<V, E>`: Contains an error.
- `Result.None<V, E>`: Represents the absence of a value.

#### Example

```java
Result<String,Exception> result = Result.of(null,new Exception("Error occurred"));
switch (result) {
    case Result.Some<String,Exception> ok -> System.out.println("Result is OK: " + ok.get());
    case Result.None<String,Exception> none -> System.out.println("Result is None.");
    case Result.Error<String,Exception> error -> System.out.println("Error occurred: " + error.getError());
}
```

## Why use these types?

- Avoid `null` pointer exceptions by making absence explicit.
- Handle errors without exceptions.
- Write more readable and maintainable code.

## License

MIT License.