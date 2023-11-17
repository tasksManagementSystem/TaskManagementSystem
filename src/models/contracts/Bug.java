package models.contracts;

import models.enums.Priority;
import models.enums.Severity;
import models.enums.StatusBug;

import java.util.List;

public interface Bug extends Task, TaskInfo {

    List<String> getStepOfReproduce();


    Severity getSeverity();

    StatusBug getStatusBug();






}
