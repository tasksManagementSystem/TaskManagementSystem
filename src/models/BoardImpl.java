package models;

import models.contracts.*;
import java.util.ArrayList;
import java.util.List;

public class BoardImpl implements Board {

    private String name;
    private List<History> activityHistory= new ArrayList<>();
    private final List<Feedback> feedbacks;
    private final List<Story> stories;
    private final List<Bug> bugs;

    public BoardImpl(String name) {
        setName(name);
        this.activityHistory = new ArrayList<>();
        this.feedbacks = new ArrayList<>();
        this.stories = new ArrayList<>();
        this.bugs=new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Bug> getBugs() {
        return new ArrayList<>(bugs);
    }

    public List<Feedback> getFeedbacks() {
        return new ArrayList<>(feedbacks);
    }

    public List<Story> getStories() {
        return new ArrayList<>(stories);
    }

    public List<History> getActivityHistory() {
        return new ArrayList<>(activityHistory);
    }

    public void addHistory(String events) {
        History event= new HistoryImpl(events);
        activityHistory.add(event);
    }
    @Override
    public void addBug(Bug bug) {
        bugs.add(bug);
    }
    public void addFeedback(Feedback feedback) {
        feedbacks.add(feedback);
    }

    public void addStory(Story story) {
        stories.add(story);
    }
    private void setName(String name) {
        this.name = name;
    }

}

