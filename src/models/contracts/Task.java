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

  default StatusBug getStatusBug(){ return StatusBug.ACTIVE;}
  default StatusStory getStatusStory(){return  StatusStory.NOT_DONE;}
 default StatusFeedback getStatusFeedback(){return StatusFeedback.NEW;};

}
