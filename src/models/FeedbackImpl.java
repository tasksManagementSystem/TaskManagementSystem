package models;

import models.contracts.Comment;
import models.contracts.Feedback;
import models.contracts.History;
import models.enums.Status;

import java.util.List;

public class FeedbackImpl extends TaskImpl implements Feedback {
    private int rating;

    public FeedbackImpl(int ID, String title, String description, Status status, List<Comment> comments, List<History> histories, int rating) {
        super(ID, title, description, status, comments, histories);
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }
}
