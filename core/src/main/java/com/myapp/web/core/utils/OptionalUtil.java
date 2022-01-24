package com.myapp.web.core.utils;


import java.util.Optional;
import java.util.function.Supplier;

public class OptionalUtil {

    private OptionalUtil() {

    }

    public static <T> Optional<T> resolveNestedField(Supplier<T> resolver) {
        try {
            T result = resolver.get();
            return Optional.ofNullable(result);
        } catch (RuntimeException e) {
            return Optional.empty();
        }
    }
}
