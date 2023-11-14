package models.enums;

public enum StatusBug {
    ACTIVE,
    FIXED,

    ;

    @Override
    public String toString() {
        switch (this) {
            case ACTIVE:
                return "Active";
            case FIXED:
                return "Fixed";

            default:
                throw new UnsupportedOperationException("Invalid status.");

        }
    }
}