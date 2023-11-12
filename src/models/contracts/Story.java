package models.contracts;

import models.enums.Size;

public interface Story extends Task, TaskInfo {

    Size getSize();


}
