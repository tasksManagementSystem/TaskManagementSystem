package models;

import models.contracts.Member;
import models.contracts.Task;

import java.util.ArrayList;
import java.util.List;

public class MemberImpl extends UserImpl implements Member {


    public MemberImpl(String name, List<Task> tasks, List<String> activityHistory) {
        super(name, tasks, activityHistory);
    }
    public String getName(){
        return getName();
    }

    public List<Task> getTasks(){
        return new ArrayList<>(getTasks());
    }

    public List<String> getActivityHistory(){
        return new ArrayList<>(getActivityHistory());
    }
}
