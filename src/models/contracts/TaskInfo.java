package models.contracts;

import models.enums.Priority;
import models.enums.Size;

public interface TaskInfo {
    Priority getPriority();

    String getAssignee();
}
