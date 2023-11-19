package models;

import models.contracts.*;
import utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MemberImpl implements Member {

    public static final int NAME_MAX_LENGTH = 15;
    public static final int NAME_MIN_LENGTH = 5;
    public static final String NAME_ALREADY_EXIST_MESSAGE = "This name already exist.";
    public static final String INVALID_NAME_MESSAGE =
            String.format("Name should be between %d and %d symbols.",NAME_MIN_LENGTH,NAME_MAX_LENGTH);
    static List<String> memberNames = new ArrayList<>();
    private String name;
    private List<Task> tasks;
    private List<History> activityHistory;

    public MemberImpl(String name) {
        setName(name);
        this.tasks = new ArrayList<>();
        this.activityHistory = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public List<History> getActivityHistory() {
        return new ArrayList<>(activityHistory);
    }
    public void addActivityHistory(History history){
        activityHistory.add(history);
    }

    private void setName(String name) {
        ValidationHelpers.validateStringLength(name, NAME_MIN_LENGTH, NAME_MAX_LENGTH,String.format(INVALID_NAME_MESSAGE));
        ValidationHelpers.validateMemberName(memberNames,name,NAME_ALREADY_EXIST_MESSAGE);

        ValidationHelpers.validateTeamName(TeamImpl.teamNames,name,NAME_ALREADY_EXIST_MESSAGE);
        this.name = name;
        memberNames.add(name);
    }

    private void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }



    public List<String> getAllMemberNames(){
        List<String> allMemberNames = new ArrayList<>();
        for (String name :
                memberNames) {
            allMemberNames.add(name);
        }
        return allMemberNames;
    }
    public String showAllMembers(){
        StringBuilder allNames = new StringBuilder();
        for (String name :
                memberNames) {
            allNames.append(name + " ");
        }
        return allNames.toString();
    }

    @Override
    public void logEvent(String event) {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberImpl member = (MemberImpl) o;
        return Objects.equals(name, member.name) && Objects.equals(tasks, member.tasks) && Objects.equals(activityHistory, member.activityHistory);
    }



    public void addHistory(String events) {
        History event= new HistoryImpl(events);
        addActivityHistory(event);
    }

}
