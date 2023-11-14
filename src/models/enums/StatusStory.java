package models.enums;

public enum StatusStory {
    NOT_DONE,
    IN_PROGRESS,

    DONE,

    ;

    @Override
    public String toString() {
        switch (this) {
            case NOT_DONE:
                return "Not_done";
            case IN_PROGRESS:
                return "In_Progress";
            case DONE:
                return "Done";

            default:
                throw new UnsupportedOperationException("Invalid status.");

        }
    }
}