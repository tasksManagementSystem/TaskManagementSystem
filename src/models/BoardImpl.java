package models;

import models.contracts.Board;
import models.contracts.Task;
import utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public class BoardImpl implements Board {

    public static final String INVALID_NAME_MESSAGE = "Name should be between %d and %d symbols.";
    public static final int BOARD_NAME_MIN_LENGTH = 5;
    public static final int BOARD_NAME_MAX_LENGTH = 10;
    private String name;
    private List<Task> tasks;
    private List<String> activityHistory;

    public BoardImpl(String name) {
        setName(name);
        this.tasks = new ArrayList<>();
        this.activityHistory = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public List<String> getActivityHistory() {
        return activityHistory;
    }

    public void setName(String name) {
        ValidationHelpers.validateStringLength(name, BOARD_NAME_MIN_LENGTH,BOARD_NAME_MAX_LENGTH,String.format(INVALID_NAME_MESSAGE));
        this.name = name;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void setActivityHistory(List<String> activityHistory) {
        this.activityHistory = activityHistory;
    }
}
