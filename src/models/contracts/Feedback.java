package models.contracts;

import models.enums.StatusFeedback;

public interface Feedback extends Task, Identifiable {
    int getRating();

    StatusFeedback getStatusFeedback();

    void changeStatusFeedback(StatusFeedback status);

    void changeRating(int newRating);
}
