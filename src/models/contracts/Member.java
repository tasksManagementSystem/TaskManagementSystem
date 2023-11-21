package models.contracts;

import java.util.List;

public interface Member {

    String getName();

    List<History> getActivityHistory();

    void addHistory(String events);
    List<Task> getTasks();

}
