package models.contracts;

import models.enums.StatusBug;
import models.enums.StatusFeedback;
import models.enums.StatusStory;

import java.util.List;

public interface Task extends Identifiable {

    String getTitle();

    String getDescription();

    List<Comment> getComments();

    List<History> getHistories();

    void addHistory(String events);

    String toString();

     StatusBug getStatusBug();
     StatusStory getStatusStory();
    StatusFeedback getStatusFeedback();

}
