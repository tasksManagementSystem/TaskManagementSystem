package models;

import models.contracts.Story;
import models.enums.*;

public class StoryImpl extends TaskImpl implements Story {
    private Priority priority;
    private Size size;
    private String assignee;
    private StatusStory statusStory;

    public StoryImpl(int id, String title, String description, Priority priority, Size size, String assignee) {
        super(id, title, description);
        setPriority(priority);
        setSize(size);
        setAssignee(assignee);
        statusStory = StatusStory.NOT_DONE;
    }

    @Override
    public StatusStory getStatusStory() {
        return statusStory;
    }

    @Override
    public StatusFeedback getStatusFeedback() {
        return null;
    }

    @Override
    public Priority getPriority() {
        return priority;
    }

    @Override
    public Size getSize() {
        return size;
    }

    @Override
    public String getAssignee() {
        return assignee;
    }

    private void setPriority(Priority priority) {
        this.priority = priority;
    }

    private void setSize(Size size) {
        this.size = size;
    }

    private void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public void changeAssignee(String newAssignee) {
        this.assignee = newAssignee;
    }

    public void changeStatusStory(StatusStory status) {
        statusStory = status;
    }

    public void changePriority(Priority newPriority) {
        priority = newPriority;
    }

    public void changeSize(Size newSize) {
        size = newSize;
    }

    @Override
    public String toString() {
        return "StoryImpl{" +
                "priority=" + priority +
                ", size=" + size +
                ", assignee='" + assignee + '\'' +
                ", statusStory=" + statusStory +
                '}';
    }

    @Override
    public StatusBug getStatusBug() {
        return null;
    }

}
