package models.contracts;

import models.enums.Priority;
import models.enums.Size;
import models.enums.StatusFeedback;
import models.enums.StatusStory;

public interface Story extends Task, TaskInfo, Identifiable {

    Size getSize();

    void changeAssignee(String newAssignee);

    void changeStatusStory(StatusStory status);

    StatusStory getStatusStory();

    void changePriority(Priority newPriority);

    void changeSize(Size newSize);

}
