package models.contracts;

import java.util.List;

public interface Task extends Identifiable {

    String getTitle();

    String getDescription();

    List<Comment> getComments();

    List<History> getHistories();

    void addHistory(String events);

}
