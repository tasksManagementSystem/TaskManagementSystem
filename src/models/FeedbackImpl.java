package models;

import models.contracts.Feedback;
import models.enums.StatusFeedback;

public class FeedbackImpl extends TaskImpl implements Feedback {
    private int rating;
    private StatusFeedback statusFeedback;

    public FeedbackImpl(String title, String description, int rating) {
        super(title, description);
        setRating(rating);
        statusFeedback = StatusFeedback.NEW;
    }

    public int getRating() {
        return rating;
    }

    private void setRating(int rating) {
        this.rating = rating;
    }
@Override
    public StatusFeedback getStatusFeedback() {
        return statusFeedback;
    }

    public void setStatusFeedback(StatusFeedback statusFeedback) {
        this.statusFeedback = statusFeedback;
    }
}
