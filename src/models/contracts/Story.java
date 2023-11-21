package models.contracts;

import models.enums.Priority;
import models.enums.Size;
import models.enums.StatusFeedback;
import models.enums.StatusStory;

public interface Story extends Task, TaskInfo {

    Size getSize();

    void changeAssignee(String newAssignee);
    void changeStatus(StatusStory status);
    StatusStory getStatusStory();

    void changePriority(Priority storyPriority);

}
