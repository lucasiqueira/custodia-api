package br.com.siqueira.shared.utils;

import java.util.Collection;
import java.util.Objects;

public final class Parameters {

    private Parameters() {
        // Utility class
    }

    // =========================
    // Null checks
    // =========================

    public static <T> T requireNonNull(T value, String paramName) {
        if (value == null) {
            throw new IllegalArgumentException(paramName + " must not be null");
        }
        return value;
    }

    // =========================
    // String validations
    // =========================

    public static String requireNonBlank(String value, String paramName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(paramName + " must not be blank");
        }
        return value;
    }

    public static String requireMaxLength(String value, int maxLength, String paramName) {
        if (value != null && value.length() > maxLength) {
            throw new IllegalArgumentException(
                    paramName + " must have at most " + maxLength + " characters");
        }
        return value;
    }

    public static String requireMinLength(String value, int minLength, String paramName) {
        if (value == null || value.length() < minLength) {
            throw new IllegalArgumentException(
                    paramName + " must have at least " + minLength + " characters");
        }
        return value;
    }

    // =========================
    // Number validations
    // =========================

    public static Long requirePositive(Long value, String paramName) {
        if (value == null || value <= 0) {
            throw new IllegalArgumentException(paramName + " must be positive");
        }
        return value;
    }

    public static Integer requirePositive(Integer value, String paramName) {
        if (value == null || value <= 0) {
            throw new IllegalArgumentException(paramName + " must be positive");
        }
        return value;
    }

    public static Long requireNonNegative(Long value, String paramName) {
        if (value == null || value < 0) {
            throw new IllegalArgumentException(paramName + " must be zero or positive");
        }
        return value;
    }

    // =========================
    // Collections
    // =========================

    public static <T> Collection<T> requireNonEmpty(Collection<T> collection, String paramName) {
        if (collection == null || collection.isEmpty()) {
            throw new IllegalArgumentException(paramName + " must not be empty");
        }
        return collection;
    }

    // =========================
    // Generic condition
    // =========================

    public static void require(boolean condition, String message) {
        if (!condition) {
            throw new IllegalArgumentException(message);
        }
    }

    // =========================
    // Equality
    // =========================

    public static void requireEquals(Object expected, Object actual, String message) {
        if (!Objects.equals(expected, actual)) {
            throw new IllegalArgumentException(message);
        }
    }
}

