package models.contracts;

import java.util.List;

public interface Member {

    String getName();

    List<Task> getTasks();

    List<History> getActivityHistory();

    String showAllMembers();

    void logEvent(String event);

    List<String> getAllMemberNames();
    void addHistory(String events);

}
