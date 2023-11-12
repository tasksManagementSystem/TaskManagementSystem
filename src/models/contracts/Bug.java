package models.contracts;

import models.enums.Priority;
import models.enums.Severity;

import java.util.List;

public interface Bug extends Task, TaskInfo {

    List<String> getStepOfReproduce();

    Severity getSeverity();


}
