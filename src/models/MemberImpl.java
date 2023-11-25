package models;

import models.contracts.History;
import models.contracts.Member;
import models.contracts.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MemberImpl implements Member {
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
        return new ArrayList<>(tasks);
    }

    public List<History> getActivityHistory() {
        return new ArrayList<>(activityHistory);
    }

    @Override
    public void addHistory(String events) {
        History event = new HistoryImpl(events);
        activityHistory.add(event);
    }

    private void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberImpl member = (MemberImpl) o;
        return Objects.equals(name, member.name) && Objects.equals(tasks, member.tasks) && Objects.equals(activityHistory, member.activityHistory);
    }


}
