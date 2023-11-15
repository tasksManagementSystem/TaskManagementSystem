package models;

import models.contracts.Board;
import models.contracts.Member;
import models.contracts.Task;
import utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public class MemberImpl implements Member {

    public static final int NAME_MAX_LENGTH = 15;
    public static final int NAME_MIN_LENGTH = 5;
    public static final String NAME_ALREADY_EXIST_MESSAGE = "This name already exist.";
    public static final String INVALID_NAME_MESSAGE =
            String.format("Name should be between %d and %d symbols.",NAME_MIN_LENGTH,NAME_MAX_LENGTH);
    static List<String> memberNames = new ArrayList<>();
    private String name;
    private List<Task> tasks;
    private List<String> activityHistory;

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

    public List<String> getActivityHistory() {
        return activityHistory;
    }

    private void setName(String name) {
        ValidationHelpers.validateMemberName(memberNames,name,NAME_ALREADY_EXIST_MESSAGE);
        ValidationHelpers.validateTeamName(TeamImpl.teamNames,name,NAME_ALREADY_EXIST_MESSAGE);
        ValidationHelpers.validateStringLength(name, NAME_MIN_LENGTH, NAME_MAX_LENGTH,String.format(INVALID_NAME_MESSAGE));
        this.name = name;
        memberNames.add(name);
    }

    private void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    private void setActivityHistory(List<String> activityHistory) {
        this.activityHistory = activityHistory;
    }

    public String showAllMembers(){
        StringBuilder allNames = new StringBuilder();
        for (String name :
                memberNames) {
            allNames.append(name + " ");
        }
        return allNames.toString();
    }

}
