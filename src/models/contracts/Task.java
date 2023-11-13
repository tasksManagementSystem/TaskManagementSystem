package models.contracts;

import models.enums.Status;
import java.util.List;

public interface Task {
    int getId();

    String getTitle();

    String getDescription();

    Status getStatus();

    List<Comment> getComments();

    List<History> getHistories();
}
