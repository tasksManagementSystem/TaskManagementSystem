package models;

import models.contracts.Bug;
import models.enums.*;

import java.util.ArrayList;
import java.util.List;

public class BugImpl extends TaskImpl implements Bug {
    private List<String> stepOfReproduce = new ArrayList<>();
    private Priority priority;
    private Severity severity;
    private String assignee;
    private StatusBug statusBug;
    private int id;


    public BugImpl(int id, String title, String description, List<String> stepOfReproduce,
                   Priority priority, Severity severity, String assignee) {
        super(id, title, description);
        this.stepOfReproduce = stepOfReproduce;
        this.priority = priority;
        this.severity = severity;
        this.assignee = assignee;
        this.statusBug = StatusBug.ACTIVE;

    }
    @Override
    public List<String> getStepOfReproduce() {
        return new ArrayList<>(stepOfReproduce);
    }
    @Override
    public Priority getPriority() {
        return priority;
    }
    @Override
    public Severity getSeverity() {
        return severity;
    }
    @Override
    public StatusBug getStatusBug() {
        return statusBug;
    }

    @Override
    public StatusStory getStatusStory() {
        return null;
    }

    @Override
    public StatusFeedback getStatusFeedback() {
        return null;
    }

    @Override
    public String getAssignee() {
        return assignee;
    }
    public void changeAssignee(String newAssignee) {
        this.assignee = newAssignee;
    }
    public void changeStatus(StatusBug status) {
        statusBug = status;
    }
    public void changePriority(Priority newPriority) {
        priority = newPriority;
    }
    public void changeSeverity(Severity newSeverity) {
        severity = newSeverity;
    }
    @Override
    public String toString() {
        return super.toString();
    }

}
