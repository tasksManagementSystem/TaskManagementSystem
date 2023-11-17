package models.contracts;

import java.util.List;

public interface Member {

    String getName();

    List<Task> getTasks();

    List<String> getActivityHistory();

    String showAllMembers();

    void logEvent(String event);

}
