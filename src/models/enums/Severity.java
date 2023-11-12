package models.enums;

public enum Severity {
    CRITICAL,
    MAJOR,
    MINOR;

    @Override
    public String toString() {
        switch (this) {
            case CRITICAL:
                return "Critical";
            case MAJOR:
                return "Major";
            case MINOR:
                return "Minor";
            default:
                throw new UnsupportedOperationException("Invalid severity.");
        }
    }
}
