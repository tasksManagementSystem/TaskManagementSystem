package models.contracts;

import java.util.List;

public interface Board {

    String getName();

    List<Bug> getBugs();

    List<Story> getStories();

    List<Feedback> getFeedbacks();

    List<History> getActivityHistory();

    void addBug(Bug bug);

    void addStory(Story story);

    void addFeedback(Feedback feedback);

    void addHistory(String events);

}
