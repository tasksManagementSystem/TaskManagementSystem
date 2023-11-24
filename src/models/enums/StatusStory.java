package models.enums;

public enum StatusStory {
    DONE,
    IN_PROGRESS,
    NOT_DONE;

    @Override
    public String toString() {
        switch (this) {
            case NOT_DONE:
                return "Not done";
            case IN_PROGRESS:
                return "In Progress";
            case DONE:
                return "Done";

            default:
                throw new UnsupportedOperationException("Invalid status.");

        }
    }
}