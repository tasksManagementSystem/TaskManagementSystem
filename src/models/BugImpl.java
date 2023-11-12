package models;

import models.contracts.*;
import models.enums.Priority;
import models.enums.Severity;
import models.enums.Status;

import java.util.ArrayList;
import java.util.List;

public class BugImpl extends TaskImpl implements Bug {
    private List<String> stepOfReproduce;
    private Priority priority;
    private Severity severity;
    private String assignee;

    public BugImpl(int ID, String title, String description, Status status, List<Comment> comments, List<History> histories, List<String> stepOfReproduce,
                   Priority priority,Severity severity,String assignee) {
        super(ID, title, description, status, comments, histories);
        this.stepOfReproduce=new ArrayList<>(stepOfReproduce);
        this.priority=priority;
        this.severity=severity;
        this.assignee=assignee;

    }

    public List<String> getStepOfReproduce() {
        return new ArrayList<>(stepOfReproduce);
    }

    public Priority getPriority() {
        return priority;
    }

    public Severity getSeverity() {
        return severity;
    }

    public String getAssignee() {
        return assignee;
    }
}
