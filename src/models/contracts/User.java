package models.contracts;

import java.util.List;

public interface User {
    String getName();

    List<Task> getTasks();

    List<String> getActivityHistory();
}
