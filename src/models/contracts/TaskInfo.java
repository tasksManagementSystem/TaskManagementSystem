package models.contracts;

import models.enums.Priority;
import models.enums.StatusBug;
import models.enums.StatusFeedback;
import models.enums.StatusStory;

public interface TaskInfo {
    Priority getPriority();

    String getAssignee();
}
