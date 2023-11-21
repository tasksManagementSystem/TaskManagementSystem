package models.contracts;

import java.util.List;

public interface Task extends Identifiable {


    String getTitle();

    String getDescription();


    List<Comment> getComments();

    List<History> getHistories();

    void logEvent(String event);

    void addHistory(String events);
}
