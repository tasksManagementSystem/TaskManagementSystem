package models;

import models.contracts.Bug;
import models.enums.*;

import java.util.ArrayList;
import java.util.List;

public class BugImpl extends TaskImpl implements Bug {
    public static final String NOT_ASSIGN = "Not assign";
    private List<String> stepOfReproduce;
    private Priority priority;
    private Severity severity;
    private String assignee;
    private StatusBug statusBug;



    public BugImpl(int id, String title, String description, List<String> stepOfReproduce,
                   Priority priority, Severity severity, String assignee) {
        super(id, title, description);
        this.stepOfReproduce = stepOfReproduce;
        this.priority = priority;
        this.severity = severity;
        setAssignee(assignee);
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

    private void setAssignee(String assignee) {
        if (assignee == null) {
            this.assignee = NOT_ASSIGN;
        } else {
            this.assignee = assignee;
        }
    }
    public void unassigned(String name){
        assignee=NOT_ASSIGN;
    }

    @Override
    public void changeAssignee(String newAssignee) {
        this.assignee = newAssignee;
    }

    @Override
    public void changeStatusBug(StatusBug status) {
        statusBug = status;
    }

    @Override
    public void changePriority(Priority newPriority) {
        priority = newPriority;
    }

    @Override
    public void changeSeverity(Severity newSeverity) {
        severity = newSeverity;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
