# Not-Null Java Utilities

This project provides two utility types for Java: `Optional` and `Result`. These types are inspired by Rust programming language, this project is designed to help you write safer, more expressive code by avoiding `null` checks and unchecked exceptions.

## Features

- **Optional**: Represents an optional value that may or may not be present.
- **Result**: Represents the result of an operation that can succeed with a value, fail with an error, or be empty.

## Classes

### Optional

Located at: `com.kybsa.Optional`

The `Optional<V>` class is a sealed type with two implementations:

- `Optional.Some<V>`: Contains a value.
- `Optional.None<V>`: Represents the absence of a value.

#### Example

```java
Optional<String> maybeString = Optional.of("Hello, World!");
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


## Requirements

- **Java 21** or higher is required to build and use this project.

Make sure you have a compatible JDK installed. You can check your Java version with:

```sh
java -version
```

## How to include this library

### Maven

Add the following dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>com.kybsa</groupId>
    <artifactId>not-null</artifactId>
    <version>0.0.3</version>
</dependency>
```

### Gradle

Add the following to your `build.gradle` dependencies:

```groovy
dependencies {
    implementation 'com.kybsa:not-null:1.0.0'
}
```
 Replace `1.0.0` with the actual version you want to use.

## License

MIT License.