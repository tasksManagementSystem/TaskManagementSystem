package utils;

public class ValidationHelpers {

    public static void validateStringLength(String stringToValidate, int minLength, int maxLength, String message) {
        validateIntRange(stringToValidate.length(), minLength, maxLength, message);
    }

    private static void validateIntRange(int value, int min, int max, String message) {
        if (value < min || value > max) {
            throw new IllegalArgumentException(message);

        }
    }

}
