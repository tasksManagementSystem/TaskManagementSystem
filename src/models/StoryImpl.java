package models;

import models.contracts.Comment;
import models.contracts.History;
import models.contracts.Story;
import models.contracts.Task;
import models.enums.Priority;
import models.enums.Size;
import models.enums.Status;

import java.util.List;

public class StoryImpl extends TaskImpl implements Story {
    private Priority priority;
    private Size size;
    private String assignee;

    public StoryImpl(int ID, String title, String description, Status status, List<Comment> comments, List<History> histories, Priority priority, Size size, String assignee) {
        super(ID, title, description, status, comments, histories);
        this.priority = priority;
        this.size = size;
        this.assignee = assignee;
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
}
