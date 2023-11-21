package models;

import models.contracts.Feedback;
import models.enums.StatusFeedback;

import java.util.List;

public class FeedbackImpl extends TaskImpl implements Feedback {
    private int rating;
    private StatusFeedback statusFeedback;


    public FeedbackImpl(int id,String title, String description, int rating) {
        super(id,title, description);
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

    @Override
    public void logEvent(String event) {

    }
    public  void changeStatus(StatusFeedback status){
        statusFeedback=status;

    }
    public  void changeRating(int newRating){
        rating=newRating;

    }

    @Override
    public String toString() {
        return "FeedbackImpl{" +
                "rating=" + rating +
                ", statusFeedback=" + statusFeedback +
                '}';
    }
}
