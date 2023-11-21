package models;

import models.contracts.Story;
import models.contracts.TaskInfo;
import models.enums.Priority;
import models.enums.Size;
import models.enums.StatusFeedback;
import models.enums.StatusStory;

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

    @Override
    public void logEvent(String event) {

    }

    public void changeAssignee(String newAssignee) {
        this.assignee = newAssignee;

    }
    public  void changeStatus(StatusStory status){
        statusStory=status;

    }

    public void changePriority(Priority storyPriority){
        priority = storyPriority;
    }

    public void changeSize(Size storySize){
        size = storySize;
    }
}
