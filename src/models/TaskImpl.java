package models;

import models.contracts.Comment;
import models.contracts.History;
import models.contracts.Task;
import utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

abstract class TaskImpl implements Task {
    public static final int TITLE_MIN_LENGTH = 10;
    public static final int TITLE_MAX_LENGTH = 100;
    public static final String INVALID_TITLE_MESSAGE =
            String.format("Title should be between %d and %d symbols.", TITLE_MIN_LENGTH, TITLE_MAX_LENGTH);
    public static final int DESCRIPTION_MIN_LENGTH = 10;
    public static final int DESCRIPTION_MAX_LENGTH = 500;
    public static final String INVALID_DESCRIPTION_MESSAGE =
            String.format("Description should be between %d and %d symbols.", DESCRIPTION_MIN_LENGTH, DESCRIPTION_MAX_LENGTH);
    private int id;
    private String title;
    private String description;

    //TO DO
    private List<Comment> comments;
    private List<History> history = new ArrayList<>();


    public TaskImpl(int id, String title, String description) {
        setId(id);
        setTitle(title);
        setDescription(description);
        this.comments = new ArrayList<>();
        addHistory(String.format("Create task with ID %d", id));

    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public List<Comment> getComments() {
        return new ArrayList<>(comments);
    }

    @Override
    public List<History> getHistories() {
        return new ArrayList<>(history);
    }

    private void setId(int id) {
        this.id = id;
    }

    private void setTitle(String title) {
        ValidationHelpers.validateStringLength(title, TITLE_MIN_LENGTH, TITLE_MAX_LENGTH, String.format(INVALID_TITLE_MESSAGE));
        this.title = title;
    }

    private void setDescription(String description) {
        ValidationHelpers.validateStringLength(description, DESCRIPTION_MIN_LENGTH, DESCRIPTION_MAX_LENGTH, INVALID_DESCRIPTION_MESSAGE);
        this.description = description;
    }

    public void addHistory(String events) {
        History event = new HistoryImpl(events);
        history.add(event);
    }
}
