package models.contracts;

import models.enums.Priority;

public interface TaskInfo {
    Priority getPriority();

    String getAssignee();
}
