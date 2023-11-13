package models;

import models.contracts.Feedback;
import models.enums.Status;

public class FeedbackImpl extends TaskImpl implements Feedback {
    private int rating;

    public FeedbackImpl(String title, String description, Status status, int rating) {
        super(title, description, status);
        setRating(rating);
    }

    public int getRating() {
        return rating;
    }

    private void setRating(int rating) {
        this.rating = rating;
    }
}
