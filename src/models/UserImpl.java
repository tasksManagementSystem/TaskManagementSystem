package models;

import models.contracts.Task;
import models.contracts.User;

import java.util.ArrayList;
import java.util.List;

abstract class UserImpl implements User {
     private String name;
     private List<Task> tasks;
     private List<String> activityHistory;

    public UserImpl(String name, List<Task> tasks, List<String> activityHistory) {
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
}
