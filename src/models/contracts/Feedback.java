package models.contracts;

import models.enums.StatusFeedback;

public interface Feedback extends Task {
    int getRating();
    StatusFeedback getStatusFeedback();
    void changeStatus(StatusFeedback status);
}
