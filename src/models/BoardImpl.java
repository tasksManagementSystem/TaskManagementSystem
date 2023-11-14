package models;

import models.contracts.Board;
import models.contracts.Task;

import java.util.ArrayList;
import java.util.List;

public class BoardImpl implements Board {
    private String name;
    private List<Task> tasks;
    private List<String> activityHistory;

    public BoardImpl(String name, List<Task> tasks, List<String> activityHistory) {
        this.name = name;
        this.tasks = tasks;
        this.activityHistory = new ArrayList<>(activityHistory);
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

        this.name = name;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void setActivityHistory(List<String> activityHistory) {
        this.activityHistory = activityHistory;
    }
}
