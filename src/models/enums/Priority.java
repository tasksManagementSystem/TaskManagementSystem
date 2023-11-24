package models.enums;

public enum Priority {
    HIGH,
    LOW,
    MEDIUM;

    @Override
    public String toString() {
        switch (this){
            case HIGH:
                return "High";
            case MEDIUM:
                return "Medium";
            case LOW:
                return "Low";
            default:
                throw new UnsupportedOperationException("Invalid priority.");
        }
    }
}
