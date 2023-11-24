package models.enums;

public enum StatusFeedback {
    DONE,
    NEW,
    SCHEDULED,
    UNSCHEDULED;

    @Override
    public String toString() {
        switch (this) {

            case DONE:
                return "Done";

            case NEW:
                return "New";
            case UNSCHEDULED:
                return "Unscheduled";
            case SCHEDULED:
                return "Scheduled";
            default:
                throw new UnsupportedOperationException("Invalid status.");

        }
    }
}