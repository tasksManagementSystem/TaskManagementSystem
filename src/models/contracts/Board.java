package models.contracts;

import java.util.List;

public interface Board {

    String getName();

    List<Task> getTasks();

//    List<Bug> getBugs();

    List<String> getActivityHistory();

    void addBug(Bug bug);

    void addStory(Story story);

    void addFeedback(Feedback feedback);

}
