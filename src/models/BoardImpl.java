package models;

import models.contracts.*;
import utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public class BoardImpl implements Board {

    public static final String INVALID_NAME_MESSAGE = "Name should be between %d and %d symbols.";
    public static final int BOARD_NAME_MIN_LENGTH = 5;
    public static final int BOARD_NAME_MAX_LENGTH = 10;
    private String name;
    private List<Task> tasks;
    private List<String> activityHistory;

    private final List<Feedback> feedbacks;
    private final List<Story> stories;
    private final List<Bug> bugs= new ArrayList<>();

    public BoardImpl(String name) {
        setName(name);
        this.tasks = new ArrayList<>();
        this.activityHistory = new ArrayList<>();
        this.feedbacks = new ArrayList<>();
        this.stories = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Task> getTasks() {
        return new ArrayList<>();
    }


    public List<Bug> getBugs() {
        return new ArrayList<>(bugs);
    }


    public List<String> getActivityHistory() {
        return activityHistory;
    }

    @Override
    public void addBug(Bug bug) { bugs.add(bug);
    }
    public void addFeedback(Feedback feedback) {feedbacks.add(feedback);
    }

    public void addStory(Story story) {
        stories.add(story);
    }

    public void setName(String name) {
        ValidationHelpers.validateStringLength(name, BOARD_NAME_MIN_LENGTH,BOARD_NAME_MAX_LENGTH,String.format(INVALID_NAME_MESSAGE, BOARD_NAME_MIN_LENGTH,BOARD_NAME_MAX_LENGTH));
        this.name = name;
    }
    public int changeFeedback(Feedback feedback) {
        int currentIndex=-1;
        int currentRating=0;

        for(Feedback feedback1:feedbacks){
            currentIndex++;
            currentRating=feedback1.getRating();
            if(feedback1.getTitle().equals(feedback.getTitle()) && feedback1.getDescription().equals(feedback.getDescription())){
                feedbacks.set(currentIndex,feedback);

            }
        }

        return currentRating;
    }
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void setActivityHistory(List<String> activityHistory) {
        this.activityHistory = activityHistory;
    }
}
