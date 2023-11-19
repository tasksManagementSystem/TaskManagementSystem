package models.contracts;

import java.util.List;

public interface Board {

    String getName();

    List<Task> getTasks();

     List<Bug> getBugs();
    List<Story> getStories();
    List<Feedback> getFeedbacks();

    List<History> getActivityHistory();

    void addBug(Bug bug);

    void addStory(Story story);

    void addFeedback(Feedback feedback);

     int changeFeedback(Feedback feedback);
     void addHistory(String events);
    String viewInfo(History event);
}
