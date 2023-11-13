package models;

import models.contracts.Comment;
import models.contracts.History;
import models.contracts.Task;
import models.enums.Status;
import utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

abstract class TaskImpl implements Task {
    public static final int TITLE_MIN_LENGTH = 10;
    public static final int TITLE_MAX_LENGTH = 50;
    public static final String INVALID_TITLE_MESSAGE =
            String.format("Title should be between %d and %d symbols.", TITLE_MIN_LENGTH, TITLE_MAX_LENGTH);
    public static final int DESCRIPTION_MIN_LENGTH = 10;
    public static final int DESCRIPTION_MAX_LENGTH = 500;
    public static final String INVALID_DESCRIPTION_MESSAGE =
            String.format("Description should be between %d and %d symbols.",DESCRIPTION_MIN_LENGTH,DESCRIPTION_MAX_LENGTH);
    private int id;
    private static int totalId=1;
    private String title;
    private String description;
    private Status status;
    private List<Comment> comments;
    private List<History> histories;


    public TaskImpl(String title, String description, Status status) {

        this.id=totalId;
        totalId++;

        setTitle(title);
        setDescription(description);
        this.status = status;
        this.comments = new ArrayList<>(comments);
        this.histories = new ArrayList<>(histories);
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public List<Comment> getComments() {
        return new ArrayList<>(comments);
    }

    public List<History> getHistories() {
        return new ArrayList<>(histories);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        ValidationHelpers.validateStringLength(title, TITLE_MIN_LENGTH, TITLE_MAX_LENGTH,String.format(INVALID_TITLE_MESSAGE));
        this.title = title;
    }

    public void setDescription(String description) {
        ValidationHelpers.validateStringLength(description, DESCRIPTION_MIN_LENGTH, DESCRIPTION_MAX_LENGTH, INVALID_DESCRIPTION_MESSAGE);
        this.description = description;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setHistories(List<History> histories) {
        this.histories = histories;
    }
}
