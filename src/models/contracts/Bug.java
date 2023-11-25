package models.contracts;

import models.enums.Priority;
import models.enums.Severity;
import models.enums.StatusBug;

import java.util.List;

public interface Bug extends Task, TaskInfo, Identifiable {

    List<String> getStepOfReproduce();

    Severity getSeverity();

    StatusBug getStatusBug();

    void changeAssignee(String newAssignee);

    void changeStatusBug(StatusBug status);

    void changePriority(Priority newPriority);

    void changeSeverity(Severity newSeverity);

}
