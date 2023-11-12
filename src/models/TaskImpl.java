package models;

import models.contracts.Comment;
import models.contracts.History;
import models.contracts.Task;
import models.enums.Status;

import java.util.ArrayList;
import java.util.List;

abstract class TaskImpl implements Task {
    private int ID;
    private String title;
    private String description;
    private Status status;
    private List<Comment> comments;
    private List<History> histories;

    public TaskImpl(int ID, String title, String description, Status status, List<Comment> comments, List<History> histories) {
        this.ID = ID;
        this.title = title;
        this.description = description;
        this.status = status;
        this.comments = new ArrayList<>(comments);
        this.histories = new ArrayList<>(histories);
    }

    public int getID() {
        return ID;
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
}
