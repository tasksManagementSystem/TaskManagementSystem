package models;

import models.contracts.Story;
import models.enums.Priority;
import models.enums.Size;
import models.enums.Status;

public class StoryImpl extends TaskImpl implements Story {
    private Priority priority;
    private Size size;
    private String assignee;

    public StoryImpl(String title, String description, Status status, Priority priority, Size size, String assignee) {
        super(title, description, status);
        setPriority(priority);
        setSize(size);
        setAssignee(assignee);
    }

    public Priority getPriority() {
        return priority;
    }

    public Size getSize() {
        return size;
    }

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
}
