package models;

import models.contracts.*;
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

    private final List<Feedback> feedbacks;
    private final List<Story> stories;

    public BoardImpl(String name) {
        setName(name);
        this.tasks = new ArrayList<>();
        this.activityHistory = new ArrayList<>();
        this.feedbacks = new ArrayList<>();
        this.stories = new ArrayList<>();
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

    @Override
    public void addBug(Bug bug) {
    }
    public void addFeedback(Feedback feedback) {
        feedbacks.add(feedback);
    }

    public void addStory(Story story) {
        stories.add(story);
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
